import java.util.Scanner;

public abstract class Calculator {
    protected Scanner scanner = new Scanner(System.in);

    public abstract void run();

    protected double parseNumber(String num){
        try{
            return Double.parseDouble(num);
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException("Невеерный формат числа");
        }
    }

    protected void printResult(double res){
        System.out.println("Результат: " + res);
    }
}
