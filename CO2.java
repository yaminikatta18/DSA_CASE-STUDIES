class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 1, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;

            build(arr, 2 * node, start, mid);
            build(arr, 2 * node + 1, mid + 1, end);

            tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        }
    }

    int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l)
            return Integer.MAX_VALUE;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        int leftMin = query(2 * node, start, mid, l, r);
        int rightMin = query(2 * node + 1, mid + 1, end, l, r);

        return Math.min(leftMin, rightMin);
    }

    void update(int node, int start, int end, int idx, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;

            if (idx <= mid)
                update(2 * node, start, mid, idx, value);
            else
                update(2 * node + 1, mid + 1, end, idx, value);

            tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        }
    }

    int rangeMinQuery(int l, int r) {
        return query(1, 0, n - 1, l, r);
    }

    void updateValue(int idx, int value) {
        update(1, 0, n - 1, idx, value);
    }
}

public class CO2 {

    public static void main(String[] args) {

        int[] petHealthScores = {85, 72, 90, 60, 78, 95, 68, 88};

        SegmentTree segmentTree = new SegmentTree(petHealthScores);

        System.out.println("======================================");
        System.out.println("   PETCARE HEALTH RANGE QUERY SYSTEM");
        System.out.println("======================================");

        System.out.println("\nPet Health Scores:");
        for (int score : petHealthScores) {
            System.out.print(score + " ");
        }

        System.out.println("\n");

        int left = 2;
        int right = 6;

        int minHealth = segmentTree.rangeMinQuery(left, right);

        System.out.println("Range Query:");
        System.out.println("Minimum Health Score from index "
                + left + " to " + right + " = " + minHealth);

        System.out.println("\nUpdating Health Score...");
        System.out.println("Pet at index 3 health score changed from 60 to 55");

        segmentTree.updateValue(3, 55);

        minHealth = segmentTree.rangeMinQuery(left, right);

        System.out.println("\nAfter Update:");
        System.out.println("Minimum Health Score from index "
                + left + " to " + right + " = " + minHealth);

        System.out.println("\n======================================");
        System.out.println("Time Complexity:");
        System.out.println("Segment Tree Construction : O(n)");
        System.out.println("Range Query              : O(log n)");
        System.out.println("Update Operation         : O(log n)");
        System.out.println("======================================");
    }
}