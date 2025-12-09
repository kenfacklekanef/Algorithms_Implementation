public class Sorting {

    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(a, lo, hi);
        quickSort(a, lo, p - 1);
        quickSort(a, p + 1, hi);
    }

    private static int partition(int[] a, int lo, int hi) {
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (a[j] <= pivot) {
                int t = a[i]; a[i] = a[j]; a[j] = t;
                i++;
            }
        }
        int t = a[i]; a[i] = a[hi]; a[hi] = t;
        return i;
    }

    public static int[] mergeSort(int[] a) {
        if (a.length <= 1) return a.clone();
        int mid = a.length / 2;
        int[] left = mergeSort(java.util.Arrays.copyOfRange(a, 0, mid));
        int[] right = mergeSort(java.util.Arrays.copyOfRange(a, mid, a.length));
        return merge(left, right);
    }

    private static int[] merge(int[] l, int[] r) {
        int[] res = new int[l.length + r.length];
        int i=0,j=0,k=0;
        while (i<l.length && j<r.length)
            res[k++] = l[i] <= r[j] ? l[i++] : r[j++];

        while (i<l.length) res[k++] = l[i++];
        while (j<r.length) res[k++] = r[j++];
        return res;
    }

    public static int[] insertionSort(int[] a) {
        int[] arr = a.clone();
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        return arr;
    }
}
