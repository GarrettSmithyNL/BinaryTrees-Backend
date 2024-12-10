package com.keyin.Tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tree")
public class TreeContorller {
  private final TreeServices treeServices;

  @Autowired
  public TreeContorller(TreeServices treeServices) {
    this.treeServices = treeServices;
  }

  @GetMapping("/{id}")
  public Long getTreeFromInputId(@PathVariable Long id) {
    return treeServices.createTreeFromInput(id);
  }


}
