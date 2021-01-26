package leetcode.editor.cn;
//86
//给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。 
//
// 你应当保留两个分区中每个节点的初始相对位置。 
//
// 
//
// 示例: 
//
// 输入: head = 1->4->3->2->5->2, x = 3
//输出: 1->2->2->4->3->5
// 
// Related Topics 链表 双指针 
// 👍 273 👎 0

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _86_PartitionList {
    public static void main(String[] args) {
        Solution solution = (new _86_PartitionList()).new Solution();
        ListNode head = createFromArray(new int[] {1,1});
        printList(head);
        ListNode partition = solution.partition(head, 2);
        printList(partition);
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
    //大概思路：从左往右遍历，遇到比x小的，交换到最左侧，并扩大左侧的边界
    public ListNode partition(ListNode head, int x) {
        ListNode preHead = new ListNode(0, head);

        ListNode leftLastNode = preHead;

        //每一轮的循环变量
        ListNode preCur = preHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val >=x) {
                //若比x大，则直接后移
                preCur = cur;
                cur = cur.next;
            } else {
                if (leftLastNode.next == cur) {
                    //若后续第一个就是比x小的，则直接扩大左侧边界即可
                    //一开始对这种情况没有做特殊处理，走下面的流程的话，兼顾这种情况的很不好写
                    leftLastNode = cur;
                    preCur = cur;
                    cur = cur.next;
                    continue;
                }
                //下一轮的cur
                ListNode nextCur = cur.next;
                //若比x小，则交换到最左侧
                //先切割
                preCur.next = cur.next;
                cur.next = leftLastNode.next;
                leftLastNode.next = cur;
                leftLastNode = cur;

                //更新下一轮的cur
                cur = nextCur;
            }
        }
        return preHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}