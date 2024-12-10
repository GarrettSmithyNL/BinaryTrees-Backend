package com.keyin.Input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InputServices {
  private final InputRepository inputRepository;

  @Autowired
  public InputServices (InputRepository inputRepository) {
    this.inputRepository = inputRepository;
  }

  public Input addInput(Input newInput) {
    return inputRepository.save(newInput);
  }

  public Input getInput(Long inputId) {
    Optional<Input> optionalInput = inputRepository.findById(inputId);
    return optionalInput.orElse(null);
  }
}
