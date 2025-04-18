import java.util.Scanner;

public class CalculatorMenu {
    private final NumberSystem numberSystem;
    private final Scanner scanner = new Scanner(System.in);
    private Operations op;

    public CalculatorMenu(NumberSystem numberSystem) {
        this.numberSystem = numberSystem;
    }

    public void show() {
        System.out.println("\nРежим вычислений (" + numberSystem + ")");

        while (true) {
            System.out.println("\n1. Арифметические операции");
            System.out.println("2. Битовые операции");
            System.out.println("3. Вернуться назад");
            System.out.print("Выберите режим: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Арифметические опреации");
                    op = new ArithmeticOperation();
                    Operations();
                    break;
                case "2":
                    System.out.println("Битовые опреации");
                    op = new BitwiseOperations();
                    Operations();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void Operations() {
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();

        try {
            String result = op.calculate(expression, numberSystem);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}