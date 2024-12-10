package com.keyin.Tree;

import com.keyin.Input.InputServices;
import com.keyin.Node.Node;
import com.keyin.Node.NodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeServices {
  private final TreeRepository treeRepository;
  private final InputServices inputServices;
  private final NodeServices nodeServices;

  @Autowired
  public TreeServices(TreeRepository treeRepository, InputServices inputServices, NodeServices nodeServices) {
    this.treeRepository = treeRepository;
    this.inputServices = inputServices;
    this.nodeServices = nodeServices;
  }

  public Long createTreeFromInput(Long inputId) {
    List<Integer> inputFromDb = inputServices.getInput(inputId).getInputs();
    Tree newTree = new Tree();
    for(Integer value : inputFromDb) {
      newTree.add(value);
    }
    newTree = treeRepository.save(newTree);
    saveNodes(newTree.getRoot());
    return newTree.getPostingId();
  }


  private void saveNodes(Node root) {
    if (root != null) {
      nodeServices.createNode(root);
      saveNodes(root.getLeft());
      saveNodes(root.getRight());
    }
  }
}
