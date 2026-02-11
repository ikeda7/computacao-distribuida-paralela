package client;

import compute.Task;
import java.io.Serializable;
import java.math.BigInteger;

public class Factorial implements Task<BigInteger>, Serializable {

    private static final long serialVersionUID = 1L;
    private final int number;

    public Factorial(int number) {
        this.number = number;
    }

    @Override
    public BigInteger execute() {
        return factorial(number);
    }

    private BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}