package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServidor {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor en ejecución. Esperando conexiones...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String input = in.readLine();
                    String[] parts = input.split(" ");
                    int expression = Integer.parseInt(parts[0]);
                    String operation = parts[1];
                    double result = evaluateExpression(expression, operation);
                    out.println(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double evaluateExpression(int expression, String operation) {
        switch (expression) {
            case 1:
                String[] sum = operation.split("\\+");
                double operandSumA = Double.parseDouble(sum[0]);
                double operandSumB = Double.parseDouble(sum[1]);
                return operandSumA + operandSumB;

            case 2:
                String[] subs = operation.split("\\-");
                double operandSubA = Double.parseDouble(subs[0]);
                double operandSubB = Double.parseDouble(subs[1]);
                return operandSubA - operandSubB;

            case 3:
                String[] mult = operation.split("\\*");
                double operandMultA = Double.parseDouble(mult[0]);
                double operandMultB = Double.parseDouble(mult[1]);
                return operandMultA * operandMultB;

            case 4:
                String[] div = operation.split("\\/");
                double operandDivA = Double.parseDouble(div[0]);
                double operandDivB = Double.parseDouble(div[1]);
                return operandDivA / operandDivB;

            default:
                System.out.println("Operación no reconocida.");
                return 0;
        }
    }
}