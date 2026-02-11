import java.io.Serializable;

public class Moeda implements Task<Double>, Serializable {
    private double value;
    private String type; // "USD" para Dólar, "BRL" para Real

    public Moeda(double value, String type) {
        this.value = value;
        this.type = type;
    }

    public Double execute() {
        if (type.equalsIgnoreCase("USD")) {
            return value * 5.0; // Exemplo de taxa de conversão de dólar para real
        } else if (type.equalsIgnoreCase("BRL")) {
            return value / 5.0; // Exemplo de taxa de conversão de real para dólar
        } else {
            return null;
        }
    }
}
