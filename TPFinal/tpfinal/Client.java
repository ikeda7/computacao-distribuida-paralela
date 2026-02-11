import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        Socket s = null;
        Scanner scanner = new Scanner(System.in);

        try {
            int serverPort = 1234;
            s = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            while (true) {
                System.out.println("Escolha um serviço:");
                System.out.println("1. Conversão de Temperatura");
                System.out.println("2. Número Aleatório");
                System.out.println("3. Conversão de Moeda");
                System.out.println("4. Cálculo de IMC");
                System.out.println("5. Sair");
                int choice = scanner.nextInt();

                if (choice == 5) {
                    break;
                }

                String request = null;

                switch (choice) {
                    case 1:
                        System.out.print("Digite o valor: ");
                        double tempValue = scanner.nextDouble();
                        System.out.print("Digite o tipo (C/F): ");
                        String tempType = scanner.next();
                        request = "temperatura " + tempValue + " " + tempType;
                        break;
                    case 2:
                        System.out.print("Digite o valor mínimo: ");
                        int min = scanner.nextInt();
                        System.out.print("Digite o valor máximo: ");
                        int max = scanner.nextInt();
                        request = "random " + min + " " + max;
                        break;
                    case 3:
                        System.out.print("Digite o valor: ");
                        double currencyValue = scanner.nextDouble();
                        System.out.print("Digite o tipo (USD/BRL): ");
                        String currencyType = scanner.next();
                        request = "moeda " + currencyValue + " " + currencyType;
                        break;
                    case 4:
                        System.out.print("Digite o peso: ");
                        double peso = scanner.nextDouble();
                        System.out.print("Digite a altura: ");
                        double altura = scanner.nextDouble();
                        request = "imc " + peso + " " + altura;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        continue;
                }

                out.writeUTF(request); // Envia a solicitação
                String data = in.readUTF(); // Recebe a resposta
                System.out.println("Recebido: " + data);
            }

        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                if (s != null) s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
            scanner.close();
        }
    }
}
