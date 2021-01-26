### 解题思路
解题思路见下面注解😁

### 代码

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 方法1：栈 + 哈希映射表
     * 1. 因为每个结点地址是唯一的，所以可以作为键存入到map，这样获得的对照关系就是唯一且全部都包含
     * 2. 栈的操作，遍历链表：
     *      a. 如果栈空，结点入栈
     *      b. 栈顶结点值 小于 遍历结点值，说明出现下一个更大元素，将对应关系存入map中，再将栈顶元素抛出；
     *         循环往复，将所有满足条件的键值对都存入map中
     *      c. 其他情况，直接将遍历结点入栈
     * 3. 链表遍历完，剩下在栈中的结点，就是没有下一个更大元素的，直接依次存入map中
     * 4. 接着再遍历一遍链表，把对应关系一一存入到数组中，返回即可
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        Map<ListNode, Integer> map = new HashMap<>();
        Stack<ListNode> stack = new Stack<>();
        int count = 0;
        ListNode tempLi = head;
        while(tempLi != null) {
            if(stack.empty()) {
                stack.push(tempLi);
            } else if(stack.peek().val < tempLi.val) {
                while(!stack.empty() && stack.peek().val < tempLi.val) {
                    map.put(stack.peek(), tempLi.val);
                    stack.pop();
                }
                stack.push(tempLi);
            } else {
                stack.push(tempLi);
            }
            count++;
            tempLi = tempLi.next;
        }
        while(!stack.empty()) {
            map.put(stack.peek(), 0);
            stack.pop();
        }
        tempLi = head;
        int[] result = new int[count];
        int i = 0;
        while(tempLi != null) {
            result[i++] = map.get(tempLi);
            tempLi = tempLi.next;
        }
        return result;
    }
}
```