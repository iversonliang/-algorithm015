### 散列思想
能作为数据唯一标识的我们称为键（key）或者关键字，把Key转化为数组下标的映射方法就叫作散列函数（或“Hash 函数”“哈希函数”），而散列函数计算得到的值就叫作散列值（或“Hash 值”“哈希值”）。

### 散列冲突
再好的散列函数也无法避免散列冲突。那究竟该如何解决散列冲突问题呢？我们常用的散列冲突解决方法有两类，开放寻址法（open addressing）和链表法（chaining）。

#### 开放寻址法总结：
当数据量比较小、装载因子小的时候，适合采用开放寻址法。这也是 Java 中的ThreadLocalMap使用开放寻址法解决散列冲突的原因。

#### 链表法总结：
基于链表的散列冲突处理方法比较适合存储大对象、大数据量的散列表，而且，比起开放寻址法，它更加灵活，支持更多的优化策略，比如用红黑树代替链表。

### 如何设计散列函数？
散列函数的设计不能太复杂。过于复杂的散列函数，势必会消耗很多计算时间，也就间接地影响到散列表的性能。
散列函数生成的值要尽可能随机并且均匀分布，这样才能避免或者最小化散列冲突，而且即便出现冲突，散列到每个槽里的数据也会比较平均，不会出现某个槽内数据特别多的情况。


### 如何设计一个工业级的散列表？
##### 工业级的散列表应该具有哪些特性？  
+ 支持快速地查询、插入、删除操作；  
+ 内存占用合理，不能浪费过多的内存空间；  
+ 性能稳定，极端情况下，散列表的性能也不会退化到无法接受的情况。  
##### 如何实现？  
+ 设计一个合适的散列函数；  
+ 定义装载因子阈值，并且设计动态扩容策略；  
+ 选择合适的散列冲突解决方法。  
---

### 二叉树（Binary Tree）
二叉树，顾名思义，每个节点最多有两个子节点，分别是左子节点和右子节点。不过，二叉树并不要求每个节点都有两个子节点，有的节点只有左子节点，有的节点只有右子节点。

### 二叉树的存储
两种方法
+ 一种是基于指针或者引用的二叉链式存储法
+ 一种是基于数组的顺序存储法。

### 二叉树的遍历
经典的方法有三种，前序遍历、中序遍历和后序遍历。  
其中，前、中、后序，表示的是节点与它的左右子树节点遍历打印的先后顺序。

#### 递归遍历
```$xslt
前序遍历的递推公式：
preOrder(r) = print r->preOrder(r->left)->preOrder(r->right)

中序遍历的递推公式：
inOrder(r) = inOrder(r->left)->print r->inOrder(r->right)

后序遍历的递推公式：
postOrder(r) = postOrder(r->left)->postOrder(r->right)->print r
```

#### 遍历的时间复杂度
每个节点最多被访问两次，跟节点个数 n 成正比，遍历的时间复杂度是 O(n)

---

### 二叉查找树（Binary Search Tree）
二叉查找树是二叉树中最常用的一种类型，也叫二叉搜索树。  
为了实现快速查找而生，不仅仅支持快速查找，还支持快速插入、删除一个数据。

#### 要求：
二叉查找树要求，在树中的任意一个节点
+ 其左子树中的每个节点的值，都小于这个节点的值，
+ 其右子树中的每个节点的值，都大于这个节点的值。


### 思考：
散列表的插入、删除、查找操作的时间复杂度可以做到常量级的 O(1)，非常高效。而二叉查找树在比较平衡的情况下，插入、删除、查找操作时间复杂度才是 O(logn)，相对散列表并没有什么优势，那我们为什么还要用二叉查找树呢？

有下面几个原因：  
1. 散列表中的数据是无序存储的，如果要输出有序的数据，需要先进行排序。而对于二叉查找树来说，我们只需要中序遍历，就可以在 O(n) 的时间复杂度内，输出有序的数据序列。
2. 散列表扩容耗时很多，而且当遇到散列冲突时，性能不稳定，尽管二叉查找树的性能不稳定，但是在工程中，我们最常用的平衡二叉查找树的性能非常稳定，时间复杂度稳定在 O(logn)。
3. 尽管散列表的查找等操作的时间复杂度是常量级的，但因为哈希冲突的存在，这个常量不一定比 logn 小，所以实际的查找速度可能不一定比 O(logn) 快。加上哈希函数的耗时，也不一定就比平衡二叉查找树的效率高。
4. 散列表的构造比二叉查找树要复杂，需要考虑的东西很多。比如散列函数的设计、冲突解决办法、扩容、缩容等。平衡二叉查找树只需要考虑平衡性这一个问题，而且这个问题的解决方案比较成熟、固定。
5. 为了避免过多的散列冲突，散列表装载因子不能太大，特别是基于开放寻址法解决冲突的散列表，不然会浪费一定的存储空间。

综合这几点，平衡二叉查找树在某些方面还是优于散列表的，所以，这两者的存在并不冲突。我们在实际的开发过程中，需要结合具体的需求来选择使用哪一个。

---
### 什么是堆
+ 可以迅速找到一堆数据中的最大值或者最小值的数据结构   
二叉堆(Binary)的实现是比较简单的，但是性能相对比较差  
工业上会使用其他的实现方式，如非严格的斐波拉契堆，严格的斐波拉契堆等，都不是二叉的，是多叉的，会有更多的一些特性

### 二叉堆的性质
+ 堆是一个完全二叉树；
+ 堆中每一个节点的值都必须大于等于（或小于等于）其子树中每个节点的值。

### 堆排序的步骤
+ 建堆
+ 排序

#### 建堆
因为叶子节点从上往下堆化只能自己跟自己比较，所以我们直接从第一个非叶子节点开始，往前遍历所有非叶子节点，依次从上往下堆化就行了。

##### 建堆时间复杂度：O(n)

### 排序
过程类似“删除堆顶元素”的操作，当堆顶元素移除之后，我们把下标为 n 的元素放到堆顶，然后再通过堆化的方法，将剩下的 n−1 个元素重新构建成堆。

##### 排序时间复杂度：O(nlogn)

### 总结：
整个堆排序的过程，都只需要极个别临时存储空间，所以堆排序是原地排序算法。

堆排序包括建堆和排序两个操作  
+ 建堆过程的时间复杂度是 O(n)
+ 排序过程的时间复杂度是 O(nlogn)
+ 堆排序整体的时间复杂度是 O(nlogn)

堆排序不是稳定的排序算法，因为在排序的过程，存在将堆的最后一个节点跟堆顶节点互换的操作，所以就有可能改变值相同数据的原始相对顺序。

如果存储从0开始，节点的下标是 i  
左子节点的下标就是 2 ∗ i + 1  
右子节点的下标就是 2 ∗ i + 2  
父节点的下标就是 (i − 1)  / 2​  