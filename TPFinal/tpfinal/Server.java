import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class Server {
    public static void main(String args[]) {
        try {
            int serverPort = 1234; // porta do servidor
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Servidor esperando conexões...");
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            while (true) {
                String data = in.readUTF();
                System.out.println("Recebido: " + data);
                String[] parts = data.split(" ");
                String command = parts[0];
                Compute compute = null;
                Task<?> task = null;

                switch (command.toLowerCase()) {
                    case "temperatura":
                        compute = (Compute) registry.lookup("Compute");
                        task = new Temperatura(Double.parseDouble(parts[1]), parts[2]);
                        break;
                    case "random":
                        compute = (Compute) registry.lookup("Compute");
                        task = new Random(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                        break;
                    case "moeda":
                        compute = (Compute) registry.lookup("Compute");
                        task = new Moeda(Double.parseDouble(parts[1]), parts[2]);
                        break;
                    case "imc":
                        compute = (Compute) registry.lookup("Compute");
                        task = new IMC(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                        break;
                    default:
                        out.writeUTF("Comando desconhecido: " + command);
                        continue;
                }

                Object resultado = compute.executeTask(task);
                out.writeUTF(command + " Resultado: " + resultado);
                System.out.println("Enviado: " + command + " Resultado: " + resultado);
            }
        } catch (EOFException e) {
            System.out.println("EOF Exception: Client disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                /* close failed */
            }
        }
    }
}
