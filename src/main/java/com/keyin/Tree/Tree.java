package com.keyin.Tree;

import com.keyin.Node.Node;
import jakarta.persistence.*;

@Entity
public class Tree {

  @Id
  @SequenceGenerator(name = "tree_sequence", sequenceName = "tree_sequence", allocationSize = 1, initialValue = 1)
  @GeneratedValue(generator = "tree_sequence")
  private Long postingId;

  @OneToOne
  private Node root;

  public Tree () {
    this.root = null;
  }

  public void add(int value) {
    root = recursiveAdd(root, value);
  }

  private Node recursiveAdd(Node root, int value) {
    if (root == null) {
      root = new Node(value);
      return root;
    }

    if (value > root.getValue())
      root.setRight(recursiveAdd(root.getRight(), value));
    else if (value < root.getValue())
      root.setLeft(recursiveAdd(root.getLeft(), value));

    return root;
  }

  public void printInOrder() {
    recursivePrintInOrder(root);
  }

  private void recursivePrintInOrder(Node root) {
    if (root != null) {
      recursivePrintInOrder(root.getLeft());
      System.out.print(root.getValue() + " ");
      recursivePrintInOrder(root.getRight());
    }
  }

  public static void main(String[] args) {
    Tree testTree = new Tree();
    testTree.add(5);
    testTree.add(12);
    testTree.add(7);
    testTree.add(9);
    testTree.add(25);
    testTree.add(1);
    testTree.add(10);
    testTree.add(8);
    testTree.printInOrder();

  }

}