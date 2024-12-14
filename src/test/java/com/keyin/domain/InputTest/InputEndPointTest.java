package com.keyin.domain.InputTest;


import com.keyin.Domain.Input.Input;
import com.keyin.Domain.Input.InputController;
import com.keyin.Domain.Input.InputServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InputController.class)
public class InputEndPointTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private InputServices inputServices;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateInput() throws Exception {
    Input testInput = new Input();
    testInput.setPostingId(1L);

    when(inputServices.addInput(ArgumentMatchers.any(Input.class))).thenReturn(testInput);

    mockMvc.perform(post("/input/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"postingId\":1}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("postingId").value(1));
  }

  @Test
  void testGetAll() throws Exception {
    Input testInput = new Input();
    testInput.setPostingId(1L);
    List<Input> inputs = Collections.singletonList(testInput);

    when(inputServices.getAll()).thenReturn(inputs);

    mockMvc.perform(get("/input/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].postingId").value(1));
  }
}
