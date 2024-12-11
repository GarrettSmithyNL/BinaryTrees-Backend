package com.keyin.Domain.Tree;

import com.keyin.Domain.Node.Node;
import jakarta.persistence.*;

@Entity
public class Tree {

  @Id
  @SequenceGenerator(name = "tree_sequence", sequenceName = "tree_sequence", allocationSize = 1, initialValue = 1)
  @GeneratedValue(generator = "tree_sequence")
  private Long postingId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "rood_id")
  private Node root;

  public Tree () {
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
