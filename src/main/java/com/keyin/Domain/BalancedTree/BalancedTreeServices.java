package com.keyin.Domain.BalancedTree;

import com.keyin.Domain.Input.Input;
import com.keyin.Domain.Input.InputRepository;
import com.keyin.Domain.Node.Node;
import com.keyin.Domain.Node.NodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class BalancedTreeServices {
  private final BalancedTreeRepository balancedTreeRepository;
  private final InputRepository inputRepository;
  private final NodeServices nodeServices;

  @Autowired
  public BalancedTreeServices (BalancedTreeRepository balancedTreeRepository, InputRepository inputRepository, NodeServices nodeServices) {
    this.balancedTreeRepository = balancedTreeRepository;
    this.inputRepository = inputRepository;
    this.nodeServices = nodeServices;
  }

  public BalancedTree createBalancedTree(long inputId) {
    Optional<Input> optionalInput = inputRepository.findById(inputId);
    Input inputFromDb = optionalInput.orElse(null);
    BalancedTree newTree = new BalancedTree();
    if(inputFromDb == null) {
      return newTree;
    }
    if (inputFromDb.getBalancedTreeFromInput() == null) {
      int[] values = inputFromDb.getInputs()
              .stream()
              .mapToInt(Integer::intValue)
              .toArray();
      values = Arrays.stream(values).sorted().toArray();
      Node treeRoot = newTree.getRoot();
      treeRoot = createBalancedTreeRec(values, 0, values.length-1);
      newTree.setRoot(treeRoot);
      newTree = balancedTreeRepository.save(newTree);
      inputFromDb.setBalancedTreeFromInput(newTree);
      inputRepository.save(inputFromDb);
    } else {
      newTree = inputFromDb.getBalancedTreeFromInput();
    }

    return newTree;
  }

  private Node createBalancedTreeRec(int[] values, int start, int end) {
    if (start > end) {
      return null;
    }
    int mid = (start + end) / 2;
    Node node = new Node();
    node.setValue(values[mid]);
    nodeServices.createNode(node);

    node.setLeft(createBalancedTreeRec(values, start, mid - 1));
    node.setRight(createBalancedTreeRec(values, mid + 1, end));

    return node;
  }
}
