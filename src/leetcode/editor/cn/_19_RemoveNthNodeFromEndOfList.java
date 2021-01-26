package leetcode.editor.cn;
//19
//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 
//
// 示例： 
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
// 
//
// 说明： 
//
// 给定的 n 保证是有效的。 
//
// 进阶： 
//
// 你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 
// 👍 1081 👎 0

import static leetcode.editor.cn._148_SortList.createRandomList;
import static leetcode.editor.cn._148_SortList.printList;

public class _19_RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = (new _19_RemoveNthNodeFromEndOfList()).new Solution();
        ListNode list = createRandomList(2,0,100);
        printList(list);
        ListNode result = solution.removeNthFromEnd(list, 2);
        printList(result);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode pioneer = head;
        while (n-- > 0) {
            pioneer = pioneer.next;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (pioneer != null) {
            if (pioneer.next == null) {
                pre = cur;
            }
            cur = cur.next;
            pioneer = pioneer.next;
        }
        //最终这个head即是倒数第n个节点
        ListNode nodeToDelete = cur;
        if (pre == null) {
            //说明移除的是头节点
            ListNode newHead = head.next;
            head.next = null;
            return newHead;
        } else {
            pre.next = nodeToDelete.next;
            nodeToDelete.next = null;
            return head;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}