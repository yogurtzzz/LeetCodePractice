package leetcode.editor.cn;
//148
//在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。 
//
// 示例 1: 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2: 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5 
// Related Topics 排序 链表 
// 👍 786 👎 0

import java.util.*;

public class _148_SortList {
    public static void main(String[] args) {

        Solution solution = new Solution();

        int min = 0, max = 1000, size = 100;

        ListNode list = createRandomList(size, min, max);

        ListNode copyList = copyList(list);

//        printList(list);

        //list = solution.sortList(list);

//        printList(list);

        long s1 = System.nanoTime();
        ListNode sortedList = solution.mergeSort(list);
        long s2 = System.nanoTime();
        ListNode sortedList2 = solution.mergeSortNonRecursive(copyList);
        long s3 = System.nanoTime();

        System.out.println("递归耗时：" + (s2 - s1) / 1000 + " us");
        System.out.println("非递归耗时：" + (s3 - s2) / 1000 + " us");
        assertSorted(sortedList);
        assertSorted(sortedList2);
    }

    static void printTreeByLevelTraverse(_109_ConvertSortedListToBinarySearchTree.TreeNode treeRoot) {
        Deque<_109_ConvertSortedListToBinarySearchTree.TreeNode> stack = new LinkedList<>();
        stack.push(treeRoot);
        while (!stack.isEmpty()) {
            _109_ConvertSortedListToBinarySearchTree.TreeNode node = stack.pop();
            System.out.print(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (!stack.isEmpty()) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    static ListNode createFromArray(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode cur = head;
        for (int i = 1; i < array.length; i++) {
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return head;
    }
    /**
     * 拷贝链表
     * **/
    static ListNode copyList(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode copyHead = new ListNode(node.val);
        ListNode cur = copyHead;
        while (node.next != null) {
            node = node.next;
            cur.next = new ListNode(node.val);
            cur = cur.next;
        }
        return copyHead;
    }

    static int getListSize(ListNode head) {
        int listSize = 0;
        while (head != null) {
            listSize++;
            head = head.next;
        }
        return listSize;
    }

    static ListNode getRandomNode(ListNode head) {
        int size = getListSize(head);
        int randomPos = (new Random()).nextInt(size);
        while (randomPos-- > 0) {
            head = head.next;
        }
        return head;
    }

    static ListNode getTail(ListNode head) {
        while (head != null && head.next != null) {
            head = head.next;
        }
        return head;
    }

    /**
     * 创建一个随机单链表
     * @return 单链表的头节点
     * **/
     static ListNode createRandomList(int size, int min, int max) {
         if (size < 1) {
             return null;
         }
        int[] array = createRandomArray(size, min, max);
        //int[] array = {4,2,1,3};
        ListNode[] listNodes = new ListNode[size];
        ListNode next = null;
        for (int i = array.length - 1; i >= 0; i--) {
            listNodes[i] = new ListNode(array[i], next);
            next = listNodes[i];
        }
        return listNodes[0];
    }

    /**
     * 创建一个随机数组
     * **/
    static int[] createRandomArray(int size, int min, int max) {
        int[] array = new int[size];
        int gap = max - min;
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            array[i] = min + random.nextInt(gap);
        }
        return array;
    }

    /**
     * 打印单链表
     * **/
    static void printList(ListNode list) {
        String rightArrow = "->";
        while (list != null) {
            System.out.print(list.val);
            if (list.next != null) {
                System.out.print(rightArrow);
            }
            list = list.next;
        }
        System.out.println();
    }

    static void assertSorted(ListNode list) {
        while (list != null && list.next != null) {
            int preVal = list.val;
            int nextVal = list.next.val;
            if (preVal > nextVal) {
                throw new IllegalStateException("not sorted");
            }
            list = list.next;
        }
    }
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
    public ListNode sortList(ListNode head) {
        // O(nlog(n)) 的时间复杂度，则要求是基于分治思想的排序，那么只可能是 堆排，快排，归并
        // 堆排 ：需要根据下标找父子节点，适用于数组不适用于链表
        // 快排 ：由于是单链表，无法用双向循环法，单向循环则可以考虑。且可以使用递归
        // 归并 ：递归方式，需要获取数组下标以对数组进行分区，故无法用递归；非递归，则可以考虑

        //ListNode newHead = mergeSort(head);

        ListNode newHead = mergeSortNonRecursive(head);

        return newHead;
    }

    // 归并排序 - 递归实现
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 先将链表切断，用双指针找到链表中点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightListHead = slow.next;
        //将链表从中点切断，切断之前先保存右侧链表的头节点
        slow.next = null;
        //先让左右两个链表分别有序
        ListNode list1 = mergeSort(head);
        ListNode list2 = mergeSort(rightListHead);

        //开始合并
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        ListNode remain = list1 != null ? list1 : list2;
        cur.next = remain;
        return res.next;
    }

    /**
     * @return 链表的长度
     * **/
    private int getListSize(ListNode head) {
        int listSize = 0;
        while (head != null) {
            listSize++;
            head = head.next;
        }
        return listSize;
    }

    /**
     * @param head 要切割的链表头节点
     * @param size 要切割的长度
     * @return 剩余部分的链表头节点
     * 比如  1->4->5->2
     * 传入参数 ListNode(1) , 2
     * 则链表从节点1开始，往后数2个位置，进行切割，得到  1->4  ,  5->2
     * 返回 ListNode(5)
     * **/
    private ListNode cutList(ListNode head, int size) {
        //链表长度小于等于1，切不了
        if (head ==null || head.next == null) {
            return null;
        }
        while (--size > 0 && head.next != null) {
            head = head.next;
        }
        ListNode rightList = head.next;
        head.next = null;
        return rightList;
    }

    /**
     * 合并2个链表
     * **/
    private ListNode mergeList(ListNode leftListNode, ListNode rightListNode) {
        ListNode node = new ListNode(0);
        ListNode cur = node;
        while (leftListNode != null && rightListNode != null) {
            if (leftListNode.val <= rightListNode.val) {
                cur.next = leftListNode;
                leftListNode = leftListNode.next;
            } else {
                cur.next = rightListNode;
                rightListNode = rightListNode.next;
            }
            cur = cur.next;
        }
        if (leftListNode != null) {
            cur.next = leftListNode;
        }
        if (rightListNode != null) {
            cur.next = rightListNode;
        }
        return node.next;
    }

    //归并排序 - 非递归实现
    public ListNode mergeSortNonRecursive(ListNode head) {
        //获取链表长度
        int listSize = getListSize(head);
        ListNode node = new ListNode(0, head);
        //开始分区块进行合并
        for (int blockSize = 1; blockSize <= listSize; blockSize *= 2) {
            //这里不能写成 cur = head, 因为head位置移动了，这里cur应该指向当前链表的头
            ListNode cur = node.next;
            ListNode preHead = node;
            while (cur != null) {
                //先分割链表
                ListNode rightNode = cutList(cur, blockSize);
                //再切割一次, 并将剩余部分链表的链表头暂存
                ListNode remainNode = cutList(rightNode, blockSize);
                //合并链表
                ListNode mergeNode = mergeList(cur, rightNode);
                //将合并后的链表往前接上
                preHead.next = mergeNode;
                //更新preHead到已排序的链表的最右侧, 以便下次进行对接
                while (preHead.next != null) {
                    preHead = preHead.next;
                }
                // 对剩余部分继续进行
                cur = remainNode;
            }
        }
        return node.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
