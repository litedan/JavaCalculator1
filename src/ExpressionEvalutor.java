import java.util.*;

public class ExpressionEvalutor {
    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();

    static {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
        PRECEDENCE.put("%", 2);
        PRECEDENCE.put("^", 3);
    }

    public String calculate(String expression, NumberSystem numberSystem) {
        String[] tokens = expression.split(" ");
        Stack<Double> values = new Stack<>();
        Stack<String> ops = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token, numberSystem)) {
                values.push(parseNumber(token, numberSystem));
            } else if (isOperator(token)) {
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

    private double parseNumber(String numStr, NumberSystem numberSystem) {
        return Integer.parseInt(numStr, numberSystem.getBase());
    }

    private String formatResult(double value, NumberSystem numberSystem) {
        switch (numberSystem) {
            case BINARY:
                return Integer.toBinaryString((int) value);
            case OCTAL:
                return Integer.toOctalString((int) value);
            case HEXADECIMAL:
                return Integer.toHexString((int) value);
            default:
                return String.valueOf(value);
        }
    }

    private boolean isOperator(String token) {
        return token.matches("[+\\-*/%^]");
    }

    private boolean hasPrecedence(String op1, String op2) {
        return getPrecedence(op2) >= getPrecedence(op1);
    }

    private int getPrecedence(String op) {
        switch (op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }

    private double applyOp(String op, double b, double a) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Деление на ноль");
                return a / b;
            case "%":
                return a % b;
            case "^":
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Неизвестный оператор");
        }
    }
}