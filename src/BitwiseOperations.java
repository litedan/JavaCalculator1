public class BitwiseOperations {
    private BitwiseExpressionEvaluator evaluator = new BitwiseExpressionEvaluator();

    public int calculate(String expression) {
        return evaluator.evaluate(expression);
    }
}