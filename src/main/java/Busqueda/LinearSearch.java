package Busqueda;

public class LinearSearch {
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Número encontrado, devolver índice
            }
        }
        return -1; // Número no encontrado
    }
}

