package leetcode.editor.cn;
//328
//给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。 
//
// 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，杂度应为时间复 O(nodes)，nodes 为节点总数。
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 1->3->5->2->4->NULL
// 
//
// 示例 2: 
//
// 输入: 2->1->3->5->6->4->7->NULL 
//输出: 2->3->6->7->1->5->4->NULL 
//
// 说明: 
//
// 
// 应当保持奇数节点和偶数节点的相对顺序。 
// 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。 
// 
// Related Topics 链表 
// 👍 265 👎 0

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _328_OddEvenLinkedList {
    public static void main(String[] args) {
        ListNode head = createFromArray(new int[]{2,1,3,5,6,4,7});
        Solution solution = (new _328_OddEvenLinkedList()).new Solution();
        ListNode node = solution.oddEvenList(head);
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

    //用双指针，两个指针一起做切割和连接
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode oddPreHead = new ListNode(0);
        ListNode evenPreHead = new ListNode(0);
        oddPreHead.next = head;
        evenPreHead.next = head.next;
        ListNode curOdd = oddPreHead.next;
        ListNode curEven = evenPreHead.next;
        ListNode lastOdd = oddPreHead;
        ListNode lastEven = evenPreHead;

        while (curOdd != null || curEven != null) {
            ListNode nextOdd = null;
            ListNode nextEven = null;
            //要先一起备份，再一起移动
            if (curOdd != null && curOdd.next != null) nextOdd = curOdd.next.next;
            if (curEven != null && curEven.next != null) nextEven = curEven.next.next;

            //先接上前面
            lastOdd.next = curOdd;
            lastEven.next = curEven;
            //切断, 并更新lastNode
            if (curOdd != null) { curOdd.next = null; lastOdd = curOdd;}
            if (curEven != null) { curEven.next = null; lastEven = curEven;}
            //更新迭代变量
            curOdd = nextOdd;
            curEven = nextEven;
        }
        //将左侧的奇数节点链表和右侧偶数节点链表连接起来
        lastOdd.next = evenPreHead.next;
        return oddPreHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}