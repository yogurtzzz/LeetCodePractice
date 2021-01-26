package leetcode.editor.cn;
//430
//多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生
//成多级数据结构，如下面的示例所示。 
//
// 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
//输出：[1,2,3,7,8,11,12,9,10,4,5,6]
//解释：
//
//输入的多级列表如下图所示：
//
//
//
//扁平化后的链表如下图：
//
//
// 
//
// 示例 2： 
//
// 输入：head = [1,2,null,3]
//输出：[1,3,2]
//解释：
//
//输入的多级列表如下图所示：
//
//  1---2---NULL
//  |
//  3---NULL
// 
//
// 示例 3： 
//
// 输入：head = []
//输出：[]
// 
//
// 
//
// 如何表示测试用例中的多级链表？ 
//
// 以 示例 1 为例： 
//
//  1---2---3---4---5---6--NULL
//         |
//         7---8---9---10--NULL
//             |
//             11--12--NULL 
//
// 序列化其中的每一级之后： 
//
// [1,2,3,4,5,6,null]
//[7,8,9,10,null]
//[11,12,null]
// 
//
// 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。 
//
// [1,2,3,4,5,6,null]
//[null,null,7,8,9,10,null]
//[null,11,12,null]
// 
//
// 合并所有序列化结果，并去除末尾的 null 。 
//
// [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12] 
//
// 
//
// 提示： 
//
// 
// 节点数目不超过 1000 
// 1 <= Node.val <= 10^5 
// 
// Related Topics 深度优先搜索 链表 
// 👍 135 👎 0

public class _430_FlattenAMultilevelDoublyLinkedList {
    public static void main(String[] args) {
        Solution solution = (new _430_FlattenAMultilevelDoublyLinkedList()).new Solution();

        Node node = createMultiLevelList(new Integer[]{1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12});

        Node flatten = solution.flatten(node);

        System.out.println("0");

    }

    private static Node createMultiLevelList(Integer[] array) {
        Node preHead = new Node(0);
        Node preLevel = preHead;
        Node pre = preHead;
        boolean startSkip = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (!startSkip) {
                    pre.next = new Node(array[i]);
                    pre.next.prev = pre;
                    pre = pre.next;
                } else {
                    pre.child = new Node(array[i]);
                    pre = pre.child;
                    preLevel = new Node(0);
                    preLevel.next = pre;
                    startSkip = false;
                }
            } else {
                if (!startSkip) {
                    pre = preLevel.next;
                    startSkip = true;
                } else {
                    pre = pre.next;
                }
            }
        }
        //首节点处理
        preHead.next.prev = null;
        return preHead.next;
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    //观察发现，扁平化后的链表节点的顺序，符合链表从左到右的深度优先遍历
    //故可以考虑用递归的方式来做
    public Node flatten(Node head) {
        //递归退出条件，当节点为空，或者是最后一个节点时, 直接返回
        if (head == null || (head.next == null && head.child == null)) {
            return head;
        }
        if (head.child != null) {
            //有child, 则先扁平化child, 并先连接child
            Node child = flatten(head.child);
            Node childTail = getTail(child);
            //开始连接, 先连接child, 再在child后面连接next
            Node postTail = head.next;
            head.next = child;
            child.prev = head;
            childTail.next = postTail;
            if (postTail != null) {
                postTail.prev = childTail;
            }
            //将child置空
            head.child = null;
        } else {
            //没child, 则直接扁平化next, 并连接next即可
            Node next = flatten(head.next);
            head.next = next;
            if (next != null) {
                next.prev = head;
            }
        }
        return head;
    }
    private Node getTail(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

  static  class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

      public Node(int val) {
          this.val = val;
      }
  };
}