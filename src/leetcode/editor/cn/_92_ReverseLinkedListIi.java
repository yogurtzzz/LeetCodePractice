package leetcode.editor.cn;
//92
//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。 
//
// 说明: 
//1 ≤ m ≤ n ≤ 链表长度。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL 
// Related Topics 链表 
// 👍 553 👎 0

import static leetcode.editor.cn._148_SortList.createRandomList;
import static leetcode.editor.cn._148_SortList.printList;

public class _92_ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution solution = (new _92_ReverseLinkedListIi()).new Solution();
        ListNode list = createRandomList(10,0,100);
        printList(list);
        ListNode result = solution.reverseBetween(list, 2, 2);
        printList(result);
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode leftPre = null;
        ListNode cur = head;
        //翻转的长度
        int rotateSize = n - m + 1;
        //长度小于等于1，则不用翻转
        if (rotateSize <= 1) {
            return head;
        }
        while (--m > 0) {
            leftPre = cur;
            cur = cur.next;
        }
        //找到了准备开始翻转的节点
        //将这个开始节点暂存，之后用于链接右端剩余部分链表
        ListNode end = cur;
        //设置一个pre
        ListNode preCur = cur;
        cur = cur.next;
        //开始执行翻转
        while (--rotateSize > 0) {
            ListNode next = cur.next;
            cur.next = preCur;
            preCur = cur;
            cur = next;
        }
        //右侧接上
        end.next = cur;
        //左侧接上
        if (leftPre != null) {
            leftPre.next = preCur;
            return head;
        } else {
            //否则，说明从头节点开始翻转
            return preCur;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}