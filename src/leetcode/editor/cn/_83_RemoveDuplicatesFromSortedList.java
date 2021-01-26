package leetcode.editor.cn;
//83
//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表 
// 👍 414 👎 0

import java.util.HashSet;

import static leetcode.editor.cn._148_SortList.createRandomList;
import static leetcode.editor.cn._148_SortList.printList;

public class _83_RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = (new _83_RemoveDuplicatesFromSortedList()).new Solution();
        ListNode head = createRandomList(15,0,15);
        printList(head);
        ListNode node = solution.deleteDuplicates(head);
        printList(node);
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        //注意链表是已经排序好的，重复元素只可能连续出现
        ListNode pre = head;
        ListNode cur = pre;
        while (cur != null) {
            cur = cur.next;
            int curVal = pre.val;
            while (cur != null && curVal == cur.val) {
                cur = cur.next;
            }
            pre.next = cur;
            pre = cur;
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}