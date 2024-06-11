package com.example.mailhog_test.common.service;

import com.example.mailhog_test.common.dto.EmailFileAttachment;
import com.example.mailhog_test.common.dto.EmailResourceAttachment;
import com.example.mailhog_test.common.dto.MailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender javaMailSender;

  public void sendMail(boolean isHtml, MailDto mailDto) throws MessagingException {
    sendMail(isHtml, null, null, mailDto);
  }

  public void sendMail(boolean isHtml, List<EmailResourceAttachment> resourceAttachments, List<EmailFileAttachment> fileAttachments, MailDto mailDto) throws MessagingException {
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom(mailDto.getMailFrom());
    helper.setTo(mailDto.getMailTo());
    helper.setSubject(mailDto.getSubject());
    helper.setText(mailDto.getContent(), isHtml);

    if (!CollectionUtils.isEmpty(mailDto.getCc())) {
      helper.setCc(mailDto.getCc().toArray(String[]::new));
    }

    if (resourceAttachments != null && !resourceAttachments.isEmpty()) {
      addResourceAttachments(helper, resourceAttachments);
    }
    if (fileAttachments != null && !fileAttachments.isEmpty()) {
      addFileAttachments(helper, fileAttachments);
    }
    this.javaMailSender.send(message);
  }

  private void addResourceAttachments(MimeMessageHelper helper, List<EmailResourceAttachment> attachments) throws MessagingException {
    for (var attachment : attachments) {
      helper.addAttachment(attachment.getFileName(), attachment.getSource());
    }
  }

  private void addFileAttachments(MimeMessageHelper helper, List<EmailFileAttachment> attachments) throws MessagingException {
    for (var attachment : attachments) {
      helper.addAttachment(attachment.getFileName(), attachment.getFile());
    }
  }
}
