package leetcode.editor.cn;
//64
//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 770 👎 0

import java.util.Arrays;

import static leetcode.util.Utils.create2dArray;

public class _64_MinimumPathSum {
    public static void main(String[] args) {
        Solution solution = (new _64_MinimumPathSum()).new Solution();
        int[][] grid = create2dArray(Arrays.asList(new int[]{1,2,3}, new int[]{4,5,6}));
        int i = solution.minPathSum(grid);
        System.out.println(i);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        // 难得一次提交就AC，耗时2ms,97.53%, 内存41.3MB,27.01%
    public int minPathSum(int[][] grid) {
        // 解法和62,63号题目差不多, 用滚动数组
        int m = grid.length;
        int n = grid[0].length;
        int[] ans = new int[n];
        // 先初始化第一行
        ans[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + grid[0][i];
        }
        // 逐行计算到达每一个格子的步数和
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) ans[j] += grid[i][j]; // 第一列
                else {
                    int min = Math.min(ans[j - 1], ans[j]); // 找出左边和上方二者的较小者
                    ans[j] = min + grid[i][j]; // 计算当前格子的数量
                }
            }
        }
        return ans[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}