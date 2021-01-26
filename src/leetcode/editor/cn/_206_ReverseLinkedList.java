package leetcode.editor.cn;
//206
//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1315 👎 0

public class _206_ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = (new _206_ReverseLinkedList()).new Solution();
        
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

    //递归方式和非递归方式
    public ListNode reverseList(ListNode head) {
        return reverseListRecursive(head);
    }

    private ListNode reverseListRecursive(ListNode head) {
        //递归退出条件
        if (head == null || head.next == null) {
            return head;
        }
        //右侧链表
        ListNode rightList = head.next;
        //切断
        head.next = null;
        //对右侧链表进行翻转
        ListNode rightReverseList = reverseListRecursive(rightList);
        //接上最左端
        rightList.next = head;
        return rightReverseList;
    }

    //非递归实现
    private ListNode reverseListNonRecursive(ListNode head) {
        ListNode lastNode = null;
        ListNode leftNode = head;
        while (leftNode != null) {
            ListNode rightNode = leftNode.next;
            //先接上先前的链表
            leftNode.next = lastNode;
            if (rightNode == null) {
                lastNode = leftNode;
                break;
            }
            ListNode next = rightNode.next;
            rightNode.next = leftNode;
            lastNode = rightNode;
            leftNode = next;
        }
        return lastNode;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}