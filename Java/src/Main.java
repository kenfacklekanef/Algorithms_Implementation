public class Main {
    public static void main(String[] args) {
        System.out.println("Demo: quicksort");
        int[] arr = {5,1,4,2};
        Sorting.quickSort(arr);
        for (int x: arr) System.out.print(x+" ");
    }
}
