package Ordenamiento;

public class StoogeSort {
    public static void sort(int[] arr, int l, int h) {
        if (l >= h)
            return;

        if (arr[l] > arr[h]) {
            int temp = arr[l];
            arr[l] = arr[h];
            arr[h] = temp;
        }

        if (h - l + 1 > 2) {
            int t = (h - l + 1) / 3;
            sort(arr, l, h - t);
            sort(arr, l + t, h);
            sort(arr, l, h - t);
        }
    }
}

