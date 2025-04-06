public class ProgrammerCalculator extends Calculator{
    private ArithmeticOperation arithmeticOperations = new ArithmeticOperation();

    private BitwiseOperations bitwiseOps = new BitwiseOperations();
    private LogicalOperations logicalOps = new LogicalOperations();

    @Override
    public void run() {
        System.out.println("Программерский калькулятор");
        System.out.println("Режимы: 1 - Арифметика, 2 - Битовые, 3 - Логические");

        while (true) {
            System.out.print("Выберите режим (1-3 или 'exit'): ");
            String mode = scanner.nextLine();

            if (mode.equalsIgnoreCase("exit")) break;

            try {
                switch (mode) {
                    case "1": arithmeticMode(); break;
                    case "2": bitwiseMode(); break;
                    case "3": logicalMode(); break;
                    default: System.out.println("Неверный режим");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void arithmeticMode() {
        System.out.println("Арифметический режим");
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();
        double result = arithmeticOperations.calculate(input);
        printResult(result);
    }

    private void bitwiseMode() {
        System.out.println("Битовый режим");
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();
        int result = bitwiseOps.calculate(input);
        System.out.println("Результат: " + result +
                " (bin: " + Integer.toBinaryString(result) +
                ", hex: " + Integer.toHexString(result) + ")");
    }

    private void logicalMode() {
        System.out.println("Логический режим");
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();
        boolean result = logicalOps.calculate(input);
        System.out.println("Результат: " + result);
    }
}
