package array;

public class ProductOfArrayExceptSelf {
    // S=O(n)
    // T=O(n)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        int[] suff = new int[n];
        int[] res = new int[n];

        int front = 1;
        int back = 1;
        for (int i = 0; i < n; i++) {
            int li = n - 1 - i;
            pre[i] = front;
            front *= nums[i];
            suff[li] = back;
            back *= nums[li];
        }

        for (int i = 0; i < n; i++) {
            res[i] = pre[i] * suff[i];
        }
        return res;
    }

    // S=O(1) Ignore output array
    // T=O(n)
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int r = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= r;
            r *= nums[i];
        }
        return res;

    }
}
