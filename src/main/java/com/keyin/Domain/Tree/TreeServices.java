package com.keyin.Domain.Tree;

import com.keyin.Domain.Input.Input;
import com.keyin.Domain.Input.InputRepository;
import com.keyin.Domain.Node.Node;
import com.keyin.Domain.Node.NodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreeServices {
  private final TreeRepository treeRepository;
  private final InputRepository inputRepository;
  private final NodeServices nodeServices;

  @Autowired
  public TreeServices(TreeRepository treeRepository, InputRepository inputServices, NodeServices nodeServices) {
    this.treeRepository = treeRepository;
    this.inputRepository = inputServices;
    this.nodeServices = nodeServices;
  }

  public Tree createTreeFromInput(Long inputId) {
    Optional<Input> optionalInput = inputRepository.findById(inputId);
    Input inputFromDb = optionalInput.orElse(null);
    Tree newTree = new Tree();
    if(inputFromDb == null) {
      return newTree;
    }
    if (inputFromDb.getTreeFromInput() == null) {
      List<Integer> values = inputFromDb.getInputs();
      Node treeRoot = newTree.getRoot();
      for (Integer value : values) {
        Node newNode = new Node();
        newNode.setValue(value);
        treeRoot = add(treeRoot, newNode);
      }
      newTree.setRoot(treeRoot);
      newTree = treeRepository.save(newTree);
      inputFromDb.setTreeFromInput(newTree);
      inputRepository.save(inputFromDb);
    } else {
      newTree = inputFromDb.getTreeFromInput();
    }
    return newTree;
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
