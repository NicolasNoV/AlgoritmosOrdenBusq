package Ordenamiento.CrearArchivos;
import java.io.*;
import java.util.Random;

public class CrearArchivos {

    // Método para generar una lista de números enteros de 8 dígitos y guardarlos en un archivo .txt
    public static void generateRandomNumbersToFile(int size, String fileName) throws IOException {
        Random random = new Random();
        int min = 10000000; // Mínimo número de 8 dígitos
        int max = 99999999; // Máximo número de 8 dígitos

        // Abrir el archivo para escribir los números generados
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < size; i++) {
                int randomNumber = random.nextInt((max - min) + 1) + min;
                writer.write(Integer.toString(randomNumber));
                writer.newLine(); // Escribir cada número en una nueva línea
            }
        }
    }

    // Método para leer un archivo .txt y cargar los números en un arreglo de enteros
    public static int[] loadNumbersFromFile(String fileName, int size) throws IOException {
        int[] numbers = new int[size];

        // Leer el archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = 0;

            // Leer cada línea y convertirla a número entero
            while ((line = reader.readLine()) != null && index < size) {
                numbers[index++] = Integer.parseInt(line);
            }
        }

        return numbers;
    }

    public static void main(String[] args) {
        try {
            // Generar 10.000, 100.000 y 1.000.000 números aleatorios de 8 dígitos y guardarlos en archivos
            generateRandomNumbersToFile(10000, "10mil_numeros.txt");
            generateRandomNumbersToFile(100000, "100mil_numeros.txt");
            generateRandomNumbersToFile(1000000, "1millon_numeros.txt");

            // Cargar los números del archivo de 10.000 y mostrar algunos
            int[] tenThousandNumbers = loadNumbersFromFile("10mil_numeros.txt", 10000);
            System.out.println("Ejemplo de 10 números del archivo de 10.000: ");
            for (int i = 0; i < 10; i++) {
                System.out.println(tenThousandNumbers[i]);
            }

            // Cargar los números del archivo de 100.000 y mostrar algunos (opcional)
            int[] hundredThousandNumbers = loadNumbersFromFile("100mil_numeros.txt", 100000);
            System.out.println("Ejemplo de 10 números del archivo de 100.000: ");
            for (int i = 0; i < 10; i++) {
                System.out.println(hundredThousandNumbers[i]);
            }

            // Cargar los números del archivo de 1.000.000 y mostrar algunos (opcional)
            int[] oneMillionNumbers = loadNumbersFromFile("1millon_numeros.txt", 1000000);
            System.out.println("Ejemplo de 10 números del archivo de 1.000.000: ");
            for (int i = 0; i < 10; i++) {
                System.out.println(oneMillionNumbers[i]);
            }

        } catch (IOException e) {
            System.err.println("Error al manejar archivos: " + e.getMessage());
        }
    }
}
