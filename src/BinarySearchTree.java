public class BinarySearchTree<K extends Orderable<K>, T extends Keyed<K>> {

    private class TreeNode {
        T data;
        TreeNode left, right;
        TreeNode(T d) { data = d; }
    }

    private TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(T item) {
        root = addNode(root, new TreeNode(item));
    }

    private TreeNode addNode(TreeNode root, TreeNode newNode) {
        if (root == null) {
            return newNode;
        }

        if (newNode.data.getKey().isLessThan(root.data.getKey())) {
            root.left = addNode(root.left, newNode);
        } else {
            root.right = addNode(root.right, newNode);
        }

        return root;
    }

    public T find(K key) {
        TreeNode node = findNode(root, key);
        if (node == null) {
            return null;
        }
        return node.data;
    }

    private TreeNode findNode(TreeNode root, K key) {
        if (root == null) {
            return null;
        }

        if (key.match(root.data.getKey())){
            return root;
        }

        if (key.isLessThan(root.data.getKey())){
            return findNode(root.left, key);
        } else {
            return findNode(root.right, key);
        }
    }

    private void findLeftmost(TreeNode start, Stack<TreeNode> stack) {
        TreeNode node = start;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public class Iterator {
        private Stack<TreeNode> stack;

        public Iterator() {
            stack = new Stack<>(2000);
            findLeftmost(root, stack);
        }

        public T next() {
            TreeNode node = stack.pop();
            if (node == null) {
                return null;
            }
            findLeftmost(node.right, stack);
            return node.data;
        }
    }
}