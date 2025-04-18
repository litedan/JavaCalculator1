import java.util.Objects;
import java.util.Scanner;

public class ProgrammerCalculator{
    private final Scanner scanner = new Scanner(System.in);
    private NumberSystem currentNumberSystem = NumberSystem.DECIMAL;

    public void start() {
        System.out.println("Программерский калькулятор");

        while (true) {
            System.out.println("\nТекущая система счисления: " + currentNumberSystem);
            System.out.println("1. Выбрать систему счисления");
            System.out.println("2. Вычисления");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    selectNumberSystem();
                    break;
                case "2":
                    new CalculatorMenu(currentNumberSystem).show();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void selectNumberSystem() {
        System.out.println("\nВыберите систему счисления:");
        System.out.println("1. Двоичная");
        System.out.println("2. Восьмеричная");
        System.out.println("3. Десятичная");
        System.out.println("4. Шестнадцатеричная");
        System.out.print("Ваш выбор: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                currentNumberSystem = NumberSystem.BINARY;
                break;
            case "2":
                currentNumberSystem = NumberSystem.OCTAL;
                break;
            case "3":
                currentNumberSystem = NumberSystem.DECIMAL;
                break;
            case "4":
                currentNumberSystem = NumberSystem.HEXADECIMAL;
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }
}