### 解题思路
递归：分为2部分，头元素和待删去总和值为零的链表，从头开始累加和，返回累加和为零的最后一个元素的next记为结果链表的首节点

### 代码

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* removeZeroSumSublists(ListNode* head) {
        if(!head){
            return nullptr;
        }
        ListNode* Next = removeZeroSumSublists(head->next);
        head->next = Next;
        ListNode* tmp = head;
        long long value = 0;
        while(tmp){
            value += tmp->val;
            if(0==value){
                return tmp->next;
            }
            tmp = tmp->next;
        }
        return head;
       
    }
};
```