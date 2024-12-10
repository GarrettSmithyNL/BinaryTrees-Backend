package com.keyin.Domain.Input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/input")
public class InputController {
  private final InputServices inputServices;

  @Autowired
  public InputController(InputServices inputServices) {
    this.inputServices = inputServices;
  }

  @PostMapping("/post")
  public Input postInput(@RequestBody Input input) {
    return inputServices.addInput(input);
  }
}
