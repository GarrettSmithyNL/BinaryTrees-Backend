package com.keyin.Domain.Input;

import com.keyin.Domain.Tree.TreeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class InputServices {
  private final InputRepository inputRepository;
  private final TreeServices treeServices;

  @Autowired
  public InputServices (InputRepository inputRepository, TreeServices treeServices) {
    this.inputRepository = inputRepository;
    this.treeServices = treeServices;
  }

  public Input addInput(Input newInput) {
    Input inputInDb = inputRepository.save(newInput);
    inputInDb.setTreeFromInput(treeServices.createTreeFromInput(inputInDb.getPostingId()));
    return inputRepository.save(inputInDb);
  }

  public void saveInput(Input inputToUpdate) {
    inputRepository.save(inputToUpdate);
  }

  public Input getInput(Long inputId) {
    Optional<Input> optionalInput = inputRepository.findById(inputId);
    return optionalInput.orElse(null);
  }



  public List<Input> getAll() {
    return (List<Input>) inputRepository.findAll();
  }

  public Input getLastAdded() {
    List<Input> inputsFromDb = (List<Input>) inputRepository.findAll();
    return inputsFromDb.stream()
                       .max(Comparator.comparing(Input::getPostingId))
                       .orElse(null);
  }
}
