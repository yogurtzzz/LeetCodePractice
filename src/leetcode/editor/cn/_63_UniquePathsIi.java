package leetcode.editor.cn;
//63
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//输出：2
//解释：
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
//
// 示例 2： 
//
// 
//输入：obstacleGrid = [[0,1],[0,0]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// m == obstacleGrid.length 
// n == obstacleGrid[i].length 
// 1 <= m, n <= 100 
// obstacleGrid[i][j] 为 0 或 1 
// 
// Related Topics 数组 动态规划 
// 👍 480 👎 0

import java.util.Arrays;
import java.util.List;

public class _63_UniquePathsIi {
    public static void main(String[] args) {
        Solution solution = (new _63_UniquePathsIi()).new Solution();
        int[][] obstacleGrid = create(Arrays.asList(new int[]{0,0}, new int[]{1,1}, new int[]{0,0}));
        int i = solution.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(i);
    }

    private static int[][] create(List<int[]> list) {
        int m = list.size();
        int n = list.get(0).length;
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            int[] item = list.get(i);
            for (int j = 0; j < n; j++) {
                arr[i][j] = item[j];
            }
        }
        return arr;
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return mysolution2(obstacleGrid);
    }

    // 耗时1ms, 内存 36.4MB
    private int mysolution(int[][] obstacleGrid) {
        // 与 62 题类似, 采用一维数组即可解决
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m == 1 || n == 1) return hasObstacle(obstacleGrid) ? 0 : 1; // 若只有1行或1列, 检查是否有障碍物
        if (obstacleGrid[0][0] == 1) return 0; // 若在起始位置有障碍物, 直接返回不可达
        // 创建一个一维数组
        int[] ans = new int[n - 1];
        // 初始化第一行
        boolean obstacle = false;
        for (int i = 0; i < n - 1; i++) {
            // 只要有一个障碍物, 则第一行后续的所有格子都为0,表示不可达
            if (!obstacle && obstacleGrid[0][i + 1] == 1) obstacle = true;
            ans[i] = obstacle ? 0 : 1;
        }
        // 开始逐行计算
        boolean firstColumnHasObstacle = false;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                // 先判断该行的第一列,是不是有障碍物, 如果第一列有障碍物, 则后续的行的第一列都为不可达
                if (obstacleGrid[i][0] == 1) firstColumnHasObstacle = true;
                if (obstacleGrid[i][j + 1] == 1) {
                    // 若这个位置恰好是障碍物的位置, 更新ans为0, 跳过
                    ans[j] = 0;
                    continue;
                }
                int left = 0;
                if (!firstColumnHasObstacle && j == 0 && obstacleGrid[i][0] != 1) left = 1;
                if (j > 0) left = ans[j - 1];  //第二列以后的列, 直接取左侧一个格子的值
                ans[j] = left + ans[j];
            }
        }
        return ans[n - 2]; // 返回最后一个格子的数
    }

    private int mysolution2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] ans = new int[n];
        ans[0] = obstacleGrid[0][0] == 0 ? 1 : 0; // 到达(0,0)位置, 共有1种路径
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) { // 若该格子本身是障碍物, 则置为0, 表示不可达
                    ans[j] = 0;
                    continue;
                }
                // j = 0 时, f[j]等于上一行的f[j], 无需计算
                // obstacleGrid[i][j-1]是否为0, 不影响
                if (j > 0) ans[j] += ans[j - 1];
            }
        }
        return ans[n - 1];
    }

    // 官方答案, 耗时0ms, 内存36.5MB
    public int solution(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) { // 其实这里只需要 j > 0 即可
                    f[j] += f[j - 1];
                }
            }
        }
        return f[m - 1];
    }

    // 是否有障碍物
    private boolean hasObstacle(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}