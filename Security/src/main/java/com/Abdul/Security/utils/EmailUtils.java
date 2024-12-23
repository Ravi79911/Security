package com.Abdul.Security.utils;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils
{
        @Autowired
       private JavaMailSender javaMailSender;

       public void sendOptToEmail(String email,String otp) throws MessagingException {
           MimeMessage mimeMessage = javaMailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
           mimeMessageHelper.setTo(email);
           mimeMessageHelper.setSubject("Verify OTP");
           mimeMessageHelper.setText("""  
                   <div>
                                   <p>Dear User : %s,</p>
                                   <p>Your OTP for account verification: %s</p>
                                   <p>Please use this OTP to verify your account on our website.</p>
                               </div>
                                      
                   """.formatted(email,otp),true);
           javaMailSender.send(mimeMessage);
       }

    public void sendSetpasswordEmail(String email,String token) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("SET PASSWORD");
        mimeMessageHelper.setText("""  
                             <div>
                                        <p>Dear User: %s,</p>
                                        <p>Please click on this link to change your password:</p>
                                        <a href="http://localhost:8080/api/reset-password?token=%s" target="_blank">Reset Password</a>
                                    </div>
                                   
                """.formatted(email,token),true);
        javaMailSender.send(mimeMessage);
    }


}
