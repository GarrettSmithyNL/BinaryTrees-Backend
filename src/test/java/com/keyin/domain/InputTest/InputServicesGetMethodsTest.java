package com.keyin.domain.InputTest;

import com.keyin.Domain.Input.Input;
import com.keyin.Domain.Input.InputRepository;
import com.keyin.Domain.Input.InputServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InputServicesGetMethodsTest {

  @Mock
  private InputRepository inputRepository;

  @InjectMocks
  private InputServices inputServices;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAll() {
    Iterable<Input> inputs = Collections.singletonList(new Input());
    when(inputRepository.findAll()).thenReturn(inputs);
    Iterable<Input> result = inputServices.getAll();
    assertEquals(inputs, result);
    verify(inputRepository, times(1)).findAll();
  }
}
