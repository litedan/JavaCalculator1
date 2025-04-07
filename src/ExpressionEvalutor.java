import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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

    public double evaluate(String expression) {
        String[] tokens = expression.split("");
        Deque<Double> values = new ArrayDeque<>();
        Deque<String> ops = new ArrayDeque<>();

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                values.push(Double.parseDouble(token));
            } else if (PRECEDENCE.containsKey(token)) {
                while (!ops.isEmpty() && hasPrecedence(token, ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(token);
            } else {
                throw new IllegalArgumentException("Неверное выражение: " + token);
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

    private double applyOp(String op, double b, double a) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Деление на ноль");
                return a / b;
            case "%": return a % b;
            case "^": return Math.pow(a, b);
            default: throw new IllegalArgumentException("Неизвестный оператор: " + op);
        }
    }
}