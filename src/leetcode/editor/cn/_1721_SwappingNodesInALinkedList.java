package leetcode.editor.cn;
//1721
//给你链表的头节点 head 和一个整数 k 。 
//
// 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
//输出：[7,9,6,6,8,7,3,0,9,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：head = [1,2], k = 1
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：head = [1,2,3], k = 2
//输出：[1,2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目是 n 
// 1 <= k <= n <= 105 
// 0 <= Node.val <= 100 
// 
// Related Topics 链表 
// 👍 6 👎 0

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _1721_SwappingNodesInALinkedList {
    public static void main(String[] args) {
        Solution solution = (new _1721_SwappingNodesInALinkedList()).new Solution();
        ListNode listNode = createFromArray(new int[]{7, 9, 6, 6, 7, 8, 3, 0, 9, 5});
        ListNode node = solution.swapNodes(listNode, 5);
        printList(node);
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
    public ListNode swapNodes(ListNode head, int k) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 有可能k的值超过了链表长度的一半, 则取其补值len - 1 - k
        // 比如链表长度为6,则k取1和取6的效果是一样的, 取2和取5的效果是一样的
        int complement = len + 1 - k;
        // 选取小于链表长度一半的值
        int min = Math.min(k, complement);
        int delta = len - 2 * min + 1; // 2个待交换节点的间隔
        if (delta == 0) return head; // 若值为0, 说明2个节点是同一个, 无需交换
        ListNode preHead = new ListNode(1, head);
        ListNode preA, preB;
        preA = preB = preHead;
        // preB先移动delta的距离
        while (delta-- > 0) {
            preB = preB.next;
        }
        // preA和preB一起移动
        while (--min > 0) {
            // 由于1表示第一个节点, 无需移动, 所以用 --min , 而不是 min--
            preA = preA.next;
            preB = preB.next;
        }
        ListNode nodeA = preA.next;
        ListNode nodeB = preB.next;
        ListNode nextA = nodeA.next;
        ListNode nextB = nodeB.next;
        preA.next = nodeB;
        if (nextA == nodeB) {
            // 2个节点相邻
            nodeB.next = nodeA;
        } else {
            nodeB.next = nextA;
            preB.next = nodeA;
        }
        nodeA.next = nextB;
        return preHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}