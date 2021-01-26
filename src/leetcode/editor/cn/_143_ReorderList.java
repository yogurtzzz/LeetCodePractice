package leetcode.editor.cn;
//143
//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表 
// 👍 435 👎 0

import static leetcode.editor.cn._148_SortList.*;

public class _143_ReorderList {
    public static void main(String[] args) {
        Solution solution = (new _143_ReorderList()).new Solution();
        ListNode node = createFromArray(new int[]{1,2,3,4,5});
        printList(node);
        solution.reorderList(node);
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return ;
        }
        // 设链表长度为n + 1
        // 则节点从0，1，2，3.... 一直到n
        // 设最后一次交换的是节点x
        // 则结束的条件为 x + 2 >= n - x
        // 比如 [0,1,2,3] ，则n为3 ，最后一次交换是L1（L1->L2），即x=1
        // L0->L3->L1->L2
        // 比如 [0,1,2,3,4]，则n为4，最后一次交换是L1（L1->L3），即x=1
        // L0->L4->L1->L3->L2
        // [0,1,2,3,4,5]
        // L0->L5->L1->L4->L2->L3
        // 最后一次交换，左右两个节点要么是 x 和 x + 1，要么是 x 和 x + 2
        // 当链表长度为奇数时，重排后的链表尾部是 x 和 x + 2，且其后还有一个节点为 x + 1
        // 当链表长度为偶数时，重排后的链表尾部是 x 和 x + 1

        //观察发现，可以将这个链表看成2个链表合并后的结果
        // 一个是 L1->L2->L3....   这个链表的终点就是slow指针
        // 另一个是 Ln->Ln-1->Ln-2...

        //那么，只要先找到中点，然后切割成左右两部分，对右侧部分进行逆序
        // 在将2个链表合并即可
        ListNode slow, fast;
        slow = head;
        fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode leftList = head;
        ListNode rightList = slow.next;

        //从slow节点切断
        slow.next = null;

        //将右侧链表逆序
        ListNode preCur = rightList;
        ListNode cur = preCur.next;
        preCur.next = null;
        while (cur != null) {
            ListNode nextCur = cur.next;
            cur.next = preCur;
            preCur = cur;
            cur = nextCur;
        }
        rightList = preCur;

        //合并链表
        ListNode preHead = new ListNode(0);
        ListNode pre = preHead;
        boolean getLeft = true;
        while (leftList != null && rightList != null) {
            if (getLeft) {
                pre.next = leftList;
                leftList = leftList.next;
                getLeft = false;
            } else {
                pre.next = rightList;
                rightList = rightList.next;
                getLeft = true;
            }
            pre = pre.next;
        }
        if (leftList != null) {
            pre.next = leftList;
        }
        if (rightList != null) {
            pre.next = rightList;
        }
        head = preHead.next;
        //printList(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}