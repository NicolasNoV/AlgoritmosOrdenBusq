package Ordenamiento;

public class BitonicSort {
    // Método principal para iniciar el ordenamiento
    public static void sort(int[] arr, int low, int cnt, int dir) {
        if (cnt > 1) {
            int k = cnt / 2;

            // Ordenar las primeras mitades en dirección ascendente
            sort(arr, low, k, 1);
            // Ordenar la segunda mitad en dirección descendente
            sort(arr, low + k, k, 0);

            // Mezclar los resultados en dirección correcta
            bitonicMerge(arr, low, cnt, dir);
        }
    }

    // Método para mezclar el subarray en la dirección especificada
    private static void bitonicMerge(int[] arr, int low, int cnt, int dir) {
        if (cnt > 1) {
            int k = cnt / 2;
            for (int i = low; i < low + k; i++) {
                if ((dir == 1 && arr[i] > arr[i + k]) || (dir == 0 && arr[i] < arr[i + k])) {
                    // Intercambiar los elementos si no están en la dirección correcta
                    int temp = arr[i];
                    arr[i] = arr[i + k];
                    arr[i + k] = temp;
                }
            }

            // Aplicar la mezcla recursivamente
            bitonicMerge(arr, low, k, dir);
            bitonicMerge(arr, low + k, k, dir);
        }
    }
}

