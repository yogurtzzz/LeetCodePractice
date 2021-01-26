package leetcode.editor.cn;
//138
//给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。 
//
// 要求返回这个链表的 深拷贝。 
//
// 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示： 
//
// 
// val：一个表示 Node.val 的整数。 
// random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为 null 。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
//
// 示例 4： 
//
// 输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
// 
//
// 
//
// 提示： 
//
// 
// -10000 <= Node.val <= 10000 
// Node.random 为空（null）或指向链表中的节点。 
// 节点数目不超过 1000 。 
// 
// Related Topics 哈希表 链表 
// 👍 410 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class _138_CopyListWithRandomPointer {
    public static void main(String[] args) {
        Solution solution = (new _138_CopyListWithRandomPointer()).new Solution();
        Node node0 = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node1.random = node0;
        node2.random = node4;
        node3.random = node2;
        node4.random = node0;

        Node copyNode = solution.copyRandomList(node0);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node newNode = copyOrGet(head, map);
        return newNode;
    }


    //如果一个节点在map中不存在，则拷贝这个节点，否则，返回map中的该节点
    private Node copyOrGet(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        //这个get有问题的，因为是根据Node的hashCode或者equals来判断是否是同一个node的
        //所以问题的关键在于，如何判断原有的node和拷贝后的node是同一个node，只需要将key设置为原node，value设为newNode
        Node exist = map.get(node);
        if (exist != null) {
            return exist;
        }
        Node newNode = new Node(node.val);
        //关键在这里！
        //注意key是原node，而结果是newNode
        map.put(node, newNode);
        newNode.next = copyOrGet(node.next, map);
        newNode.random = copyOrGet(node.random, map);
        return newNode;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
static class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
}