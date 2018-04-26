package me.dhamith.numbersystemcalculator;

/**
 * Created by dhamith on 4/26/18.
 */

class ExpressionTree {
    private Node root;
    private Node current;
    private String result;
    private final Calculator calculator;

    ExpressionTree(String type) {
        root = null;
        root = null;
        current = null;
        calculator = (type.equals("bin")) ? new BinaryCalculator() :
                (type.equals("oct") ? new OctalCalculator() : new HexadecimalCalculator());
    }

    void insert(String data, boolean op) {
        Node tempNode = new Node(data, op);
        if (root == null) {
            root = tempNode;
            return;
        }
        if (!root.op) {
            tempNode.left = root;
            root = tempNode;
            return;
        }
        if (root.left == null) {
            root.left = tempNode;
            return;
        }
        if (root.right == null) {
            root.right = tempNode;
            return;
        }
        if (root.right.op) {
            current = root.right;
            while (true) {
                if (current.left == null) {
                    current.left = tempNode;
                    return;
                }
                if (current.right == null) {
                    current.right = tempNode;
                    return;
                }
                if (!current.right.op) {
                    tempNode.left = root;
                    root = tempNode;
                    return;
                }
                if (current.right.left == null) {
                    current.right.left = tempNode;
                    return;
                }
                if (current.right.right == null) {
                    current.right.right = tempNode;
                    return;
                }
                if (current.right.right.op) {
                    current = current.right.right;
                    continue;
                }
                break;
            }
        }
        tempNode.left = root;
        root = tempNode;
    }

    private String solve(Node root) throws DivideByZeroException {
        if (root.op) {
            if (root.left != null && root.right != null) {
                if (!root.left.op && !root.right.op) {
                    root.result = calculator.getResult(root.data, root.left.data, root.right.data);
                    return root.result;
                }
                if (!root.left.op) {
                    if (!root.right.result.isEmpty()) {
                        root.result = calculator.getResult(root.data, root.left.data, root.right.result);
                        return root.result;
                    }
                }
                if (root.left.op && !root.right.op) {
                    if (!root.left.result.isEmpty()) {
                        root.result = calculator.getResult(root.data, root.left.result, root.right.data);
                        return root.result;
                    }
                }
                if (root.left.op && root.right.op) {
                    if (!root.left.result.isEmpty() && !root.right.result.isEmpty()) {
                        root.result = calculator.getResult(root.data, root.left.result, root.right.result);
                        return root.result;
                    }
                }
            }
        }
        return result;
    }

    private void preoderTraversal(Node root) throws DivideByZeroException {
        if (root != null) {
            preoderTraversal(root.right);
            preoderTraversal(root.left);
            result = solve(root);
        }
    }

    String getResult() throws DivideByZeroException {
        preoderTraversal(root);
        return result;
    }
}
