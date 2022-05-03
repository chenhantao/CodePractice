package cht.leetcode.java.code116;

import java.util.ArrayDeque;
import java.util.Deque;

import cht.leetcode.java.Node;

/**
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 *输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 * 示例 2:
 *
 * 输入：root = []
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 树中节点的数量在 [0, 212 - 1] 范围内
 * -1000 <= node.val <= 1000
 *  
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node">https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author chenhantao
 * @since 2022/5/2
 */
public class Solution {

    public Node connect(Node root) {

        connectNode(root);
        return root;
    }

    /**
     * 一层一层的处理
     * @param node
     */
    public void connectNode(Node node) {
        if (node == null) {
            return;
        }

        if (node.left == null) {
            return;
        }

        node.left.next = node.right;
        if (node.next != null) {
            node.right.next = node.next.left;
        }

        connectNode(node.left);
        connectNode(node.right);
    }

    public Node connectBfs(Node root) {
        if (root == null) {
            return null;
        }

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(root);

        while (!deque.isEmpty()) {

            int current = deque.size();
            for (int i = 0; i < current; i++) {
                Node temp = deque.poll();

                if (temp == null) {
                    continue;
                }
                // 这里最后一个不能指定next但是需要poll
                if (i < current - 1) {
                    temp.next = deque.peek();
                }

                if (temp.left != null) {
                    deque.add(temp.left);
                    deque.add(temp.right);
                }
            }
        }

        return root;
    }
}
