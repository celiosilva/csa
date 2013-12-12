package br.com.delogic.csa.manager.email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import br.com.delogic.csa.manager.EmailManager;
import br.com.delogic.csa.util.Converter;
import br.com.delogic.csa.util.Do;
import br.com.delogic.csa.util.is;

public class EmailManagerImpl implements EmailManager {

    @Inject
    private JavaMailSender      mailSender;

    private static final Logger log      = LoggerFactory.getLogger(EmailManagerImpl.class);

    private final Executor      executor = Executors.newSingleThreadExecutor();

    public boolean send(final EmailAddress from, final EmailAddress replyTo, final EmailContent content,
        final EmailAddress to, final EmailAddress... tos) {

        List<EmailAddress> emailTos = new ArrayList<EmailAddress>();
        emailTos.add(to);
        if (is.notEmpty(tos)) {
            emailTos.addAll(Arrays.asList(tos));
        }
        final List<InternetAddress> addressesTo = Do.convertList(emailTos,
            new Converter<EmailAddress, InternetAddress>() {
                @Override
                public InternetAddress convert(EmailAddress in) {
                    try {
                        return new InternetAddress(in.getAddress(), in.getName());
                    } catch (UnsupportedEncodingException e) {
                        log.error("Error creating InternetAddress:" + e, e);
                        throw new IllegalArgumentException("Error creating InternetAddress", e);
                    }
                }
            });

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setTo(addressesTo.toArray(new InternetAddress[addressesTo.size()]));
                helper.setSubject(content.getSubject());
                helper.setFrom(from.getAddress(), from.getName());
                helper.setSentDate(new Date());
                helper.setReplyTo(replyTo.getAddress(), replyTo.getName());
                helper.setText(content.getHtmlContent(), true);
            }
        };
        // é usado um try catch para poder continuar com o processo mesmo que o
        // email gere um erro
        try {
            log.info("Sending email to:" + emailTos);
            mailSender.send(preparator);
            return true;
        } catch (Exception ex) {
            log.error("Error sending email to:" + emailTos, ex);
            return false;
        }
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean send(EmailAddress from, EmailContent content, EmailAddress to, EmailAddress... tos) {
        return send(from, from, content, to, tos);
    }

    @Override
    public void sendAsynchronously(final EmailAddress from, final EmailAddress replyTo, final EmailContent content,
        final EmailAddress to, final EmailAddress... tos) {
        log.info("Sendign email asynchronously");

        Runnable email = new Runnable() {
            @Override
            public void run() {
                send(from, replyTo, content, to, tos);
            }
        };
        executor.execute(email);
    }

    @Override
    public void sendAsynchronously(EmailAddress from, EmailContent content, EmailAddress to, EmailAddress... tos) {
        sendAsynchronously(from, from, content, to, tos);
    }

}
