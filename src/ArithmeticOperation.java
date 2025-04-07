public class ArithmeticOperation {
    private ExpressionEvalutor evaluator = new ExpressionEvalutor();

    public double calculate(String expression) {
        return evaluator.evaluate(expression);
    }
}