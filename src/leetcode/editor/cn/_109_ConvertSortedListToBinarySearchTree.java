package leetcode.editor.cn;
//109
//给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。 
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定的有序链表： [-10, -3, 0, 5, 9],
//
//一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 深度优先搜索 链表 
// 👍 391 👎 0

import sun.reflect.generics.tree.Tree;

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _109_ConvertSortedListToBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = (new _109_ConvertSortedListToBinarySearchTree()).new Solution();
        ListNode listNode = createFromArray(new int[]{-10,-3,0,5,9});
        printList(listNode);

        TreeNode treeNode = solution.sortedListToBST(listNode);

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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    //将一个升序单链表，转换位一棵平衡二叉搜索树
    //发现，升序单链表的顺序，即是平衡二叉树的前序遍历  ×  好像并不是    诶 好像就是 √  ->  是中序遍历
    // 那么问题就转变成了，如何将一棵树的中序遍历得到的链表，还原成树
    // 只根据中序遍历，是无法完全确定一棵树的，所以可以有多个答案
    // 但是题目规定了，需要时严格平衡的二叉树，加上这条限制后，可能的答案个数就非常有限了
    // 那么只要保证左右两边子树的个数尽可能相等即可
    // 那么取最中间的点作为根节点，划分为左右两边后，继续取最中间的点作为根节点即可
    // 可用递归的思路，就非常简单了
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        //先找到中点，作为根节点，然后进行链表切割，后对左右两侧链表做相同操作即可
        ListNode slow, fast, preSlow;
        preSlow = slow = fast = head;
        while (fast != null && fast.next != null) {
            if (slow != head) {
                preSlow = preSlow.next;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //找到中点，中点为slow
        TreeNode root = new TreeNode(slow.val);
        //暂存左右两个链表的链表头
        ListNode leftStart = head;
        ListNode rightStart = slow.next;
        //切割
        preSlow.next = null;
        slow.next = null;
        TreeNode leftNode = sortedListToBST(leftStart);
        TreeNode rightNode = sortedListToBST(rightStart);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
public static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
}

}