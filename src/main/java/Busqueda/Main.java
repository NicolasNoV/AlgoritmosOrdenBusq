package Busqueda;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // Cargar datos desde archivo de texto
        int[] data = loadArrayFromFile("1millon_numeros.txt");
        int target = 12345678; // Número a buscar

        // Crear un map para almacenar los tiempos de ejecución
        Map<String, Long> executionTimes = new HashMap<>();

        // Clonar el array original para cada algoritmo
        int[] dataClone;

        // Ejecución y medición de Búsqueda Lineal
        dataClone = data.clone();
        long start = System.nanoTime();
        int result = LinearSearch.linearSearch(dataClone, target);
        long end = System.nanoTime();
        long timeLinearSearch = end - start;
        executionTimes.put("Búsqueda Lineal", timeLinearSearch);
        System.out.println("Tiempo Búsqueda Lineal: " + timeLinearSearch / 1_000_000.0 + " ms");

        // Ejecución y medición de Búsqueda Lineal Limitada
        dataClone = data.clone();
        start = System.nanoTime();
        result = LimitedLinearSearch.limitedLinearSearch(dataClone, target, 100000); // Limitar a 100,000
        end = System.nanoTime();
        long timeLimitedLinearSearch = end - start;
        executionTimes.put("Búsqueda Lineal Limitada", timeLimitedLinearSearch);
        System.out.println("Tiempo Búsqueda Lineal Limitada: " + timeLimitedLinearSearch / 1_000_000.0 + " ms");

        // Ordenar el arreglo para búsquedas Binaria y por Saltos
        Arrays.sort(data);

        // Ejecución y medición de Búsqueda Binaria
        dataClone = data.clone();
        start = System.nanoTime();
        result = BinarySearch.binarySearch(dataClone, target);
        end = System.nanoTime();
        long timeBinarySearch = end - start;
        executionTimes.put("Búsqueda Binaria", timeBinarySearch);
        System.out.println("Tiempo Búsqueda Binaria: " + timeBinarySearch / 1_000_000.0 + " ms");

        // Ejecución y medición de Búsqueda por Saltos
        dataClone = data.clone();
        start = System.nanoTime();
        result = JumpSearch.jumpSearch(dataClone, target);
        end = System.nanoTime();
        long timeJumpSearch = end - start;
        executionTimes.put("Búsqueda por Saltos", timeJumpSearch);
        System.out.println("Tiempo Búsqueda por Saltos: " + timeJumpSearch / 1_000_000.0 + " ms");

        // Ordenar los algoritmos según el tiempo de ejecución
        List<Map.Entry<String, Long>> sortedTimes = new ArrayList<>(executionTimes.entrySet());
        sortedTimes.sort(Map.Entry.comparingByValue());

        // Mostrar los resultados en un gráfico de barras
        showBarChart(sortedTimes);
    }

    // Método para cargar el arreglo desde un archivo de texto
    public static int[] loadArrayFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<Integer> list = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            list.add(Integer.parseInt(line));
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // Método para mostrar los resultados en un gráfico de barras
    public static void showBarChart(List<Map.Entry<String, Long>> sortedTimes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Long> entry : sortedTimes) {
            dataset.addValue(entry.getValue() / 1_000_000.0, "Tiempo (ms)", entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparación de tiempos de algoritmos de búsqueda",
                "Algoritmo", "Tiempo (ms)", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}

