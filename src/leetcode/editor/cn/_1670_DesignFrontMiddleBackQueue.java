package leetcode.editor.cn;
//1670
//请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。 
//
// 请你完成 FrontMiddleBack 类： 
//
// 
// FrontMiddleBack() 初始化队列。 
// void pushFront(int val) 将 val 添加到队列的 最前面 。 
// void pushMiddle(int val) 将 val 添加到队列的 正中间 。 
// void pushBack(int val) 将 val 添加到队里的 最后面 。 
// int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。 
// int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。 
// int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。 
// 
//
// 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说： 
//
// 
// 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。 
// 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：
//["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", 
//"popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
//[[], [1], [2], [3], [4], [], [], [], [], []]
//输出：
//[null, null, null, null, null, 1, 3, 4, 2, -1]
//
//解释：
//FrontMiddleBackQueue q = new FrontMiddleBackQueue();
//q.pushFront(1);   // [1]
//q.pushBack(2);    // [1, 2]
//q.pushMiddle(3);  // [1, 3, 2]
//q.pushMiddle(4);  // [1, 4, 3, 2]
//q.popFront();     // 返回 1 -> [4, 3, 2]
//q.popMiddle();    // 返回 3 -> [4, 2]
//q.popMiddle();    // 返回 4 -> [2]
//q.popBack();      // 返回 2 -> []
//q.popFront();     // 返回 -1 -> [] （队列为空）
// 
//
// 
//
// 提示： 
//
// 
// 1 <= val <= 109 
// 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。 
//
// 
// Related Topics 设计 链表 
// 👍 3 👎 0

public class _1670_DesignFrontMiddleBackQueue {
    public static void main(String[] args) {
        FrontMiddleBackQueue q = (new _1670_DesignFrontMiddleBackQueue()).new FrontMiddleBackQueue();
        int i = q.popMiddle();
        q.pushMiddle(1);
        q.pushMiddle(2);
        int i1 = q.popMiddle();
        int i2 = q.popMiddle();
        int i3 = q.popMiddle();
        q.pushFront(3);
        q.pushMiddle(4);
        q.pushMiddle(5);
        int i4 = q.popMiddle();
        q.pushBack(6);
        int i5 = q.popMiddle();
        q.pushMiddle(7);
        q.pushMiddle(8);
        int i6 = q.popMiddle();
        q.pushMiddle(9);
        int i7 = q.popBack();
        q.popMiddle();
        q.popMiddle();
        q.popMiddle();
        q.pushMiddle(10);
        int i8 = q.popBack();
        int i9 = q.popMiddle();
        int i10 = q.popMiddle();
        q.pushMiddle(11);
        q.pushMiddle(12);
        q.pushBack(13);
        q.pushFront(14);
        q.pushMiddle(15);
        int i11 = q.popMiddle();
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class FrontMiddleBackQueue {

    private Node head;

    private Node tail;

    private Node mid;

    private int len;

    class Node {
        int val;
        Node next;
        Node prev;
        Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
        Node(int val) {
            this(val, null, null);
        }
    }

    public FrontMiddleBackQueue() {
        len = 0;
    }
    
    public void pushFront(int val) {
        Node newHead = new Node(val);
        if (head == null) {
            // 首次插入
            head = newHead;
            tail = mid = head;
        } else {
            // 非首次插入
            Node oldHead = head;
            head = newHead;
            head.next = oldHead;
            oldHead.prev = head;
            if (len % 2 != 0) {
                // mid前移一位
                mid = mid.prev;
            }
        }
        len++;
    }
    
    public void pushMiddle(int val) {
        Node newMid = new Node(val);
        if (mid == null) head = tail = mid = newMid; // 首次插入
        else {
            // 注意考虑边界情况
            if (len % 2 == 0) {
                // 插在mid后面
                Node nextMid = mid.next;
                mid.next = newMid;
                newMid.prev = mid;
                newMid.next = nextMid;
                if (nextMid != null) nextMid.prev = newMid;
            } else {
                // 插在mid前面
                Node preMid = mid.prev;
                newMid.next = mid;
                mid.prev = newMid;
                newMid.prev = preMid;
                if (preMid != null) preMid.next = newMid;
                if (len == 1) head = newMid; // 边界情况
            }
            mid = newMid;
        }
        len++;
    }
    
    public void pushBack(int val) {
        Node newTail = new Node(val);
        if (tail == null) {
            // 首次插入
            head = tail = mid = newTail;
        } else {
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
            if (len % 2 == 0) {
                // mid 后移
                mid = mid.next;
            }
        }
        len++;
    }
    
    public int popFront() {
        if (len <= 0) return -1; // 队列为空
        // 可能会更新mid和tail
        if (len % 2 == 0) {
            // mid需要后移
            mid = mid.next;
        }
        // 更新head
        Node oldHead = head;
        head = oldHead.next;
        if (head != null) head.prev = null;
        oldHead.next = null;
        if (head == null) mid = tail = null; // 若删除后列表为空了, 同时更新mid和tail
        len--;
        return oldHead.val;
    }
    
    public int popMiddle() {
        if (len <= 0) return -1;
        Node oldMid = mid;
        Node preMid = mid.prev;
        Node nextMid = mid.next;
        if (len % 2 == 0) {
            // mid 后移
            mid = nextMid;
            mid.prev = preMid;
            if (preMid != null) preMid.next = mid;
            if (len == 2) head = mid;
        } else {
            // mid 前移
            mid = preMid;
            if (mid != null) {
                mid.next = nextMid;
                if (nextMid != null) nextMid.prev = mid;
            }
            if (len == 1) head = tail = mid;
        }
        oldMid.next = null;
        oldMid.prev = null;
        len--;
        return oldMid.val;
    }
    
    public int popBack() {
        if (len <= 0) return -1;
        Node oldTail = tail;
        if (len % 2 != 0) {
            // mid前移
            mid = mid.prev;
        }
        tail = tail.prev;
        if (tail != null) tail.next = null;
        oldTail.prev = null;
        len--;
        if (tail == null) head = mid = null;
        return oldTail.val;
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
//leetcode submit region end(Prohibit modification and deletion)


}