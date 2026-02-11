package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import compute.Compute;

public class ComputeCalculator {
    public static void main(String args[]) {
        computeCalculator(args);
    }

    private static void computeCalculator(String[] args) {
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
            Compute comp = (Compute) registry.lookup(name);
            Calculator task = new Calculator(Double.parseDouble(args[2]), Double.parseDouble(args[4]), args[3].charAt(0));
            Double result = comp.executeTask(task);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("ComputeCalculator exception:");
            e.printStackTrace();
        }
    }    
}