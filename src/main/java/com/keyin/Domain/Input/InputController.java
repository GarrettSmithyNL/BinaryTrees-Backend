package com.keyin.Domain.Input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping("/all")
  public List<Input> getAll() {
    return inputServices.getAll();
  }

  @GetMapping("/last")
  public Input getLast() {
    return inputServices.getLastAdded();
  }
}
