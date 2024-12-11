package com.keyin.Domain.Tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tree")
public class TreeController {
  private final TreeServices treeServices;

  @Autowired
  public TreeController(TreeServices treeServices) {
    this.treeServices = treeServices;
  }

 @GetMapping("/{id}")
public ResponseEntity<Tree> getTreeFromInputId(@PathVariable Long id) {
  Tree tree = treeServices.createTreeFromInput(id);
  if (tree != null) {
    return new ResponseEntity<>(tree, HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}


}
