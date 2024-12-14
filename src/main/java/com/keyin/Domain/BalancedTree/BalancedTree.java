package com.keyin.Domain.BalancedTree;

import com.keyin.Domain.Node.Node;
import jakarta.persistence.*;

@Entity
public class BalancedTree {

  @Id
  @SequenceGenerator(name = "balanced_tree_sequence", sequenceName = "balanced_tree_sequence", allocationSize = 1, initialValue = 1)
  @GeneratedValue(generator = "balanced_tree_sequence")
  private Long postingId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "rood_id")
  private Node root;

  public BalancedTree() {
    this.root = null;
  }

  public Long getPostingId() {
    return postingId;
  }

  public Node getRoot() {
    return root;
  }

  public void setRoot(Node root) {
    this.root = root;
  }
}
