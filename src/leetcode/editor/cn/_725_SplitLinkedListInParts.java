package leetcode.editor.cn;
//725
//给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。 
//
// 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。 
//
// 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。 
//
// 返回一个符合上述规则的链表的列表。 
//
// 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ] 
//
// 示例 1： 
//
// 
//输入: 
//root = [1, 2, 3], k = 5
//输出: [[1],[2],[3],[],[]]
//解释:
//输入输出各部分都应该是链表，而不是数组。
//例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.ne
//xt.next.next = null。
//第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
//最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
// 
//
// 示例 2： 
//
// 
//输入: 
//root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
//输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
//解释:
//输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
// 
//
// 
//
// 提示: 
//
// 
// root 的长度范围： [0, 1000]. 
// 输入的每个节点的大小范围：[0, 999]. 
// k 的取值范围： [1, 50]. 
// 
//
// 
// Related Topics 链表 
// 👍 104 👎 0

import static leetcode.editor.cn._148_SortList.createFromArray;
import static leetcode.editor.cn._148_SortList.printList;

public class _725_SplitLinkedListInParts {
    public static void main(String[] args) {
        //Solution solution = (new _725_SplitLinkedListInParts()).new Solution();

        ListNode node = createFromArray(new int[]{1,2,3});
        changeInternal(node);
        printList(node);
        
    }

    private static void changeInternal(ListNode node) {
        node = node.next;
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
    public ListNode[] splitListToParts(ListNode root, int k) {
        //先求出链表长度size
        //然后用size和k相除，得到一个商和余数
        //商就是长度最小部分链表的长度
        //余数从左到右依次给每个部分的链表长度加1
        int size = 0;
        ListNode cur = root;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        int quotient = size / k;
        int remainder = size - quotient * k;
        // 一共有 k 个链表
        ListNode[] resultArray = new ListNode[k];
        int i = 0;
        cur = root;
        while (i < k) {
            //0 到 remainder - 1 ，共remainder个链表，其长度为quotient + 1
            // 后面的链表，长度为 quotient
            ListNode[] r;
            if (i < remainder) r = getPartList(cur, quotient + 1);
            else r = getPartList(cur, quotient);
            resultArray[i] = r[0];
            cur = r[1];
            i++;
        }
        return resultArray;
    }

    //从 start 开始, 截取后 length 长度的 子链表
    private ListNode[] getPartList(ListNode start, int length) {
        ListNode preHead = new ListNode(0);
        ListNode pre = preHead;
        int size = 0;
        while (size < length) {
            pre.next = start;
            pre = pre.next;
            start = start.next;
            size++;
        }
        //节点取完了, 切断
        pre.next = null;
        return new ListNode[]{preHead.next, start};
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}