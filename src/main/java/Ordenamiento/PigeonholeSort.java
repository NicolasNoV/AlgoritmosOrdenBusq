package Ordenamiento;

public class PigeonholeSort {
    public static void sort(int[] arr) {
        int min = arr[0], max = arr[0], n = arr.length;
        for (int i = 1; i < n; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        int range = max - min + 1;
        int[] pigeonhole = new int[range];
        for (int i = 0; i < n; i++)
            pigeonhole[arr[i] - min]++;
        int index = 0;
        for (int i = 0; i < range; i++)
            while (pigeonhole[i]-- > 0)
                arr[index++] = i + min;
    }
}

