package leetcode.editor.cn;
//70
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1340 👎 0

public class _70_ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = (new _70_ClimbingStairs()).new Solution();
        System.out.println(solution.climbStairs(1));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 从最后一步开始倒推
    // 最后一步无非只有2种情况,
    // 1. 还剩一级楼梯
    // 2. 还剩两级楼梯
    // 设 f(n) 表示爬 n 级楼梯的不同方法的个数
    // 那么  f(n) = f(n - 1) + f(n - 2)
    // 直接递归调用的化, 会超时, 因为做了许多重复的计算
    // 考虑用一个数组来暂存每个f(n), 避免重复计算

    // 优化: 可以采用滚动数组的思想，将原本O(n)的空间复杂度优化为O(1)
    private int[] arr;
    public int climbStairs(int n) {
        // 解法1: 未优化
//        arr = new int[n + 1];
//        return f(n);
        return rollingArray(n);
    }

    private int f(int n) {
        if (n <= 1) return 1;
        if (arr[n] == 0) {
            // 执行计算
            arr[n] = f(n - 1) + f(n - 2);
        }
        return arr[n];
    }

    // 用滚动数组的思想
    // 计算f(n), 只需要保留f(n - 2)和f(n - 1)
    private int rollingArray(int n) {
        // f(0)可以认为是1, f(1)也认为是1
        int pre1 = 0, pre2 = 0, cur = 1;
        for (int i = 0; i < n; i++) {
            pre1 = pre2;
            pre2 = cur;
            cur = pre1 + pre2;
        }
        return cur;
    }


}
//leetcode submit region end(Prohibit modification and deletion)


}