package Busqueda;

public class LimitedLinearSearch {
    public static int limitedLinearSearch(int[] arr, int target, int limit) {
        for (int i = 0; i < Math.min(arr.length, limit); i++) {
            if (arr[i] == target) {
                return i; // Número encontrado, devolver índice
            }
        }
        return -1; // Número no encontrado dentro del límite
    }
}

