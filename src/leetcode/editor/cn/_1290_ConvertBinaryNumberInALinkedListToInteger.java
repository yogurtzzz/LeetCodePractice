package leetcode.editor.cn;
//1290
//给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。 
//
// 请你返回该链表所表示数字的 十进制值 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [1,0,1]
//输出：5
//解释：二进制数 (101) 转化为十进制数 (5)
// 
//
// 示例 2： 
//
// 输入：head = [0]
//输出：0
// 
//
// 示例 3： 
//
// 输入：head = [1]
//输出：1
// 
//
// 示例 4： 
//
// 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
//输出：18880
// 
//
// 示例 5： 
//
// 输入：head = [0,0]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 链表不为空。 
// 链表的结点总数不超过 30。 
// 每个结点的值不是 0 就是 1。 
// 
// Related Topics 位运算 链表 
// 👍 59 👎 0

import static leetcode.editor.cn._148_SortList.createFromArray;

public class _1290_ConvertBinaryNumberInALinkedListToInteger {
    public static void main(String[] args) {
        ListNode node = createFromArray(new int[]{1,0,1});
        Solution solution = (new _1290_ConvertBinaryNumberInALinkedListToInteger()).new Solution();
        int decimalValue = solution.getDecimalValue(node);
        System.out.println(decimalValue);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        //解法: 两次遍历, 一次求出链表长度, 第二次算出十进制表示; 或者一次遍历做链表反转, 第二次算出十进制表示
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        // 获取完长度以后, 遍历进行计算
        cur = head;
        int result = 0;
        for (int i = size - 1; i >= 0 ; i--) {
            if (cur.val == 1) {
                result += Math.pow(2, i);
            }
            cur = cur.next;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}