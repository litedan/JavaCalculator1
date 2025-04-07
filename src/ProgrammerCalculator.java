import java.util.Objects;

public class ProgrammerCalculator extends Calculator{
    private ArithmeticOperation arithmeticOperations = new ArithmeticOperation();
    private BitwiseOperations bitwiseOps = new BitwiseOperations();

    @Override
    public void run() {
        System.out.println("Программерский калькулятор");
        System.out.println("Режимы: 1 - Арифметика, 2 - Битовые");

        while (true) {
            System.out.print("Выберите режим (1-2 или 'exit'): ");
            String mode = scanner.nextLine();

            if (mode.equalsIgnoreCase("exit")) break;

            try {
                switch (mode) {
                    case "1": arithmeticMode(); break;
                    case "2": bitwiseMode(); break;
                    default: System.out.println("Неверный режим");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void arithmeticMode() {
        System.out.println("Арифметический режим (+, -, *, /, %, ^) для выхода напишите exit");
        while(true) {
            System.out.print("Введите выражение: ");
            String input = scanner.nextLine();
            if (Objects.equals(input, "exit")) {
                break;
            }
            double result = arithmeticOperations.calculate(input);
            printResult(result);
        }
    }

    private void bitwiseMode() {
        System.out.println("Битовый режим (AND, OR, XOR, <<, >>)");
        while(true) {
            System.out.println("Пример: 5 & 3 | 4");
            System.out.print("Введите выражение: ");
            String input = scanner.nextLine();
            if(input == "exit"){
                break;
            }
            int result = bitwiseOps.calculate(input);
            System.out.println("Результат: " + result +
                    " (bin: " + Integer.toBinaryString(result) +
                    ", hex: " + Integer.toHexString(result) + ")");
        }
    }

}
