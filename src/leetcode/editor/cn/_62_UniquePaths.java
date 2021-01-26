package leetcode.editor.cn;
//62
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 示例 1： 
//
// 
//输入：m = 3, n = 7
//输出：28 
//
// 示例 2： 
//
// 
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
// 
//
// 示例 3： 
//
// 
//输入：m = 7, n = 3
//输出：28
// 
//
// 示例 4： 
//
// 
//输入：m = 3, n = 3
//输出：6 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 109 
// 
// Related Topics 数组 动态规划 
// 👍 864 👎 0

import java.math.BigInteger;

public class _62_UniquePaths {
    public static void main(String[] args) {
        Solution solution = (new _62_UniquePaths()).new Solution();
        System.out.println(solution.uniquePaths(3, 3));  // 6
        System.out.println(solution.uniquePaths(7, 3));  // 28
        System.out.println(solution.uniquePaths(3,2));   // 3
        System.out.println(solution.uniquePaths(10,10));  //48620
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int uniquePaths(int m, int n) {
        // 只能向下或者向右移动一步
        // 则每条路径，都固定需要移动 m + n - 2 次
        // 对于 3 × 3 的格子, 实际计算的是组合数 C2/4
        // 对于最后一步, 只有2种情况, 最后一次需要往下走, 最后一次需要往右走
        // f(m,n) = f(m - 1, n) + f(m, n - 1)
        // 递归可以得到正确答案, 但是会导致栈溢出
        // 其实可以只用一个二维数组 [m,n], 算出从左上角, 到达每个位置的所有路径数即可
        return combination(m,n);
    }

    // 耗时 0ms ,空间 35.3MB
    private int combination(int m, int n) {
        // 其实答案就是排列组合
        // 如 m = 3 , n = 3 , 答案就是C2/4  = (4*3)/(1*2) = 6
        // 如 m = 5, n = 4, 答案就是C3/7 = (7*6*5)/(1*2*3) = 35
        // 如 m = 3, n = 7 , 答案就是C2/8 = (8*7)/(1*2) = 28
        // 但是当输入规模达到 m = 10, n = 10, 算出来的数太大, 会造成溢出, 若使用字符串形式的乘法, 则可以避免
        // 或者考虑, 乘法和除法同时进行, 即可避免整数溢出, 但是要如何保证除法时不出现小数，因为小数会导致整数被截断, 丢失精度
        // 不必担心丢失精度的问题, 连续的2个整数, 一定有一个可以被2整除, 同理, 连续的3个整数，一定有一个被3整除, 连续的n个整数, 一定有一个会被n整除
        if (m > n) {
            int temp = n;
            n = m;
            m = temp;
        }
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }
    // 解法1, 递归, 输入数据规模太大会导致栈溢出, 或超时
    private int recursive(int m,int n) {
        if (m == 1 || n == 1) return 1; // 上边界和左边界
        return recursive(m - 1, n) + recursive(m, n - 1);
    }

    // 解法2: 用一个二维数组 [m,n], 算出从左上角, 到达每个位置的路径数量即可
    // 这个解法时间复杂度很优, 但是空间复杂度有点高
    // 考虑能不能采用类似于滚动数组的方法??
    // 耗时 0ms ,消耗内存 35.3MB, 42.02%
    private int solution2(int m, int n) {
        int[][] arr = new int[m + 1][n + 1];
        // 先填充横竖两条边界的值, 固定为1
        for (int i = 1; i <= n; i++) {
            arr[1][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            arr[i][1] = 1;
        }
        // 再依次填充下面的行(或列)
        for (int i = 2; i <= m ; i++) {
            for (int j = 2; j <= n; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[m][n];
    }

    // 在解法2的基础上优化了一下, 少用了 m + n - 1 个元素的内存
    // 耗时 0ms ,消耗内存 35.2MB, 64.36%
    private int solution3(int m,int n) {
        if (m == 1 || n == 1) return 1;
        int[][] arr = new int[m - 1][n - 1];
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (i == 0 && j == 0) arr[i][j] = 2;
                else if (i == 0) arr[i][j] = 1 + arr[i][j - 1];
                else if (j == 0) arr[i][j] = 1 + arr[i - 1][j];
                else arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[m - 2][n - 2];
    }

    // 优化: 根据 solution3 的解法, 每算完第i行, 则前面的 1 到 i-1 行的数据都没用了
    // 其实用一个一维数组即可
    // 耗时 0ms ,消耗内存 35MB, 96.61%
    private int solution4(int m, int n) {
        if (n == 1 || m == 1) return 1;
        // m × n 的方格
        // 只使用一行的空间, 即 n 个格子, 其实第一个格子不需要, 因为其固定为1, 所以只用n - 1个格子
        int[] arr = new int[n - 1];
        // 第一行时, 初始化全为1
        for (int i = 0; i < n - 1; i++) {
            arr[i] = 1;
        }
        // 依次计算第二行, 第三行, 到第m行
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                // f(i,j) = f(i-1,j) + f(i,j-1)
                // 现在要做的是, 根据上一行，即第i-1行, 来计算第i行
                int left = j == 0 ? 1 : arr[j - 1];
                // f(i,j) = f(i,j-1) + f(i-1,j)
                // 原本的 arr[j]是同一列，上一行的数, 即f(i-1,j)
                // left 即是同一行, 左侧的数, 即f(i,j-1)
                arr[j] = left + arr[j];
            }
        }
        return arr[n - 2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}