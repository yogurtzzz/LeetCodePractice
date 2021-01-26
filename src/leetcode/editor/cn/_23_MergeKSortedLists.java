package leetcode.editor.cn;
//23
//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 堆 链表 分治算法 
// 👍 962 👎 0

public class _23_MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = (new _23_MergeKSortedLists()).new Solution();
        
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        int mid = lists.length / 2;
        return mergeLists(lists, 0, mid, mid + 1, lists.length - 1);
    }

    private ListNode mergeLists(ListNode[] lists, int leftListLeft, int leftListRight, int rightListLeft, int rightListRight) {
        ListNode leftList = null, rightList = null;
        if (leftListLeft < leftListRight) {
            int mid = (leftListLeft + leftListRight) / 2;
            leftList = mergeLists(lists, leftListLeft, mid, mid + 1, leftListRight);
        } else if (leftListLeft == leftListRight) {
            leftList = lists[leftListLeft];
        }

        if (rightListLeft < rightListRight) {
            int mid = (rightListLeft + rightListRight) / 2;
            rightList = mergeLists(lists, rightListLeft, mid, mid + 1, rightListRight);
        } else if (rightListLeft == rightListRight) {
            rightList = lists[rightListLeft];
        }
        return mergeTwoLists(leftList, rightList);
    }


    private ListNode mergeTwoLists(ListNode left, ListNode right) {
        ListNode mergeListPre = new ListNode(0);
        ListNode cur = mergeListPre;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) {
            cur.next = left;
        }
        if (right != null) {
            cur.next = right;
        }
        return mergeListPre.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}