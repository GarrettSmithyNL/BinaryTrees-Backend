package com.keyin.Tree;

import com.keyin.Input.InputServices;
import com.keyin.Node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeServices {
  private final TreeRepository treeRepository;
  private final InputServices inputServices;

  @Autowired
  public TreeServices(TreeRepository treeRepository, InputServices inputServices) {
    this.treeRepository = treeRepository;
    this.inputServices = inputServices;
  }

  public Long createTreeFromInput(Long inputId) {
    List<Integer> inputFromDb = inputServices.getInput(inputId).getInputs();
    Tree newTree = new Tree();
    for(Integer value : inputFromDb) {
      newTree.add(value);
    }
    newTree = treeRepository.save(newTree);

    return newTree.getPostingId();
  }

  private List<Node> saveNodes(Node root) {
    if (root != null) {
      saveNodes(root.getLeft());

      saveNodes(root.getRight());
    }
  }
}
