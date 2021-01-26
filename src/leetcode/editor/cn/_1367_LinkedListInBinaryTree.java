package leetcode.editor.cn;

import leetcode.editor.cn._109_ConvertSortedListToBinarySearchTree.TreeNode;
//1367
//给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。 
//
// 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 
//。 
//
// 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null
//,1,3]
//输出：true
//解释：树中蓝色的节点构成了与链表对应的子路径。
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,nu
//ll,1,3]
//输出：true
// 
//
// 示例 3： 
//
// 输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,
//null,1,3]
//输出：false
//解释：二叉树中不存在一一对应链表的路径。
// 
//
// 
//
// 提示： 
//
// 
// 二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。 
// 链表包含的节点数目在 1 到 100 之间。 
// 二叉树包含的节点数目在 1 到 2500 之间。 
// 
// Related Topics 树 链表 动态规划 
// 👍 63 👎 0

public class _1367_LinkedListInBinaryTree {
    public static void main(String[] args) {
        Solution solution = (new _1367_LinkedListInBinaryTree()).new Solution();
        
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
    // 用递归的方式求解
    // 找到第一个值相等的节点, 然后依次匹配后面的节点
    public boolean isSubPath(ListNode head, TreeNode root) {
        //退出条件, 树的节点搜索完毕, 则为false
        if (root == null) {
            return false;
        }
        // 若找到勒第一个值相等的节点, 则挨个依次匹配后续的节点
        // 若存在多个值相等的节点, 则还要考虑后面的序列
        if (head.val == root.val) {
            return isSubFromStart(head.next, root.left) || isSubFromStart(head.next, root.right)
                    || isSubPath(head, root.left) || isSubPath(head, root.right);
        } else {
            // 此位置不是值相等的位置, 则后移root节点
            return isSubPath(head, root.left) || isSubPath(head, root.right);
        }
    }

    // 从头开始匹配, 是否有一条路径
    private boolean isSubFromStart(ListNode head, TreeNode root) {
        // 若链表节点已经到了最后, 匹配为true
        if (head == null) {
            return true;
        }
        // head不为null, 但是root为null勒, 说明链表节点还未匹配完, 但是树节点用完了, 返回false
        if (root == null) {
            return false;
        }
        // 若当前2个节点值不相等, 则直接返回false
        if (head.val != root.val) {
            return false;
        }
        // 否则, 递归检测下一个子结构
        return isSubFromStart(head.next, root.left) || isSubFromStart(head.next, root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}