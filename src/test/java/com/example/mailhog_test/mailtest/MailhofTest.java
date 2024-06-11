package com.example.mailhog_test.mailtest;

import com.example.mailhog_test.common.dto.EmailFileAttachment;
import com.example.mailhog_test.common.dto.MailDto;
import com.example.mailhog_test.common.service.MailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MailhofTest {
  @Autowired
  MailService mailService;

  @Test
  void mailTest() throws MessagingException {
    MailDto mailDto = MailDto.builder()
            .mailFrom("sender@gmail.com")
            .mailTo("test@gmail.com")
            .subject("test")
            .content("test")
            .build();

    mailService.sendMail(false, mailDto);
  }

  @Test
  void send_mail_with_file_test() throws MessagingException {
    MailDto mailDto = MailDto.builder()
            .mailFrom("sender@gmail.com")
            .mailTo("test@gmail.com")
            .subject("file attachment test")
            .content("test with file")
            .build();

    List<EmailFileAttachment> fileAttachments = new ArrayList<EmailFileAttachment>();
    File file = new File("C:\\Download\\test.txt");
    fileAttachments.add(new EmailFileAttachment("test.txt", file));

    mailService.sendMail(false, null, fileAttachments, mailDto);
  }
}
