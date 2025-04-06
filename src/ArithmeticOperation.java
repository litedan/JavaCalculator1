public class ArithmeticOperation {
    public double calculate(String expression) {
        String[] parts = expression.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }

        double a = Double.parseDouble(parts[0]);
        double b = Double.parseDouble(parts[2]);
        String op = parts[1];

        switch (op) {
            case "+": return add(a, b);
            case "-": return subtract(a, b);
            case "*": return multiply(a, b);
            case "/": return divide(a, b);
            case "^": return power(a, b);
            case "%": return modulo(a, b);
            default: throw new IllegalArgumentException("Неизвестная операция");
        }
    }

    private double add(double a, double b) { return a + b; }
    private double subtract(double a, double b) { return a - b; }
    private double multiply(double a, double b) { return a * b; }
    private double divide(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Деление на ноль");
        return a / b;
    }
    private double power(double a, double b) { return Math.pow(a, b); }
    private double modulo(double a, double b) { return a % b; }
}
