package com.alya.system.user.app.tree;

/**
 * @author alya
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private TreeNode<K, V> root;

    public TreeNode<K, V> getRoot() {
        return root;
    }

    static class TreeNode<K extends Comparable<K>, V> {
        private TreeNode<K, V> parent;
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;
        private boolean color;
        private K key;
        private V value;

        public TreeNode() {
        }

        public TreeNode(TreeNode<K, V> parent, TreeNode<K, V> left, TreeNode<K, V> right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public TreeNode<K, V> getParent() {
            return parent;
        }

        public void setParent(TreeNode<K, V> parent) {
            this.parent = parent;
        }

        public TreeNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<K, V> left) {
            this.left = left;
        }

        public TreeNode<K, V> getRight() {
            return right;
        }

        public void setRight(TreeNode<K, V> right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private TreeNode<K, V> parentOf(TreeNode<K, V> node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    private boolean isRed(TreeNode<K, V> node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    private boolean isBlack(TreeNode<K, V> node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    private void setBlack(TreeNode<K, V> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    private void setRed(TreeNode<K, V> node) {
        if (node != null) {
            node.color = RED;
        }
    }

    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    private void inOrderPrint(TreeNode<K, V> node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.println("key: " + node.key + ", value: " + node.value + ", color: " + (node.color == RED ? "red" : "black"));
            inOrderPrint(node.right);
        }
    }

    public void preOrderPrint() {
        preOrderPrint(this.root);
    }

    private void preOrderPrint(TreeNode<K, V> node) {
        if (node != null) {
            System.out.println("key: " + node.key + ", value: " + node.value + ", color: " + (node.color == RED ? "red" : "black"));
            preOrderPrint(node.left);
            preOrderPrint(node.right);
        }
    }

    public void afterOrderPrint() {
        afterOrderPrint(this.root);
    }

    private void afterOrderPrint(TreeNode<K, V> node) {
        if (node != null) {
            afterOrderPrint(node.left);
            afterOrderPrint(node.right);
            System.out.println("key: " + node.key + ", value: " + node.value + ", color: " + (node.color == RED ? "red" : "black"));
        }
    }

    /**
     *     root                                  root
     *      |                                     |
     *      p                                     r
     *    /  \                                  /  \
     *   l    r         <<< p为支点左旋 <<<     p    rr
     *      /  \                             /  \
     *     rl  rr                           l   rl
     *
     * @param p 旋转支点
     */
    private void leftRotate(TreeNode<K, V> p) {

        TreeNode<K, V> r = p.right;
        TreeNode<K, V> rl = r.left;

        // p的右子节点指向为rl
        p.right = rl;

        // rl的父节点指向p
        if (rl != null) {
            rl.parent = p;
        }

        // 如果p有父节点， r的父节点指向p的父节点，当p在左边r在左边，p在右边则r在右边
        TreeNode<K, V> pp = p.parent;
        if (pp != null) {
            r.parent = pp;
            if (p == pp.left) {
                pp.left = r;
            } else {
                pp.right = r;
            }
        } else {
            // p为根节点，那么r就是根节点
            this.root = r;
            r.parent = null;
        }

        // p的父节点为r，r的左子节点为p
        p.parent = r;
        r.left = p;
    }

    /**
     *       pp                               pp
     *       |                                |
     *       p                                l
     *     /  \                             /  \
     *    l    r       >>> p为支点右旋 >>>  ll   p
     *  /  \                                  /  \
     * ll  lr                                lr   r
     *
     * @param p 旋转支点
     */
    private void rightRotate(TreeNode<K, V> p) {
        TreeNode<K, V> l = p.left;
        TreeNode<K, V> lr = l.right;

        // p的左子节点指向lr
        p.left = lr;

        // lr不是空节点，lr的父节点指向p
        if (lr != null) {
            lr.parent = p;
        }

        // 如果p有父节点，p在左边时l在在左边，右边同理，l的父节点指向p的父节点；
        TreeNode<K, V> pp = p.parent;
        if (pp != null) {
            l.parent = pp;
            if (p == pp.left) {
                pp.left = l;
            } else {
                pp.right = l;
            }
        } else {
            this.root = l;
            l.parent = null;
        }

        p.parent = l;
        l.right = p;
    }

    public void put(K key, V value) {
        TreeNode<K, V> node = new TreeNode<>();
        node.setKey(key);
        node.setValue(value);
        node.setColor(RED);
        put(node);
    }

    private void put(TreeNode<K, V> node) {
        TreeNode<K, V> parent = null;
        TreeNode<K, V> currentNode = this.root;
        // 找位置
        while (currentNode != null) {
            parent = currentNode;
            int i = node.key.compareTo(currentNode.key);
            if (i > 0) {
                // 在右子树
                currentNode = currentNode.right;
            } else if (i == 0) {
                // 替换值
                currentNode.setValue(node.getValue());
                return;
            } else {
                // 在左子树
                currentNode = currentNode.left;
            }
        }
        // 在左边还是右边
        node.parent = parent;
        if (parent != null) {
            int i = node.key.compareTo(parent.key);
            if (i > 0) {
                // 在右子树
                parent.right = node;
            } else {
                // 在左子树
                parent.left = node;
            }
        } else {
            this.root = node;
        }
        // 数平衡修复
        balanceTree(node);
    }

    /**
     * 情景1：空树 -> 插入节点变黑并设置为根节点。
     * 情景2：插入节点已存在 -> 替换值。
     * 情景3：插入节点的父节点为黑色 -> 不做处理。
     *
     * 情景4：插入节点的父节点为红色；（由于根节点为黑色，所以父节点为红色时，必定存在爷爷节点）
     *  |--- 情景4.1：叔叔节点存在并且为红色，那么爷爷节点为黑色 -> 把爷爷节点变成红色，父节点、叔叔节点变成黑色，以爷爷节点为当前节点进行下一步处理。
     *  |--- 情景4.2：叔叔节点不存在或者为黑色，父节点为爷爷节点的左子节点；
     *      |--- 情景4.2.1：插入节点为左子节点，形成LL双红情况 -> 将父节点变黑，爷爷节点变红，以爷爷节点为支点进行右旋。
     *      |--- 情景4.2.2：插入节点为右子节点，形成LR双红情况 -> 以父节点为支点左旋，结果为情景4.2.1，以父节点为当前节点进行下一步处理。
     *  |--- 情景4.3：叔叔节点不存在或者为黑色，父节点为爷爷节点的右子节点；
     *      |--- 情景4.3.1：插入节点为左子节点，形成RL双红情况 -> 将父节点变黑，爷爷节点变红，以爷爷节点为支点进行左旋。
     *      |--- 情景4.3.2：插入节点为左子节点，形成RR双红情况 -> 以父节点为支点右旋，结果为情景4.3.1，以父节点为当前节点进行下一步处理。
     *
     * @param node node
     */
    private void balanceTree(TreeNode<K, V> node) {
        setBlack(this.root);
        
        TreeNode<K, V> parent = parentOf(node);
        TreeNode<K, V> grandpa = parentOf(parent);

        if (parent != null && isRed(parent)) {
            // 情景4：插入节点的父节点为红色；（由于根节点为黑色，所以父节点为红色时，必定存在爷爷节点）
            TreeNode<K, V> uncle = null;
            if (parent == grandpa.left) {
                uncle = grandpa.right;
                // 情景4.1：叔叔节点存在并且为红色，那么爷爷节点为黑色 -> 把爷爷节点变成红色，父节点、叔叔节点变成黑色，以爷爷节点为当前节点进行下一步处理。
                if (uncle != null && isRed(uncle)) {
                    setRed(grandpa);
                    setBlack(parent);
                    setBlack(uncle);
                    balanceTree(grandpa);
                    return;
                }
                // 情景4.2：叔叔节点不存在或者为黑色，父节点为爷爷节点的左子节点；
                if (uncle == null || isBlack(uncle)) {
                    // 情景4.2.1：插入节点为左子节点，形成LL双红情况 -> 将父节点变黑，爷爷节点变红，以爷爷节点为支点进行右旋。
                    if (node == parent.left) {
                        setBlack(parent);
                        setRed(grandpa);
                        rightRotate(grandpa);
                    }
                    // 情景4.2.2：插入节点为右子节点，形成LR双红情况，结果为情景4.2.1，以父节点为当前节点进行下一步处理。
                    if (node == parent.right) {
                        leftRotate(parent);
                        balanceTree(parent);
                        return;
                    }
                }
            } else {
                uncle = grandpa.left;
                // 情景4.1：叔叔节点存在并且为红色，那么爷爷节点为黑色 -> 把爷爷节点变成红色，父节点、叔叔节点变成黑色，以爷爷节点为当前节点进行下一步处理。
                if (uncle != null && isRed(uncle)) {
                    setRed(grandpa);
                    setBlack(parent);
                    setBlack(uncle);
                    balanceTree(grandpa);
                    return;
                }
                // 情景4.3：叔叔节点不存在或者为黑色，父节点为爷爷节点的右子节点；
                if (uncle == null || isBlack(uncle)) {
                    // 情景4.3.1：插入节点为左子节点，形成RL双红情况 -> 将父节点变黑，爷爷节点变红，以爷爷节点为支点进行左旋。
                    if (node == parent.left) {
                        rightRotate(parent);
                        balanceTree(parent);
                        return;
                    }
                    // 情景4.3.2：插入节点为左子节点，形成RR双红情况，结果为情景4.3.1，以父节点为当前节点进行下一步处理。
                    if (node == parent.right) {
                        setBlack(parent);
                        setRed(grandpa);
                        leftRotate(grandpa);
                    }
                }
            }
        }
    }

    public TreeNode<K, V> getNode(K key) {
        return getVal(key);
    }

    public V get(K key) {
        TreeNode<K, V> node = getVal(key);
        return node == null ? null : node.getValue();
    }

    private TreeNode<K, V> getVal(K key) {
        TreeNode<K, V> node = this.root;
        while (node != null) {
            int i = key.compareTo(node.key);
            if (i > 0) {
                node = node.right;
            } else if (i == 0) {
                return node;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public int size() {
        return getSize(this.root);
    }

    private int getSize(TreeNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        int ls = getSize(node.left);
        int rs = getSize(node.right);
        return ls + rs + 1;
    }

}
