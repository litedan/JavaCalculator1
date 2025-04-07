import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class BitwiseExpressionEvaluator {
    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();

    static {
        PRECEDENCE.put("|", 1);
        PRECEDENCE.put("^", 2);
        PRECEDENCE.put("&", 3);
        PRECEDENCE.put("<<", 4);
        PRECEDENCE.put(">>", 4);
        PRECEDENCE.put("~", 5);
    }

    public int evaluate(String expression) {
        String[] tokens = expression.split("\\s+");
        Deque<Integer> values = new ArrayDeque<>();
        Deque<String> ops = new ArrayDeque<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.matches("-?\\d+")) {
                values.push(Integer.parseInt(token));
            } else if (token.equals("~")) {
                // Унарный оператор
                if (i + 1 >= tokens.length) {
                    throw new IllegalArgumentException("Недостаточно операндов для ~");
                }
                String nextToken = tokens[++i];
                if (!nextToken.matches("-?\\d+")) {
                    throw new IllegalArgumentException("Ожидалось число после ~");
                }
                values.push(~Integer.parseInt(nextToken));
            } else if (PRECEDENCE.containsKey(token)) {
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

        return values.pop();
    }

    private boolean hasPrecedence(String op1, String op2) {
        return PRECEDENCE.get(op2) >= PRECEDENCE.get(op1);
    }

    private int applyOp(String op, int b, int a) {
        switch (op) {
            case "AND": return a & b;
            case "OR": return a | b;
            case "XOR": return a ^ b;
            case "<<": return a << b;
            case ">>": return a >> b;
            default: throw new IllegalArgumentException("Неизвестный оператор: " + op);
        }
    }
}