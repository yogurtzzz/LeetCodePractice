package leetcode.editor.cn;
//1171
//给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。 
//
// 删除完毕后，请你返回最终结果链表的头节点。 
//
// 
//
// 你可以返回任何满足题目要求的答案。 
//
// （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。） 
//
// 示例 1： 
//
// 输入：head = [1,2,-3,3,1]
//输出：[3,1]
//提示：答案 [1,2,1] 也是正确的。
// 
//
// 示例 2： 
//
// 输入：head = [1,2,3,-3,4]
//输出：[1,2,4]
// 
//
// 示例 3： 
//
// 输入：head = [1,2,3,-3,-2]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 给你的链表中可能有 1 到 1000 个节点。 
// 对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000. 
// 
// Related Topics 链表 
// 👍 90 👎 0

import java.util.*;

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _1171_RemoveZeroSumConsecutiveNodesFromLinkedList {
    public static void main(String[] args) {

        ListNode node = createFromArray(new int[] {1,2,-3,3,1});
        Solution solution = (new _1171_RemoveZeroSumConsecutiveNodesFromLinkedList()).new Solution();
        ListNode result = solution.removeZeroSumSublists(node);
        printList(result);

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

    // 这种解法貌似性能不太好, 后续看看还有没有更优化的解法
    // 解法
    // 1. 我的解法, 用2个栈, 一个栈存当前节点所有可能的前缀和, 一个栈存所有节点  , 性能不太好
    // 2. 解法2-前缀和: 直接算出每个节点位置的前缀和, 当某2个节点所在位置的前缀和相同, 说明中间一段连续节点的和为0 ，直接移除即可
    // 3. 解法3: 递归思想
    // 4. 解法4: 暴力法 (双循环, 看来暴力法才是最快的?)
    public ListNode removeZeroSumSublists(ListNode head) {
        return solutionBrute(head);
    }


    // 我的解法, 提交耗时 100ms+
    public ListNode mySolutionUse2Stack(ListNode head) {
        // 同样可以用栈来解决, 用2个栈即可
        // 一个栈就单纯的压入节点
        // 另一个栈来表示当前位置连续节点的和的全部可能的情况
        Deque<Set<Integer>> sumChoiceStack = new LinkedList<>();
        Deque<ListNode> nodeStack = new LinkedList<>();

        ListNode preHead = new ListNode(0);
        preHead.next = head;
        // 先将 preHead 压栈 , 这里没有压 preHead 对应的 sumOfChoice , 要特别注意, 后续判断栈为空使用 sumChoiceStack 来判断
        nodeStack.push(preHead);

        // 注意遍历一次后, 需要再次判断是否有连续的和为0的子序列, 所以需要设置一个外层循环, 再设置一个标记遍历
        // 用栈的方式, 其实一次遍历即可, 无需多次遍历
        ListNode cur;
        cur = head;
        ListNode nextCur;
        // 开始遍历
        for (;cur != null;cur = nextCur) {
            nextCur = cur.next;
            if (!sumChoiceStack.isEmpty()) {
                //栈非空时, 只需要查看 sumChoiceStack 的栈顶, 看上一个节点的所有可能的情况, 是否能够和当前节点抵消为0
                int negativeVal = - cur.val;
                Set<Integer> top = sumChoiceStack.peek();
                if (top.contains(negativeVal)) {
                    // 说明有可以抵消的情况存在, 那么开始进行对链表进行删除操作, 并需要更新栈的情况
                    int sum = 0;
                    // 对 nodeStack 开始出栈
                    //注意, 这里的条件不应该是 < , 万一需要加到的和是一个负数, 应该用等于
                    while (sum != negativeVal) {
                        sum += nodeStack.pop().val;
                        // 同步对 sumChoiceStack 进行出栈
                        sumChoiceStack.pop();
                    }
                    // 此时的栈顶, 应当是连续和为0的子序列的左侧第一个节点的前节点
                    // 考虑当连续和为0的子序列的左侧第一个节点是首节点时, 那么其 pre 应为null, 所以还是要在开头加一个preHead
                    ListNode pre = nodeStack.peek();
                    // 进行链表切割
                    pre.next = cur.next;
                    // 注意切断 cur 的 next, 虽然不影响结果, 但应当尽量断干净
                    cur.next = null;
                } else {
                    // 没有可以抵消的情况存在, 那么将当前节点压栈, 并更新当前节点位置的sumOfChoice
                    nodeStack.push(cur);
                    // 当前节点的连续子序列的和的所有情况, 应该是上一个节点的所有情况的值, 加上当前节点的值, 以及当前节点本身的值
                    Set<Integer> curSumOfChoice = new HashSet<>();
                    Integer curVal = cur.val;
                    top.forEach(k -> curSumOfChoice.add(k + curVal));
                    curSumOfChoice.add(curVal);
                    // 压栈
                    sumChoiceStack.push(curSumOfChoice);
                }
            } else {
                // 栈为空, 第一个节点, 则直接压栈
                nodeStack.push(cur);
                sumChoiceStack.push(Collections.singleton(cur.val));
            }
        }

        // 最后对剩余的节点进行特殊处理, 若有单个节点值为0的, 直接删除
        while (!sumChoiceStack.isEmpty()) {
            sumChoiceStack.pop();
            ListNode node = nodeStack.pop();
            if (node.val == 0) {
                // 直接删除该节点
                ListNode pre = nodeStack.peek();
                pre.next = node.next;
                node.next = null;
            }
        }
        return preHead.next;
    }

    // 前缀和解法 , 提交耗时 2ms , 内存消耗 38.4MB
    public ListNode solutionUsePrefixSum(ListNode head) {
        // 使用一个前置节点, 就能解决很多边界问题
        // 同时注意, 前置节点的值设为0, 这也能解决类似[1,-1] 这样的链表(若不引入前置节点, 则前缀和为[1,0] 不会出现相同的前缀和情况)
        // 这样也不需要在遍历时单独对值为0的节点进行判断
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        Map<Integer,ListNode> map = new HashMap<>();
        int sum = 0;
        for (ListNode pre = preHead; pre != null; pre = pre.next) {
            sum += pre.val;
            map.put(sum, pre);
        }
        // 再遍历一次
        sum = 0;
        for (ListNode pre = preHead; pre != null; pre = pre.next) {
            sum += pre.val;
            ListNode node = map.get(sum);
            pre.next = node.next;
        }
        return preHead.next;
    }

    // 递归思路, 提交耗时 2ms , 内存消耗 38MB
    public ListNode solutionRecursive(ListNode head) {
        // 把链表分成两部分, 一部分是链表头,另一部分是剩余部分
        // 假设函数能够移除所有连续和为0的子序列, 则将剩余部分的头节点递归调用, 得到一个不含任何连续和为0子序列的链表
        // 再将右侧链表接上左侧的头节点, 由于右侧链表已经不含连续和为0的序列, 那么接上后的新链表, 若有可能存在连续和为0的序列, 则只可能是从头节点向右累加的情况

        //递归退出条件
        if (head == null || (head.next == null && head.val == 0)) return null;

        //先对头节点右侧的链表进行处理
        //将链表和头节点接上
        head.next = solutionRecursive(head.next);;
        int sum = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            //进行连续累加计算
            sum += cur.val;
            if (sum == 0) {
                return cur.next;
            }
        }
        // 若没有发现有连续和为0的序列, 则直接返回head
        return head;
    }

    // 暴力求解, 直接双层循环, 从每个节点依次往后进行查找, 如果能找到
    // 提交耗时3ms, 内存消耗 38.1MB
    public ListNode solutionBrute(ListNode head) {
        if (head == null) return null;
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        // 暴力求解, 双层循环, 先从第一个节点开始
        ListNode pre = preHead;
        while (pre.next != null) {
            ListNode cur = pre.next;
            if (cur.val == 0) {
                pre.next = cur.next;
            } else {
                int sum = cur.val;
                ListNode next = cur.next;
                while (next != null) {
                    sum += next.val;
                    if (sum == 0) {
                        pre.next = next.next;
                        break;
                    } else {
                        next = next.next;
                    }
                }
                //如果是sum为0, 做了切断, 则pre不用更新, 否则pre需要更新
                // 这里可以用next是否为null来判断是否做过切断, 因为只要做过切断, 会break出来, next一定不为null
                if (next == null) {
                    pre = pre.next;
                }
            }
        }
        return preHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}