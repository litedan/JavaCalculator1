public enum NumberSystem {
    BINARY(2, "Двоичная"),
    OCTAL(8, "Восьмеричная"),
    DECIMAL(10, "Десятичная"),
    HEXADECIMAL(16, "Шестнадцатеричная");

    private final int base;
    private final String description;

    NumberSystem(int base, String description) {
        this.base = base;
        this.description = description;
    }

    public int getBase() {
        return base;
    }

    @Override
    public String toString() {
        return description;
    }
}