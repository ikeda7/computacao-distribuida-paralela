package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigInteger;
import compute.Compute;

public class ComputeFactorial {
    public static void main(String args[]) {
        computeFactorial(args);
    }

    private static void computeFactorial(String[] args) {
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
            Compute comp = (Compute) registry.lookup(name);
            Factorial task = new Factorial(Integer.parseInt(args[2]));
            BigInteger factorial = comp.executeTask(task);
            System.out.println(factorial);
        } catch (Exception e) {
            System.err.println("ComputeFactorial exception:");
            e.printStackTrace();
        }
    }    
}