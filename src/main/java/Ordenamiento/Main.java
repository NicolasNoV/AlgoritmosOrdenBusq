package Ordenamiento;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        // Cargar datos desde archivo de texto
        int[] data = loadArrayFromFile("1millon_numeros.txt");

        // Crear un map para almacenar los tiempos de ejecución
        Map<String, Long> executionTimes = new HashMap<>();

        // Clonar el array original para cada algoritmo
        int[] dataClone;

        // Ejecución y medición del algoritmo Burbuja
        dataClone = data.clone();
        long start = System.nanoTime();
        BubbleSort.sort(dataClone);
        long end = System.nanoTime();
        long timeBurbuja = end - start;
        executionTimes.put("Burbuja", timeBurbuja);
        System.out.println("Tiempo Burbuja: " + timeBurbuja / 1_000_000.0 + " ms");

        // Ejecución y medición del algoritmo Quicksort
        dataClone = data.clone();
        start = System.nanoTime();
        QuickSort.sort(dataClone, 0, dataClone.length - 1);
        end = System.nanoTime();
        long timeQuickSort = end - start;
        executionTimes.put("Quicksort", timeQuickSort);
        System.out.println("Tiempo Quicksort: " + timeQuickSort / 1_000_000.0 + " ms");

        // Ejecución y medición del algoritmo Pigeonhole Sort
        dataClone = data.clone();
        start = System.nanoTime();
        PigeonholeSort.sort(dataClone);
        end = System.nanoTime();
        long timePigeonhole = end - start;
        executionTimes.put("Pigeonhole Sort", timePigeonhole);
        System.out.println("Tiempo Pigeonhole Sort: " + timePigeonhole / 1_000_000.0 + " ms");

        // Ejecución y medición del algoritmo Merge Sort
        dataClone = data.clone();
        start = System.nanoTime();
        MergeSort.sort(dataClone, 0, dataClone.length - 1);
        end = System.nanoTime();
        long timeMergeSort = end - start;
        executionTimes.put("Merge Sort", timeMergeSort);
        System.out.println("Tiempo Merge Sort: " + timeMergeSort / 1_000_000.0 + " ms");

        // Ejecución y medición del algoritmo Bitonic Sort
        dataClone = data.clone();
        start = System.nanoTime();
        BitonicSort.sort(dataClone, 0, dataClone.length, 1);
        end = System.nanoTime();
        long timeBitonic = end - start;
        executionTimes.put("Bitonic Sort", timeBitonic);
        System.out.println("Tiempo Bitonic Sort: " + timeBitonic / 1_000_000.0 + " ms");
/*
        // Ejecución y medición del algoritmo Stooge Sort
        dataClone = data.clone();
        start = System.nanoTime();
        StoogeSort.sort(dataClone, 0, dataClone.length - 1);
        end = System.nanoTime();
        long timeStooge = end - start;
        executionTimes.put("Stooge Sort", timeStooge);
        System.out.println("Tiempo Stooge Sort: " + timeStooge / 1_000_000.0 + " ms");
*/
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
                "Comparación de tiempos de algoritmos de ordenamiento",
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
