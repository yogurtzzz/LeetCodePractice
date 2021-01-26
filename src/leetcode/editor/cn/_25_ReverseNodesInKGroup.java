package leetcode.editor.cn;
//25
//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表 
// 👍 771 👎 0


import static leetcode.editor.cn._148_SortList.createRandomList;
import static leetcode.editor.cn._148_SortList.printList;

public class _25_ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = (new _25_ReverseNodesInKGroup()).new Solution();
        ListNode head = createRandomList(11,0,100);
        printList(head);
        ListNode reverseHead = solution.reverseKGroup(head, 3);
        printList(reverseHead);
        
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode preHead = new ListNode(0);
        preHead.next = head;

        ListNode cur = head;
        //先行者节点
        ListNode pioneer = cur;
        //下面这个pre用于每一轮来连接链表
        ListNode pre = preHead;

        ListNode nextCur;
        do{
            //先派出一个先行者去探路,先行者被置于这k个节点的最后一个节点
            int p = k;
            boolean end = false;
            while (--p > 0) {
                pioneer = pioneer.next;
                if (pioneer == null) {
                    end = true;
                    break;
                }
            }
            //pioneer为null了，说明这一轮不够k个元素了，不用翻转
            if (end) {
                break;
            }
            //进行翻转
            //先将链表切断，切断之前保存下一次开始的节点
            nextCur = pioneer.next;
            //切断链表
            pioneer.next = null;
            //先暂存翻转后的最后一个节点
            ListNode endNode = cur;
            //开始翻转
            ListNode preCur = cur;
            cur = cur.next;
            while (cur != null) {
                //暂存下一次翻转的起始节点
                ListNode next = cur.next;
                //执行翻转
                cur.next = preCur;
                //更新下一轮的cur和preCur
                preCur = cur;
                cur = next;
            }
            //将翻转后的最后一个节点后方切断,不切断的话，第一个节点和第二个节点之间是双向指针，下面pre移动时会导致死循环
            endNode.next = null;
            //翻转完毕后，preCur是最左侧的节点，往左边链接上左侧链表
            pre.next = preCur;
            //将pre移动到当前的最后一个节点
            while (pre.next != null) {
                pre = pre.next;
            }
            //往右先接上右侧剩余链表，以防止下一轮不需要翻转的情况
            pre.next = nextCur;
            //翻转结束，设置下一轮开始的节点
            cur = nextCur;
            pioneer = cur;
        } while (pioneer != null);

        return preHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}