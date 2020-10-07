package first;

public class FindCircleNum_547 {

    public class UnionFind {
        private int count = 0;
        // 类似于族谱，记录了每一个元素的父亲是谁
        private int[] parent;
        public UnionFind(int count) {
            this.count = count;
            this.parent = new int[count];
            for (int i = 0; i < count; i++) {
                // 初始化的时候，直接将元素自己设为自己的父亲
                parent[i] = i;
            }
        }

        // 查询一次会将部分查询路径的节点上提一层，不是一次压缩完路径
        // 上提的节点是从当前开始，每隔一层的节点才操作一次
        // 如果原本的父亲就是根节点的元素，不会受影响
        public int find2(int p) {
            while (p != parent[p]) {
                // parent[p]相当于查询当前元素的父亲是谁
                // parent[parent[p]]相当于当前元素的爷爷
                // 将当前元素的父亲设为爷爷元素，相当于路径网上提了一个节点
                // 循环结束后最终挂到根节点，就完成了路径压缩
                parent[p] = parent[parent[p]];
                // 修改当前查找的p指针为父亲（原本的爷爷），下一轮循环继续往上查找
                p = parent[p];
            }
            return p;
        }

        public int find3(int p) {
            if (p == parent[p]) {
                return p;
            }
            return parent[p] = find3(parent[p]);
        }

        // 找到根节点，并直接完成路径压缩
        public int find(int p) {
            int root = p;
            while (root != parent[root]) {
                root = parent[root];
            }
            while (p != parent[p]) {
                int temp = parent[p];
                parent[p] = root;
                p = temp;
            }
            return root;
        }

        public void union(int p, int q) {
            int rootP = find3(p);
            int rootQ = find3(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                count--;
            }
        }
    }

    public int findCircleNum(int[][] M) {
        UnionFind unionFind = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.count;
    }

    public static void main(String[] args) {
        FindCircleNum_547 findCircleNum547 = new FindCircleNum_547();
        int[][] M = {
                {1,1,0},
                {1,1,0},
                {0,0,1}
        };
        System.out.println(findCircleNum547.findCircleNum2(M));
    }

    public int findCircleNum2(int[][] M) {
        int count = 0;
        int[] visited = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    public static void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
}
