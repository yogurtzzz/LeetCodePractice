package leetcode.editor.cn;
//445
//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 进阶： 
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。 
//
// 
//
// 示例： 
//
// 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 8 -> 0 -> 7
// 
// Related Topics 链表 
// 👍 295 👎 0

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _445_AddTwoNumbersIi {
    public static void main(String[] args) {
        ListNode a = createFromArray(new int[]{7,2,4,3});
        ListNode b = createFromArray(new int[]{5,6,4});
        Solution solution = (new _445_AddTwoNumbersIi()).new Solution();
        ListNode sum = solution.addTwoNumbers(a, b);
        printList(sum);
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


    //最简单的解法：反转2个链表，按位依次相加即可
    //进阶解法：不修改两个链表的结构。先获取2个链表长度，后进行对齐，再进行相加，先不进位，大于10的结果直接保存在某一个节点中，再依次做调整
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //先获取长度
        int size1 = 0, size2 = 0;
        ListNode cur1 = l1, cur2 = l2;
        while (cur1 != null || cur2 != null) {
            if (cur1 != null) {
                size1++;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                size2++;
                cur2 = cur2.next;
            }
        }
        ListNode longList = size1 > size2 ? l1 : l2;
        ListNode shortList = size1 > size2 ? l2 : l1;
        int sizeDiff = Math.abs(size1 - size2);

        //进行对齐
        ListNode preResult = new ListNode(0);
        ListNode pre = preResult;
        while (sizeDiff > 0) {
            pre.next = new ListNode(longList.val);
            pre = pre.next;
            longList = longList.next;
            sizeDiff--;
        }

        boolean needAdjust = false;
        //对齐结束，开始进行相加,直接相加, 不进行进位，每个节点可能存一个大于10的数
        while (longList != null && shortList != null) {
            int sum = longList.val + shortList.val;
            pre.next = new ListNode(sum);
            pre = pre.next;
            longList = longList.next;
            shortList = shortList.next;
            if (sum > 9) needAdjust = true;
        }

        //需要调整
        while (needAdjust) {
            needAdjust = false;
            pre = preResult;
            ListNode cur = pre.next;
            while (cur != null) {
                if (cur.val > 9) {
                    //有进位, 最多进1位
                    cur.val = cur.val - 10;
                    pre.val = pre.val + 1;
                    if (pre.val > 9) {
                        needAdjust = true;
                    }
                }
                pre = pre.next;

                cur = cur.next;
            }
        }
        //若preResult为0, 说明没有发生最高位的进位, 否则，发生了最高位的进位
        return preResult.val == 0 ? preResult.next : preResult;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}