package leetcode.editor.cn;
//24
//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
// Related Topics 链表 
// 👍 722 👎 0

import static leetcode.editor.cn._148_SortList.createRandomList;
import static leetcode.editor.cn._148_SortList.printList;

public class _24_SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = (new _24_SwapNodesInPairs()).new Solution();
        ListNode list = createRandomList(1,0,100);
        printList(list);
        ListNode result = solution.swapPairs(list);
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
    public ListNode swapPairs(ListNode head) {
        ListNode preHead = new ListNode(0, head);
        ListNode preCur = preHead;
        ListNode cur = head;
        while (cur != null) {
            ListNode pre = cur;
            ListNode post = cur.next;
            if (post == null) {
                cur = null;
            } else {
                cur = post.next;
            }
            //接上去
            preCur.next = swapPairs(pre, post);
            //更新preCur
            while (preCur.next != null) {
                preCur = preCur.next;
            }
        }
        return preHead.next;
    }

    //交换2个链表节点，并切断
    private ListNode swapPairs(ListNode pre, ListNode post) {
        if (post == null) {
            return pre;
        }
        post.next = pre;
        pre.next = null;
        return post;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}