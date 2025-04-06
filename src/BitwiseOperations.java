public class BitwiseOperations {
    public int calculate(String expression) {
        String[] parts = expression.split(" ");

        if (parts.length == 2 && parts[0].equals("~")) {
            return bitwiseNot(Integer.parseInt(parts[1]));
        }

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }

        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[2]);
        String op = parts[1];

        switch (op) {
            case "&": return bitwiseAnd(a, b);
            case "|": return bitwiseOr(a, b);
            case "<<": return leftShift(a, b);
            case ">>": return rightShift(a, b);
            default: throw new IllegalArgumentException("Неизвестная операция");
        }
    }

    private int bitwiseAnd(int a, int b) { return a & b; }
    private int bitwiseOr(int a, int b) { return a | b; }
    private int bitwiseNot(int a) { return ~a; }
    private int leftShift(int a, int b) { return a << b; }
    private int rightShift(int a, int b) { return a >> b; }
}
