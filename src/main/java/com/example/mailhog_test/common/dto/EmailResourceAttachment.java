package com.example.mailhog_test.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.InputStreamSource;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailResourceAttachment {
  private String fileName;
  private InputStreamSource source;
}
