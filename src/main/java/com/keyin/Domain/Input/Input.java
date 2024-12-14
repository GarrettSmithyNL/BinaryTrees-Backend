package com.keyin.Domain.Input;

import com.keyin.Domain.BalancedTree.BalancedTree;
import com.keyin.Domain.Tree.Tree;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Input {

  @Id
  @SequenceGenerator(name = "input_sequence", sequenceName = "input_sequence", allocationSize = 1, initialValue = 1)
  @GeneratedValue(generator = "input_sequence")
  private Long postingId;

  @ElementCollection
  private List<Integer> inputs;

  @OneToOne
  private Tree treeFromInput;

  @OneToOne
  private BalancedTree balancedTreeFromInput;

  public Long getPostingId() {
    return postingId;
  }

  public void setPostingId(Long postingId) {
    this.postingId = postingId;
  }

  public List<Integer> getInputs() {
    return inputs;
  }

  public void setInputs(List<Integer> inputs) {
    this.inputs = inputs;
  }

  public Tree getTreeFromInput() {
    return treeFromInput;
  }

  public void setTreeFromInput(Tree treeFromInput) {
    this.treeFromInput = treeFromInput;
  }

  public BalancedTree getBalancedTreeFromInput() {
    return balancedTreeFromInput;
  }

  public void setBalancedTreeFromInput(BalancedTree balancedTreeFromInput) {
    this.balancedTreeFromInput = balancedTreeFromInput;
  }
}
