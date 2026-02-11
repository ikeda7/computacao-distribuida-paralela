import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Random implements Task<Integer>, Serializable {
    private int min;
    private int max;

    public Random(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public Integer execute() {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
