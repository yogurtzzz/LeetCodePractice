package leetcode.editor.cn;
//234
//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针 
// 👍 753 👎 0

public class _234_PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = (new _234_PalindromeLinkedList()).new Solution();
        
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
    public boolean isPalindrome(ListNode head) {
        //先找到链表中点，切割，并对右侧链表进行逆序，然后依次比较即可
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow, fast;
        slow = head;
        fast = slow.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow是中点
        ListNode leftList = head;
        ListNode rightList = slow.next;
        // 切断
        slow.next = null;

        //对右侧链表进行逆序
        ListNode lastNode = null;
        while (rightList != null) {
            ListNode rightNode = rightList.next;
            rightList.next = lastNode;
            if (rightNode == null) {
                lastNode = rightList;
                break;
            }
            ListNode next = rightNode.next;
            rightNode.next = rightList;
            lastNode = rightNode;
            rightList = next;
        }
        //开始依次比较
        while (lastNode != null && leftList != null) {
            if (lastNode.val != leftList.val) {
                return false;
            }
            lastNode = lastNode.next;
            leftList = leftList.next;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}