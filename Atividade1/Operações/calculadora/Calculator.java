package client;

import compute.Task;
import java.io.Serializable;

public class Calculator implements Task<Double>, Serializable {

    private static final long serialVersionUID = 1L;
    private final double number1;
    private final double number2;
    private final char operation;

    public Calculator(double number1, double number2, char operation) {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
    }

    @Override
    public Double execute() {
        return performOperation();
    }

    private Double performOperation() {
        switch (operation) {
            case '+':
                return number1 + number2;
            case '-':
                return number1 - number2;
            case 'x':
                return number1 * number2;
            case '/':
                return divideNumbers();
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }

    private Double divideNumbers() {
        if (number2 != 0) {
            return number1 / number2;
        } else {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
    }
}