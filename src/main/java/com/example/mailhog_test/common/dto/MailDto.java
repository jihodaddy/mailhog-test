package com.example.mailhog_test.common.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
  private String mailFrom;
  private String mailTo;
  private String subject;
  private String content;
  private List<String> cc;
}
