public class LogicalOperations {
    public boolean calculate(String expression) {
        String[] parts = expression.split(" ");

        if (parts.length == 2 && parts[0].equals("!")) {
            return logicalNot(Boolean.parseBoolean(parts[1]));
        }

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }

        boolean a = Boolean.parseBoolean(parts[0]);
        boolean b = Boolean.parseBoolean(parts[2]);
        String op = parts[1];

        switch (op) {
            case "&&": return logicalAnd(a, b);
            case "||": return logicalOr(a, b);
            default: throw new IllegalArgumentException("Неизвестная операция");
        }
    }

    private boolean logicalAnd(boolean a, boolean b) { return a && b; }
    private boolean logicalOr(boolean a, boolean b) { return a || b; }
    private boolean logicalNot(boolean a) { return !a; }
}