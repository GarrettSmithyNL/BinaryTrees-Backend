package com.keyin.Domain.Node;

import jakarta.persistence.*;

@Entity
public class Node {

  @Id
  @SequenceGenerator(name = "node_sequence", sequenceName = "node_sequence", allocationSize = 1, initialValue = 1)
  @GeneratedValue(generator = "node_sequence")
  private Long postingId;

  private int value;

  @OneToOne
  private Node left;

  @OneToOne
  private Node right;

  public Node() {
    this.left = null;
    this.right = null;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public Node getLeft() {
    return left;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public Node getRight() {
    return right;
  }

  public void setRight(Node right) {
    this.right = right;
  }

}
