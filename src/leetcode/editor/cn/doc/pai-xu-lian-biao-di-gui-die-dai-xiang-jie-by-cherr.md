# 1. 题目描述
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

**示例 1:**
```
输入: 4->2->1->3
输出: 1->2->3->4
```
**示例 2:**
```
输入: -1->5->3->4->0
输出: -1->0->3->4->5
```
*注意*：题目要求时间复杂度为**O(nlogn)**,空间复杂度为**O(1)**

# 2.题解

### **A 思考过程**
由时间复杂度可以联想到**归并排序**.
- 对**数组**做归并排序需要的空间复杂度为O(n)-->新开辟数组O(n)+递归调用函数O(logn); 
- 对**链表**做归并排序可以通过修改引用来更改节点位置，因此不需要向数组一样开辟额外的O(n)空间，但是只要是递归就需要消耗log(n)的空间复杂度，要达到O(1)空间复杂度的目标，得使用迭代法。

**因此对于链表进行排序有两种方案：**
（1）递归实现归并排序（空间复杂度不符合要求）
（2）迭代实现归并排序
### B 关键技巧
**(a) 技巧一：通过快慢指针找到链表中点**
需要确定链表的中点以进行两路归并。可以通过快慢指针的方法。快指针每次走两步，慢指针每次走一步。遍历完链表时，慢指针停留的位置就在链表的中点。
以下两种找中点的方式都🉑️
**注：下面图片的快慢指针初始化粗心写错了。。。。应该是：**
    ListNode slow = head;
    ListNode fast = head.next;

![WechatIMG168.jpeg](https://pic.leetcode-cn.com/1601950521-GvSTji-WechatIMG168.jpeg)


```
    ListNode slow = head;
    ListNode fast = head.next; 
    
    while(fast!=null && fast.next!=null){ 
        slow = slow.next; //慢指针走一步
        fast = fast.next.next; //快指针走两步
    }
    ListNode rightHead = slow.next; //链表第二部分的头节点
    slow.next = null; //cut 链表
```
或者这样：
![WechatIMG167.jpeg](https://pic.leetcode-cn.com/1601950547-IAeITN-WechatIMG167.jpeg)


```
    ListNode slow = head; 
    ListNode fast = head; 
    ListNode pre = slow; //前驱指针
    while(fast!=null && fast.next!=null){ 
        pre = slow;
        slow = slow.next; //慢指针走一步
        fast = fast.next.next; //快指针走两步
    }
    ListNode rightHead = slow; //链表第二部分的头节点
    pre.next = null; //cut 链表
```
**（b) 技巧二：断链操作**
`split(l,n)` 即切掉链表l的前n个节点，并返回后半部分的链表头。
比如原来链表是`dummy->1->2->4->3->NULL`
`split(l,2)`的操作造成：
```
dummy->1->2->NULL
4->3->NULL
```

返回4这个后半部分这个链表头～

```
public ListNode split(ListNode head,int step){
        if(head==null)  return null;
        ListNode cur = head;
        //注意这里cur.next!=null 有可能出现后半段还没到规定步长但是走完的情况
        for(int i=1; i<step && cur.next!=null; i++){
            cur = cur.next; 
        }
        ListNode right = cur.next; //right为后半段链表头
        cur.next = null; //切断前半段
        return right; //返回后半段链表头
    }
```
**(c) 技巧三：合并两个有序链表**
```
public ListNode merge(ListNode h1, ListNode h2){
        ListNode head = new ListNode(-1); //新创建一个伪头节点
        ListNode p = head;
        while(h1!=null && h2!=null){
            if(h1.val < h2.val){
                p.next = h1;
                h1 = h1.next;
            }
            else{
                p.next = h2;
                h2 = h2.next;
            }
            p = p.next;           
        }
        //判断哪个链表还有没被比较完的值，直接把p指针指向它
        if(h1!=null)    p.next = h1;
        if(h2!=null)    p.next = h2;

        return head.next;  //返回排序好的链表头    
    }
```
### C.完整代码
明白上面的几个关键技巧，下面上完整代码～
- 递归
```
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

 /* 知识点1：归并排序的整体思想
 * 知识点2：找到一个链表的中间节点的方法
 * 知识点3：合并两个已排好序的链表为一个新的有序链表
 */

class Solution {
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode slow = head; //慢指针
        ListNode fast = head.next; //快指针
        
        while(fast!=null && fast.next!=null){ //快慢指针找到链表中点
            slow = slow.next; //慢指针走一步
            fast = fast.next.next; //快指针走两步
        }
        ListNode rightHead = slow.next; //链表第二部分的头节点
        slow.next = null; //cut 链表
        
        ListNode left = sortList(head); //递归排序前一段链表
        ListNode right = sortList(rightHead); //递归排序后一段链表
        return merge(left,right);
    }  
    public ListNode merge(ListNode h1,ListNode h2){ //合并两个有序链表
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while(h1!=null && h2!=null){
            if(h1.val < h2.val){
                p.next = h1;
                h1 = h1.next;
            }else{
                p.next = h2;
                h2 = h2.next;
            }
            p = p.next;
        }
        if(h1!=null)    p.next = h1;
        else if(h2!=null) p.next = h2;
        return dummy.next;

    }
}
```

- 迭代
```
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
        int length = getLength(head);
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
       
        for(int step = 1; step < length; step*=2){ //依次将链表分成1块，2块，4块...
            //每次变换步长，pre指针和cur指针都初始化在链表头
            ListNode pre = dummy; 
            ListNode cur = dummy.next;
            while(cur!=null){
                ListNode h1 = cur; //第一部分头 （第二次循环之后，cur为剩余部分头，不断往后把链表按照步长step分成一块一块...）
                ListNode h2 = split(h1,step);  //第二部分头
                cur = split(h2,step); //剩余部分的头
                ListNode temp = merge(h1,h2); //将一二部分排序合并
                pre.next = temp; //将前面的部分与排序好的部分连接
                while(pre.next!=null){
                    pre = pre.next; //把pre指针移动到排序好的部分的末尾
                }
            }
        }
        return dummy.next;
    }
    public int getLength(ListNode head){
    //获取链表长度
        int count = 0;
        while(head!=null){
            count++;
            head=head.next;
        }
        return count;
    }
    public ListNode split(ListNode head,int step){
        //断链操作 返回第二部分链表头
        if(head==null)  return null;
        ListNode cur = head;
        for(int i=1; i<step && cur.next!=null; i++){
            cur = cur.next;
        }
        ListNode right = cur.next;
        cur.next = null; //切断连接
        return right;
    }
    public ListNode merge(ListNode h1, ListNode h2){
    //合并两个有序链表
        ListNode head = new ListNode(-1);
        ListNode p = head;
        while(h1!=null && h2!=null){
            if(h1.val < h2.val){
                p.next = h1;
                h1 = h1.next;
            }
            else{
                p.next = h2;
                h2 = h2.next;
            }
            p = p.next;           
        }
        if(h1!=null)    p.next = h1;
        if(h2!=null)    p.next = h2;

        return head.next;     
    }
}
```
