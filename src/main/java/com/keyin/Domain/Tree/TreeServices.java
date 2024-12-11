package com.keyin.Domain.Tree;

import com.keyin.Domain.Input.InputServices;
import com.keyin.Domain.Node.Node;
import com.keyin.Domain.Node.NodeServices;
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

  public Tree createTreeFromInput(Long inputId) {
    List<Integer> inputFromDb = inputServices.getInput(inputId).getInputs();
    Tree newTree = new Tree();
    Node treeRoot = newTree.getRoot();
    for(Integer value : inputFromDb) {
      Node newNode = new Node();
      newNode.setValue(value);
      treeRoot = add(treeRoot, newNode);
    }
    newTree.setRoot(treeRoot);
    return treeRepository.save(newTree);
  }

  private Node add(Node root, Node newNode) {
    if (root == null) {
      root = nodeServices.createNode(newNode);
      return root;
    }

    if (newNode.getValue() > root.getValue()) {
      root.setRight(add(root.getRight(), newNode));
      nodeServices.createNode(root.getRight());
    } else if (newNode.getValue() < root.getValue()) {
      root.setLeft(add(root.getLeft(), newNode));
      nodeServices.createNode(root.getLeft());
    }
    return root;
  }



}
