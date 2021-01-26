package leetcode.editor.cn;
//147
//对链表进行插入排序。 
//
// 
//插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。 
//每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。 
//
// 
//
// 插入排序算法： 
//
// 
// 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。 
// 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。 
// 重复直到所有输入数据插入完为止。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2： 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5
// 
// Related Topics 排序 链表 
// 👍 230 👎 0

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _147_InsertionSortList {
    public static void main(String[] args) {
        ListNode node = createFromArray(new int[]{4,2,1,3});
        Solution solution = (new _147_InsertionSortList()).new Solution();
        ListNode result = solution.insertionSortList(node);

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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preHead = new ListNode(0);
        preHead.next = head;

        //先对下轮循环的cur进行暂存
        ListNode nodeToInsert = head.next; //待插入的节点
        head.next = null; //切断
        while (nodeToInsert != null) {
            ListNode nextNode = nodeToInsert.next; //先暂存下一次要处理的节点
            ListNode pre = preHead;
            ListNode cur = pre.next;
            while (cur != null && cur.val < nodeToInsert.val) {
                pre = pre.next;
                cur = cur.next;
            }
            pre.next = nodeToInsert;
            nodeToInsert.next = cur;
            nodeToInsert = nextNode; //下一个要插入的节点
        }
        return preHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}