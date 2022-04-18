package com.example.bst;

import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree {
    private Node root = null;

    public boolean find(int id) {
        Node current = root;

        while (current != null) {
            if (current.data == id) {
                return true;
            } else if (current.data > id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public void insert(int id) {
        Node newNode = new Node(id);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (true) {
            parent = current;

            if (id < current.data) {
                current = current.left;

                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;

                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public void iterate(Node root, List<Integer> results) {
        if (root != null) {
            iterate(root.left, results);

            results.add(root.data);

            iterate(root.right, results);
        }
    }

    public List<Integer> getInOrder() {
        LinkedList<Integer> result = new LinkedList<>();

        iterate(this.root, result);

        return result;
    }

    public boolean delete(int id) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;

        while (current.data != id) {
            parent = current;

            if (current.data > id) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }

            if (current == null) {
                return false;
            }
        }

        /* If I am here that means we have found the Node */
        /**
         * Case 1: if Node to be deleted has no children
         */
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }

            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        /**
         * Case 2: if Node to be deleted has only one child
         */
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {
            /* Now we have found the minimum element in the right sub tree */
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    public Node getSuccessor(Node node) {
        Node successor = null;
        Node successorParent = null;
        Node current = node.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        /* Check if successor has the right child, it cannot have left child for sure if it does have the right child, add it to the left of successorParent. */
        if (successor != node.right) {
            successorParent.left = successor.right;
            successor.right = node.right;
        }

        return successor;
    }

    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
