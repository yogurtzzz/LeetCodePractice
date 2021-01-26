package leetcode.editor.cn;
//142
//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，po
//s 仅仅是用于标识环的情况，并不会作为参数传递到函数中。 
//
// 说明：不允许修改给定的链表。 
//
// 进阶： 
//
// 
// 你是否可以使用 O(1) 空间解决此题？ 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 104] 内 
// -105 <= Node.val <= 105 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
// Related Topics 链表 双指针 
// 👍 731 👎 0

public class _142_LinkedListCycleIi {
    public static void main(String[] args) {
        Solution solution = (new _142_LinkedListCycleIi()).new Solution();
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        //找出入环点
        //设入环点之前的链表长度为n，快慢指针相遇的位置距入环点距离为Δ1，剩余环的距离为Δ2，则环长c=Δ1+Δ2
        //设慢指针移动了x位，则快指针移动了2x位，可推算得知 x = n + Δ1
        // 2x = n + kc + Δ1 （快指针已经在环里跑了k圈）
        // 两式相减，可得  x = kc，即 n + Δ1 = k(Δ1 + Δ2)
        // 得 n = (k-1)Δ1 + kΔ2
        // 由于现在两个指针的位置在距入环点 Δ1的位置，可知只要慢指针再移动一个 Δ2的距离，即可到达入环点，
        // 即可 慢指针只要再移动 (j+1)Δ2 + jΔ1 ，也即 kΔ2+(k-1)Δ1，即可到达入环点，即慢指针从当前位置再移动n位，即到达入环点
        // 那么，若让一个指针从链表头开始每次移动一位，则也恰好移动n位到入环点
        // 利用新的两个指针相遇的方式，即可找到入环点
        ListNode slow, fast;
        slow = fast = head;
        boolean isCircle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isCircle = true;
                break;
            }
        }
        if (!isCircle) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}