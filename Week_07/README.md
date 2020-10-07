学习笔记

###双向BFS代码模板
···
public void deBFS() {
    Set<String> visited = new HashSet<>();
    Set<String> startSet = new HashSet<>();
    startSet.add(begin);
    Set<String> endSet = new HashSet<>();
    endSet.add(end);
    
    while (!startSet.isEmpty() && !endSet.isEmpty()) {
        // 保证每次都从较少元素的一端进行遍历
        if (startSet.size() > endSet.size()) {
            Set<String> temp = endSet;
            endSet = startSet;
            startSet = temp;
        }
        Set<String> tempSet = new HashSet<>();
        
        // process business logic
        // ......
        
        if (endSet.contains(nextLevelWord)) {
            // 两端相遇，结束遍历
            return;
        }
        // 还没访问过该元素
        if (visited.add(element)) {
            tempSet.add(element);
        }
        // 赋值新的startSet
        startSet = tempSet;
}
···