package leetcode.editor.cn;
//817
//给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。 
//
// 同时给定列表 G，该列表是上述链表中整型值的一个子集。 
//
// 返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。 
//
// 
//
// 示例 1： 
//
// 输入: 
//head: 0->1->2->3
//G = [0, 1, 3]
//输出: 2
//解释: 
//链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。 
//
// 示例 2： 
//
// 输入: 
//head: 0->1->2->3->4
//G = [0, 3, 1, 4]
//输出: 2
//解释: 
//链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。 
//
// 
//
// 提示： 
//
// 
// 如果 N 是给定链表 head 的长度，1 <= N <= 10000。 
// 链表中每个结点的值所在范围为 [0, N - 1]。 
// 1 <= G.length <= 10000 
// G 是链表中所有结点的值的一个子集. 
// 
// Related Topics 链表 
// 👍 55 👎 0

import java.util.HashSet;
import java.util.Set;

import static leetcode.editor.cn._148_SortList.createFromArray;

public class _817_LinkedListComponents {
    public static void main(String[] args) {
        Solution solution = (new _817_LinkedListComponents()).new Solution();
        ListNode node = createFromArray(new int[] {0,1,2,3,4});
        int i = solution.numComponents(node, new int[]{0, 3, 1, 4});
        System.out.println(i);
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
    public int numComponents(ListNode head, int[] G) {
        // 简单的解法：创建一个Hash表, 但是这样做的空间复杂度为O(n)，时间复杂度也为O(n)
        //有没有更好的解法？
        Set<Integer> set = new HashSet<>();
        for (int i : G) {
            set.add(i);
        }
        int num = 0;
        ListNode cur = head;
        boolean continues = false;
        while (cur != null && !set.isEmpty()) {
            if (set.contains(cur.val)) {
                if (!continues) {
                    //还未开始一段连续节点
                    num++;
                    continues = true; // 现在开始一段连续节点
                }
            } else {
                //set中不包含该节点, 则连续性断掉
                continues = false;
            }
            cur = cur.next;
        }
        return num;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}