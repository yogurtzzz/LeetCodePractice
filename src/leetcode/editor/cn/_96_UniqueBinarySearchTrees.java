package leetcode.editor.cn;
//96
//给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？ 
//
// 示例: 
//
// 输入: 3
//输出: 5
//解释:
//给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3 
// Related Topics 树 动态规划 
// 👍 972 👎 0

public class _96_UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = (new _96_UniqueBinarySearchTrees()).new Solution();
        System.out.println(solution.numTrees(5));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numTrees(int n) {
        return solutionDp(n);
    }

    // 正常的dp求解
    // 设 G(n) 表示长度为n的序列能够构成的二叉搜索树的个数
    // F(i,n) 表示以i为根节点, 长度为n的序列, 能够构成的二叉搜索树的个数
    // 则容易得到 G(n) = F(1,n) + F(2,n) + F(3,n) + .... + F(n,n)
    // F(i,n) = G(i-1) × G(n-i)
    // 则容易得到递推式  G(n) = sum [G(i-1) × G(n-i)] (i从1到n)
    private int solutionDp(int n) {
        int[] g = new int[n + 1];
        g[0] = g[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i ; j++) {
                g[i] += g[j - 1] * g[i - j];
            }
        }
        return g[n];
    }

    // 卡塔兰数
    private int tricky(int n) {
        int arr[] = {1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700, 1767263190};
        return arr[n];
    }


    private int math(int n) {
        // 使用数学公式进行求解
        // 卡塔兰数  C(0) = 1, C(n) = 2*(2*n + 1)/(n+2) * C(n-1)
        // 提示：我们在这里需要用 long 类型防止计算过程中的溢出
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


}