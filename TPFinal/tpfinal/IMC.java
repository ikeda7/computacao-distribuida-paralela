import java.io.Serializable;

public class IMC implements Task<Double>, Serializable {
    private double peso;
    private double altura;

    public IMC(double peso, double altura) {
        this.peso = peso;
        this.altura = altura;
    }

    public Double execute() {
        return peso / (altura * altura);
    }
}
