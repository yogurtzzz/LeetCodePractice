### 方法一：把链表元素存入数组中。先放C++代码，思路清晰明了，详细注释已写好在代码中。
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
        // 特殊条件判断，减少开辟新的数组的几率（减少内存消耗）
        if(!head -> next && head -> val == 0) return nullptr;
        if(!head -> next && head -> val != 0) return head;
        vector<int> store;
        ListNode *pos = head;
        while(pos)
        {
            // 将链表里的值都存到一个数组里
            store.push_back(pos -> val);
            pos = pos -> next;
        }
        for(int i=0; i<store.size(); ++i)
        {
            // 防止数组只有一个元素，所以让sum = 0
            // 如果sum=store[i]，那嵌套循环的j初始就要等于i+1了，那store[j]从一开始就不存在了
            int sum = 0; 
            for(int j=i; j<store.size(); ++j)
            {
                sum += store[j];
                if(sum == 0)
                {
                    // 想删除[i, j]区间的所有元素，但是erase删除的是[i, j)，所以要写成j+1
                    store.erase(store.begin() + i, store.begin() + j + 1);
                    i = -1; // i又从头开始
                    break;
                }
            }
        }
        store.shrink_to_fit(); // 让store的容量capacity与大小size统一一下（可以不写）
        if(store.size() == 0) return nullptr; // 假如store里的元素已经删完了
        pos = head;
        for(int i=0; i<store.size(); ++i)
        {
            pos -> val = store[i];
            if(i + 1 == store.size()) break; // 不要让指针走的步数超出store里的元素个数
            pos = pos -> next;
        }
        pos -> next = nullptr; // 断开链表
        return head;
    }
};
```
### 执行结果截图
![image.png](https://pic.leetcode-cn.com/24c6e52a851be549e263031f59883196d9f688f248a817a299591a6790329339-image.png)
