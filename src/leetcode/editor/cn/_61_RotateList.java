package leetcode.editor.cn;
//61
//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL, k = 2
//输出: 4->5->1->2->3->NULL
//解释:
//向右旋转 1 步: 5->1->2->3->4->NULL
//向右旋转 2 步: 4->5->1->2->3->NULL
// 
//
// 示例 2: 
//
// 输入: 0->1->2->NULL, k = 4
//输出: 2->0->1->NULL
//解释:
//向右旋转 1 步: 2->0->1->NULL
//向右旋转 2 步: 1->2->0->NULL
//向右旋转 3 步: 0->1->2->NULL
//向右旋转 4 步: 2->0->1->NULL 
// Related Topics 链表 双指针 
// 👍 355 👎 0

import static leetcode.editor.cn._148_SortList.createRandomList;
import static leetcode.editor.cn._148_SortList.printList;

public class _61_RotateList {
    public static void main(String[] args) {
        Solution solution = (new _61_RotateList()).new Solution();
        ListNode node = createRandomList(3,0,100);
        printList(node);
        ListNode result = solution.rotateRight(node, 3);
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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        //先派一个先行者探路
        ListNode pioneer = head;
        int stepForward = 0;
        int p = k;
        while (p-- > 0) {
            pioneer = pioneer.next;
            stepForward++;
            if (pioneer == null) {
                break;
            }
        }
        ListNode cutPoint;
        if (pioneer == null) {
            //如果pioneer为null，表示k已经大于了链表的长度，则做一个除法，取余数
            int size = stepForward;
            int quotient = k / size; //商
            int remainder = k - (size * quotient); //余数
            if (remainder == 0) {
                return head; //余数为0，说明翻转后和原来的一样，则直接返回
            }
            int stepShouldMove = size - remainder; //需要移动的位数
            cutPoint = head;
            while (--stepShouldMove > 0) {
                cutPoint = cutPoint.next;
            }
            //得到cutPoint
        } else {
            //如果pioneer不为null，表示k小于链表的长度，则同时移动pioneer和head，找到cutPoint
            cutPoint = head;
            while (pioneer.next != null) {
                cutPoint = cutPoint.next;
                pioneer = pioneer.next;
            }
            //得到cutPoint
        }
        //将cutPoint的next指针切断，将后续链表链接到原链表头
        ListNode newHead = cutPoint.next;
        cutPoint.next = null;
        ListNode cur = newHead;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}