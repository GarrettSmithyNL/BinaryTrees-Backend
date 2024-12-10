package com.keyin.Domain.Input;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Input {

  @Id
  @SequenceGenerator(name = "input_sequence", sequenceName = "input_sequence", allocationSize = 1, initialValue = 1)
  @GeneratedValue(generator = "input_sequence")
  private Long postingId;

  @ElementCollection
  private List<Integer> inputs;

  public List<Integer> getInputs() {
    return inputs;
  }

  public void setInputs(List<Integer> inputs) {
    this.inputs = inputs;
  }
}
