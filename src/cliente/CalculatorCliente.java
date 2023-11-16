package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CalculatorCliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Que te gustaría hacer:\n" +
                    "1. Suma\n" +
                    "2. Resta\n" +
                    "3. Multiplicación\n" +
                    "4. Division\n" +  // Agregué la coma que faltaba aquí
                    "5. Salir");

            System.out.print("Selecciona una opción: ");
            int inputSeleccion = Integer.parseInt(userInput.readLine());
            out.println(inputSeleccion);

            if (inputSeleccion != 5) { // Asegúrate de que no intentemos leer una operación si el usuario eligió salir
                System.out.print("Introduce la operación: ");
                String operation = userInput.readLine();
                out.println(operation);

                String result = in.readLine();
                System.out.println("Resultado: " + result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}