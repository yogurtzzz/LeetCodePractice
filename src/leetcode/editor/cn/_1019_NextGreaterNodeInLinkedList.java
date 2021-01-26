package leetcode.editor.cn;
//1019
//给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。 
//
// 每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.
//val，那么就有 j > i 且 node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0
// 。 
//
// 返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。 
//
// 注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。 
//
// 
//
// 示例 1： 
//
// 输入：[2,1,5]
//输出：[5,5,0]
// 
//
// 示例 2： 
//
// 输入：[2,7,4,3,5]
//输出：[7,0,5,5,0]
// 
//
// 示例 3： 
//
// 输入：[1,7,5,1,9,2,5,1]
//输出：[7,9,9,9,0,5,0,0]
// 
//
// 
//
// 提示： 
//
// 
// 对于链表中的每个节点，1 <= node.val <= 10^9 
// 给定列表的长度在 [0, 10000] 范围内 
// 
// Related Topics 栈 链表 
// 👍 121 👎 0

import java.util.*;

import static leetcode.editor.cn._148_SortList.createFromArray;

public class _1019_NextGreaterNodeInLinkedList {
    public static void main(String[] args) {

        ListNode node = createFromArray(new int[]{1,7,5,1,9,2,5,1});
        Solution solution = (new _1019_NextGreaterNodeInLinkedList()).new Solution();

        int[] ints = solution.nextLargerNodes(node);
        System.out.println(Arrays.toString(ints));
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

    // 感觉也可以用递归的思想去做
    // 通过列举一些例子，发现核心是要寻找一个升序的子序列
    // 不行, 递归的思路还是太复杂了

    // 暴力解法: 时间复杂度O(n^2)
    // 有没有其他更优一些的解法呢？
    // 好像可以利用栈的特性来解决, 只需要一次遍历链表
    // 当遍历到一个节点时，先把它压栈, 之后到下一个节点，先比较节点的值和栈顶的值，若待压栈的节点值比栈顶大，则弹出栈顶, 说明找到这个节点的nextLarger
    // 之后再和新的栈顶比, 若大于栈顶，则弹出栈顶，一直到栈顶的元素大于待插入节点，或栈为空，则把节点压栈
    // 当遍历完所有节点后, 栈中剩余节点依次弹出，这些节点没有nextLargerNode

    //注意，需要同时记录节点的value和它们在链表中的位置(以便待插入的数组下标), 故需要2个栈
    //其实也可以只用一个栈, 不过那种方法的耗时貌似比较高

    // 只用一个栈的思路是, 往栈里直接压Node
    // 然后 map 里存的 key是Node, value是该Node的nextLargerNode的val

    // 这样, 在第一次遍历结束后, 得到的map里包含了所有Node及其对应的nextLargerNode的val
    // 再从头遍历一次链表, 依次从map里获取每个Node对应的nextLargerNode, 填入数组, 即可
    public int[] nextLargerNodes(ListNode head) {
        Deque<Integer> valueStack = new LinkedList<>();
        Deque<Integer> indexStack = new LinkedList<>();
        // 另外需要一个map, 来暂时记录数组的下标和对应的数
        // 遍历完成后才能知道链表大小, 也就才能知道结果数组的大小
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> indexValueMap = new HashMap<>();
        int index = 0;
        while (head != null) {
            //开始进行压栈
            // 当栈非空时, 比较栈顶和当前节点
            // 若栈顶比待处理节点小, 则说明栈顶的元素找到了它的 nextLargerNode, 直接将其弹出, 并将结果记录到map
            // 并循环判断新的栈顶, 直到栈为空, 或者新的栈顶大于待处理节点
            while (!valueStack.isEmpty() && head.val > valueStack.peek()) {
                indexValueMap.put(indexStack.pop(), head.val);
                //弹出 valueStack 的栈顶
                valueStack.pop();
            }
            // 将待处理节点压栈
            valueStack.push(head.val);
            indexStack.push(index);

            // 更新到下一个节点
            head = head.next;
            index++;
        }

        // 此时 indexValueMap 中的 entry 数量, 即是链表长度
        int[] result = new int[index];
        indexValueMap.forEach((key, value) -> result[key] = value);
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}