package Ordenamiento;

public class MergeSort {
    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            // Encuentra el punto medio del array
            int mid = (left + right) / 2;

            // Ordena las dos mitades de forma recursiva
            sort(arr, left, mid);
            sort(arr, mid + 1, right);

            // Mezcla las dos mitades ordenadas
            merge(arr, left, mid, right);
        }
    }

    // Método para mezclar dos subarrays en un solo array ordenado
    private static void merge(int[] arr, int left, int mid, int right) {
        // Encuentra los tamaños de los subarrays a mezclar
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Crea arrays temporales para almacenar los datos
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copia los datos a los arrays temporales
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        // Mezcla los arrays temporales en el array original
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copia los elementos restantes de L[], si hay alguno
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copia los elementos restantes de R[], si hay alguno
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
