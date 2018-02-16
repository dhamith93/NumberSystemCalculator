package me.dhamith.numbersystemcalculator;

/**
 * Created by dhamith on 2/14/18.
 */

class List {
    private Node head;
    private int count;
    private final String type;
    private boolean containsPriorityOp;

    List(String type) {
        head = null;
        count = 0;
        this.type = type;
        containsPriorityOp = false;
    }

    void insert(char op, String left, String right) {
        if (op == '*' || op == '/') {
            containsPriorityOp = true;
        }
        count += 1;
        if (head == null) {
            head = new Node(op, left, right);
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(op, left, right);
    }

    /**
     * Goes through the list and:
     *  1) solves priority (*, /) nodes
     *  2) solves low-priority nodes L->R
     * @return Calculated result
     */
    String solve() {
        String result = "";
        Calculator calculator = (type.equals("bin")) ? new BinaryCalculator() :
                (type.equals("oct") ? new OctalCalculator() : new HexadecimalCalculator());
        Node current = head;
        boolean priorityEnd = false;
        if (count == 1) {
            return calculator.getResult(
                    current.op,
                    current.left,
                    current.right
            );
        } else {
            if (containsPriorityOp) {
                while (current != null) {
                    if (current.op == '*' || current.op == '/') {
                        current.result = calculator.getResult(current.op, current.left, current.right);
                        current.done = true;
                        while (current.next != null && (current.next.op == '*' || current.next.op == '/')) {
                            current.result = calculator.getResult(
                                    current.next.op,
                                    current.result,
                                    current.next.right
                            );
                            current.next.done = true;
                            current.next = current.next.next;
                            if (current.next == null) {
                                priorityEnd = true;
                            }
                        }
                    }
                    current = current.next;
                }
            }
            current = head;
            if (current != null) {
                if (current.done && priorityEnd) {
                    return current.result;
                }

                if (current.next != null && !current.next.done) {
                    result = calculator.getResult(
                            current.op,
                            current.left,
                            current.right
                    );
                } else if (current.next != null) {
                    result = calculator.getResult(
                            current.op,
                            current.left,
                            current.next.result
                    );
                }
                current.done = true;
                current = current.next;
            }

            while (current != null) {
                if (current.next != null && current.next.done) {
                    result = calculator.getResult(
                            current.op,
                            result,
                            current.next.result
                    );
                } else if (current.next == null) {
                    if (current.op == '*' || current.op == '/') {
                        break;
                    }
                    result = calculator.getResult(
                            current.op,
                            result,
                            current.right
                    );
                } else if (!current.done) {
                    result = calculator.getResult(
                            current.op,
                            result,
                            current.right
                    );
                }
                current = current.next;
            }
        }
        return result;
    }

    /**
     * For Debug. Displays all nodes on commandline
     */
    private void display() {
        Node current = head;
        while (current != null) {
            System.out.println(current.toString());
            current = current.next;
        }
    }
}
