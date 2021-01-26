package leetcode.editor.cn;
//1669
//给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。 
//
// 请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。 
//
// 下图中蓝色边和节点展示了操作后的结果： 
//
// 请你返回结果链表的头指针。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
//输出：[0,1,2,1000000,1000001,1000002,5]
//解释：我们删除 list1 中第三和第四个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
// 
//
// 示例 2： 
//
// 
//输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,100
//0003,1000004]
//输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
//解释：上图中蓝色的边和节点为答案链表。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= list1.length <= 104 
// 1 <= a <= b < list1.length - 1 
// 1 <= list2.length <= 104 
// 
// Related Topics 链表 
// 👍 4 👎 0

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _1669_MergeInBetweenLinkedLists {
    public static void main(String[] args) {
        Solution solution = (new _1669_MergeInBetweenLinkedLists()).new Solution();
        ListNode list1 = createFromArray(new int[]{0, 1, 2, 3, 4, 5});
        ListNode list2 = createFromArray(new int[]{1000000,1000001,1000002});
        ListNode list = solution.mergeInBetween(list1, 3, 4, list2);
        printList(list);
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
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode pre1 = new ListNode(-1, list1);
        ListNode preA, nodeB;
        preA = pre1;
        int delta = b - a;
        while (a-- > 0) {
            preA = preA.next;
        }
        nodeB = preA.next;
        while (delta-- > 0) {
            nodeB = nodeB.next;
        }
        // 最后得到 preA 和  nodeB
        // 拼接
        preA.next = list2;
        ListNode cur2 = list2;
        // 获取 list2 的最后一个节点
        while (cur2.next != null) {
            cur2 = cur2.next;
        }
        cur2.next = nodeB.next;
        return list1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}