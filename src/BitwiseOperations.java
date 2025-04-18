import java.util.Stack;

public class BitwiseOperations implements Operations {
    public String calculate(String expression, NumberSystem numberSystem) {
        String[] tokens = expression.split(" ");
        Stack<Integer> values = new Stack<>();
        Stack<String> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (isNumber(token, numberSystem)) {
                values.push(parseNumber(token, numberSystem));
            } else if (token.equals("~")) {
                if (i + 1 >= tokens.length || !isNumber(tokens[i+1], numberSystem)) {
                    throw new IllegalArgumentException("Неверный синтаксис для ~");
                }
                values.push(~parseNumber(tokens[++i], numberSystem));
            } else if (isBitwiseOperator(token)) {
                while (!ops.isEmpty() && hasPrecedence(token, ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(token);
            } else {
                throw new IllegalArgumentException("Неизвестный токен: " + token);
            }
        }

        while (!ops.isEmpty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return formatResult(values.pop(), numberSystem);
    }

    private boolean isNumber(String token, NumberSystem numberSystem) {
        try {
            parseNumber(token, numberSystem);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int parseNumber(String numStr, NumberSystem numberSystem) {
        return Integer.parseInt(numStr, numberSystem.getBase());
    }

    private String formatResult(int value, NumberSystem numberSystem) {
        switch (numberSystem) {
            case BINARY: return Integer.toBinaryString(value);
            case OCTAL: return Integer.toOctalString(value);
            case HEXADECIMAL: return Integer.toHexString(value);
            default: return String.valueOf(value);
        }
    }

    private boolean isBitwiseOperator(String token) {
        return token.matches("[&|<>]|<<|>>");
    }

    private boolean hasPrecedence(String op1, String op2) {
        return getPrecedence(op2) >= getPrecedence(op1);
    }

    private int getPrecedence(String op) {
        switch (op) {
            case "|": return 1;
            case "^": return 2;
            case "&": return 3;
            case "<<":
            case ">>": return 4;
            default: return 0;
        }
    }

    private int applyOp(String op, int b, int a) {
        switch (op) {
            case "&": return a & b;
            case "|": return a | b;
            case "^": return a ^ b;
            case "<<": return a << b;
            case ">>": return a >> b;
            default: throw new IllegalArgumentException("Неизвестный оператор");
        }
    }
}