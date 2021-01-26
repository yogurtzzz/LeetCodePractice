package leetcode.editor.cn;
//82
//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。 
//
// 示例 1: 
//
// 输入: 1->2->3->3->4->4->5
//输出: 1->2->5
// 
//
// 示例 2: 
//
// 输入: 1->1->1->2->3
//输出: 2->3 
// Related Topics 链表 
// 👍 384 👎 0

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _82_RemoveDuplicatesFromSortedListIi {
    public static void main(String[] args) {
        Solution solution = (new _82_RemoveDuplicatesFromSortedListIi()).new Solution();
        ListNode head = createFromArray(new int[]{1,1,1,2,3});
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
        ListNode preHead = new ListNode(0, head);
        ListNode pre = preHead;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            while (next != null) {
                if (cur.val == next.val) {
                    next = next.next;
                } else {
                    break;
                }
            }
            if (next != cur.next) {
                //说明发生重复，直接跳过所有重复的节点
                //pre不用更新
                pre.next = next;
            } else {
                //没有发生重复，更新pre
                pre = cur;
            }
            cur = next;
        }
        return preHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}