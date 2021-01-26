package leetcode.editor.cn;
//53
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 2610 👎 0

public class _53_MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = (new _53_MaximumSubarray()).new Solution();
        int i = solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(i);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int maxSubArray(int[] nums) {
        return solutionDynamicProgram(nums);
//        return solutionDivideAndConquer(nums);
    }

    /** 使用动态规划的解法 **/
    //nums的长度若为n, 则其下标的范围是0到n-1
    //具有最大和的连续子数组可能位于数组的任意位置
    //我们设, 以第i个下标为结束元素的最大和为 f(i)
    // 那么f(i)只需要考虑当前元素nums[i], 和以i-1为结束元素的最大和f(i-1)即可
    // f(i) = Max(nums[i], nums[i] + f(i-1))
    // 即f(i)要么就单独一个元素成组, 要么就加上前面以i-1结尾的最大和
    // 那么只有在f(i-1) > 0 时, f(i)才会等于 nums[i] + f(i-1)
    // 实际问题的求解就变成勒  Max(f(0),f(1),f(2),.......,f(n-1))
    // 那么通过一趟遍历, 求解出各个f(i)即可
    // 提交耗时1ms, 内存消耗38.2MB
    private int solutionDynamicProgram(int[] nums) {
        int pre = 0, allMax = nums[0];
        int curMax;
        for (int num : nums) {
            //计算以当前元素为结束元素的最大和
            //这里相当于计算f(i), i为当前元素下标
            curMax = Math.max(pre + num, num);
            pre = curMax; //更新pre, 下次循环时使用
            allMax = Math.max(allMax, curMax); //allMax相当于记录 f(0),f(1)... 当中的最大者
        }
        return allMax;
    }

    //使用分治的思路来解
    //提交耗时 1ms , 内存消耗 39MB
    //线段树
    private int solutionDivideAndConquer(int[] nums) {
        //我们首先定义一个这样的函数
        // get(a , l , r)  表示求解数组a, 在区间[l,r]内的最大子段和
        // 那么最终要求解的是 get(a, 0, size - 1)
        // 我们考虑采用分治，取 m = (l + r) / 2
        // 将 get(a, l, r) 拆解成 get(a, l ,m) 和 get(a, m + 1, r)
        // 这样, 区间[l,r]就被拆成了两半, 我们称 [l,m] 为 左子区间, [m + 1,r]为右子区间
        // 那么问题就变成了, 如何对左右两个子区间的结果, 进行合并
        // 对于 [l,r]区间，我们考虑维护如下的几个变量
        //  - lSum 表示区间[l,r]内以 l为左端点的最大子段的和
        //  - rSum 表示区间[l,r]内以 r为右端点的最大子段的和
        //  - mSum 表示区间[l,r]内的最大子段的和
        //  - iSum 表示区间[l,r]的整个区间和
        return getStatus(nums, 0, nums.length - 1).mSum;
    }

    private Status getStatus(int[] nums, int left, int right) {
        if (left == right) {
            // 若所求的区间只有1个元素的话
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }
        //先将区间拆成两半, 分别对左右区间求 Status
        int mid = (left + right) / 2;
        Status leftStatus = getStatus(nums, left, mid);
        Status rightStatus = getStatus(nums, mid + 1, right);
        // 随后, 对左右区间, 进行合并
        int lSum, rSum, mSum, iSum;
        // lSum要么等于左半区间的 lSum, 要么等于左半区间的 iSum + 右半区间的 lSum
        // rSum 同理
        // mSum 要么等于左半区间的mSum, 要么等于右半区间的mSum, 要么等于左半区间的rSum+右半区间的lSum
        lSum = Math.max(leftStatus.lSum, leftStatus.iSum + rightStatus.lSum);
        rSum = Math.max(rightStatus.rSum, rightStatus.iSum + leftStatus.rSum);
        mSum = Math.max(Math.max(leftStatus.mSum, rightStatus.mSum), leftStatus.rSum + rightStatus.lSum);
        iSum = leftStatus.iSum + rightStatus.iSum;
        return new Status(lSum, rSum, mSum, iSum);
    }

    private class Status {

        private int lSum;
        private int rSum;
        private int mSum;
        private int iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}