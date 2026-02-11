import java.io.Serializable;

public class Temperatura implements Task<Double>, Serializable {
    private double value;
    private String type;

    public Temperatura(double value, String type) {
        this.value = value;
        this.type = type;
    }

    public Double execute() {
        if (type.equalsIgnoreCase("C")) {
            return (value * 9/5) + 32; // Celsius to Fahrenheit
        } else if (type.equalsIgnoreCase("F")) {
            return (value - 32) * 5/9; // Fahrenheit to Celsius
        } else {
            return null;
        }
    }
}
