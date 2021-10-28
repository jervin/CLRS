package ch2;
import org.apache.commons.lang3.ArrayUtils;

public class MergeSort {

    public static void merge(int[] array, int p, int q, int r) {
        var n1 = q - p + 1;
        var n2 = r - q;
        int[] left = new int[n1];
        for (int i = 0; i < n1; i++)
            left[i] = array[p + i];
        int[] right = new int[n2];
        for (int j = 0; j< n2; j++)
            right[j] = array[q + j + 1];
        var i = 0;
        var j = 0;

        var k = p;
        for (; k < r; k++) {
            if (i >= n1 || j >= n2)
                break;
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
        }
        while (i < n1) {
            array[k] = left[i];
            k++;
            i++;
        }
        while (j < n2) {
            array[k] = right[j];
            k++;
            j++;
        }
    }

    public static void mergeSort(final int[] array, final int p, final int r) {
        if (p >= r)
            return;
        int q = (p + r)/2;
        mergeSort(array, p, q);
        mergeSort(array, q + 1, r);
        merge(array, p, q, r);
    }

    public static void main(final String[] args) {
        int[] array = {1, 4, 2, 3};
        merge(array, 0, 1, 3);
        System.out.println("MergeSortJava.main(): " + ArrayUtils.toString(array));

        array = new int[] {2, 4, 5, 7, 1, 2, 3, 6};
        mergeSort(array, 0,7);

        System.out.println("MergeSortJava.main(): " + ArrayUtils.toString(array));

        array = new int[] {2, 7, 11, 15};
        mergeSort(array, 0,3);

        System.out.println("MergeSortJava.main(): " + ArrayUtils.toString(array));
    }
}
