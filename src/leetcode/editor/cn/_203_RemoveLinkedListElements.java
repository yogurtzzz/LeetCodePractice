package leetcode.editor.cn;
//203
//删除链表中等于给定值 val 的所有节点。 
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
// Related Topics 链表 
// 👍 473 👎 0

public class _203_RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = (new _203_RemoveLinkedListElements()).new Solution();
        
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
    public ListNode removeElements(ListNode head, int val) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pre = preHead;
        ListNode cur = pre.next;
        while (cur != null) {
            if (val == cur.val) {
                pre.next = cur.next;
                cur.next = null;
                cur = pre.next;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return preHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}