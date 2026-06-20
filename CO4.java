import java.util.*;

public class CO4 {

    // Heap Sort
    public static void heapSort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    static void heapify(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    // Counting Sort
    public static void countingSort(int arr[]) {
        int max = Arrays.stream(arr).max().getAsInt();
        int count[] = new int[max + 1];

        for (int num : arr)
            count[num]++;

        int index = 0;

        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }

    // Radix Sort
    public static void radixSort(int arr[]) {
        int max = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, exp);
    }

    static void countSort(int arr[], int exp) {
        int n = arr.length;
        int output[] = new int[n];
        int count[] = new int[10];

        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    // Greedy Knapsack
    static void greedyKnapsack(int cost[], int budget) {
        Arrays.sort(cost);

        System.out.println("\nSelected Treatments:");
        int total = 0;

        for (int c : cost) {
            if (total + c <= budget) {
                System.out.println("Treatment Cost = ₹" + c);
                total += c;
            }
        }
        System.out.println("Total Cost = ₹" + total);
    }

    // Longest Common Subsequence
    static int lcs(String a, String b) {
        int m = a.length();
        int n = b.length();

        int dp[][] = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }

    // Matrix Chain Multiplication
    static int matrixChain(int p[]) {
        int n = p.length;
        int dp[][] = new int[n][n];

        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int q = dp[i][k] + dp[k + 1][j]
                            + p[i - 1] * p[k] * p[j];

                    if (q < dp[i][j])
                        dp[i][j] = q;
                }
            }
        }
        return dp[1][n - 1];
    }

    public static void main(String[] args) {

        System.out.println("=== MANAGEMENT SYSTEM ===");

        // Heap Sort
        int priority[] = {85, 60, 95, 70, 80};

        System.out.println("\nHealth Priority Scores Before Heap Sort:");
        System.out.println(Arrays.toString(priority));

        heapSort(priority);

        System.out.println("After Heap Sort:");
        System.out.println(Arrays.toString(priority));

        // Counting Sort
        int healthScores[] = {4, 2, 5, 3, 1, 4, 2};

        countingSort(healthScores);

        System.out.println("\nHealth Scores After Counting Sort:");
        System.out.println(Arrays.toString(healthScores));

        // Radix Sort
        int costs[] = {4500, 1200, 8000, 3500, 2200};

        radixSort(costs);

        System.out.println("\nTreatment Costs After Radix Sort:");
        System.out.println(Arrays.toString(costs));

        // Greedy Knapsack
        int treatmentCosts[] = {1000, 1500, 2000, 3000, 4000};
        int budget = 6000;

        greedyKnapsack(treatmentCosts, budget);

        // LCS
        String pet1 = "VACCINE";
        String pet2 = "VETCARE";

        System.out.println("\nLCS Length = " + lcs(pet1, pet2));

        // MCM
        int matrices[] = {10, 20, 30, 40};

        System.out.println("Minimum MCM Cost = " + matrixChain(matrices));

        System.out.println("\nCO4 Execution Completed Successfully");
    }
}