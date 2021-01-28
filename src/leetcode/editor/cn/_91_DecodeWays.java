package leetcode.editor.cn;
//91
//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"111" 可以将 "1" 中的每个 "1" 映射为 "A
//" ，从而得到 "AAA" ，或者可以将 "11" 和 "1"（分别为 "K" 和 "A" ）映射为 "KA" 。注意，"06" 不能映射为 "F" ，因为 "
//6" 和 "06" 不同。 
//
// 给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。 
//
// 题目数据保证答案肯定是一个 32 位 的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
//解释：没有字符映射到以 0 开头的数字。含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。由于没有字符，因此没有有效的方法对此进行
//解码，因为所有数字都需要映射。
// 
//
// 示例 4： 
//
// 
//输入：s = "1"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 606 👎 0

public class _91_DecodeWays {
    public static void main(String[] args) {
        Solution solution = (new _91_DecodeWays()).new Solution();
        int i = solution.numDecodings("1314");
        System.out.println(i);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        return solutionRollingArray(s);
    }

    // 递归解法
    private int solutionRecursive(String s) {
        // 从左往右解码, 一个数字, 要么单独解码, 要么和它的后一个数字组合在一起解码
        // 注意, 若是1个数字单独解码, 有效的数字范围是 1 - 9
        //      若是2个数字组合在一起解码, 左边数字有效范围是1-2, 右边数字有效范围是0-9(当左侧为2时, 有效范围是0-6)
        // 先转换为int数组
        int[] codeArr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            codeArr[i] = s.charAt(i) - '0';
        }
        return count(codeArr, 0, codeArr.length - 1);
    }

    // 设 f(n) 为 从数字第n个位置开始, 到数组末尾, 的解码方法总数
    // 则在当前位置(下标n), 有3种情况
    //  1. 不能单独解码(该位置数字为0)
    //  2. 能单独解码(该位置数字为1-9)
    //  3. 能和后一个数字组合解码(该位置的数和后一个位置的数, 组合起来不超过26)
    // 则 f(n) =
    //            0; (当前位置数字为0)
    //            f(n+1); (当前位置数字只能单独解码, 无法和后一个数组合解码)
    //            f(n+1) + f(n+2); (当前位置数字能单独解码, 也能和后一个数组合解码)
    // 递归解法, 不知道会不会栈溢出或超时
    // 果然超时了
    // 看能不能优化使用类似滚动数组的思想
    // 应该可以, f(n)的计算只依赖与f(n+1)和f(n+2), 而原先的递归解法, 做了很多重复的运算
    private int count(int[] num, int index, int end) {
        if (index > end) return 1;  //超出数组范围, 要理解为什么超出数组范围也算1
        int cur = num[index];
        if (cur == 0) return 0; // 当前位置的数为0, 无法解码
        int mono = count(num, index + 1, end); // 当前数字单独解码
        int dual = 0; // 和后面的数字组合解码
        if (index < end) { // 存在后一个数字时
            int sum = cur * 10 + num[index + 1];
            if (sum <= 26) dual = count(num, index + 2, end); // 组合解码有效
        }
        return mono + dual; // 单独解码 + 组合解码
    }

    // 采用滚动数组思想进行优化
    // 耗时 1ms, 99.93% , 内存 36.6MB, 66.92%
    private int solutionRollingArray(String s) {
        // 只需要3个数即可, 注意初始化时的细节
        int ansN, ansN1, ansN2 = 1;
        ansN = s.charAt(s.length() - 1) - '0' == 0 ? 0 : 1; // 若最后一位是0, 则初始化f(n) = 0
        ansN1 = ansN; // 下面从第二位开始, 所以将 f(n+1)进行更新
        // 从后往前, 从倒数第二个数开始
        for (int i = s.length() - 2; i >= 0; i--) {
            int l = s.charAt(i) - '0';
            int r = s.charAt(i + 1) - '0';
            ansN = l == 0 ? 0 : ansN1; // 单独解码时, f(n) = f(n+1)
            int sum = l * 10 + r;
            if (sum <= 26 && sum >= 10) ansN += ansN2; // 当组合解码有效时 , f(n) = f(n+1) + f(n+2)
            // 更新 f(n+1), f(n+2)
            ansN2 = ansN1;
            ansN1 = ansN;
        }
        return ansN;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}