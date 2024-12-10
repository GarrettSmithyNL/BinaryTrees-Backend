package com.keyin.Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class NodeServices {
  private final NodeRepository nodeRepository;

  @Autowired
  public NodeServices(NodeRepository nodeRepository) {
    this.nodeRepository = nodeRepository;
  }

  public Node createNode(Node newNode) {
     return nodeRepository.save(newNode);
  }
}
