public class ProgrammerCalculator extends Calculator{
    private ArithmeticOperation arithmeticOperations = new ArithmeticOperation();

    @Override
    public void run() {
        System.out.println("Программерский калькулятор (базовые операции)");

        while (true) {
            System.out.print("Введите выражение (или 'exit'): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) break;

            try {
                double result = arithmeticOperations.calculate(input);
                printResult(result);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
