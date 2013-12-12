package br.com.delogic.csa.manager;

import br.com.delogic.csa.manager.util.EmailAddress;
import br.com.delogic.csa.manager.util.EmailContent;

public interface EmailManager {

    boolean sendEmail(EmailAddress from, EmailAddress replyTo, EmailContent content, EmailAddress to, EmailAddress... tos);

    boolean sendEmail(EmailAddress from, EmailContent content, EmailAddress to, EmailAddress... tos);

    void sendEmailAsynchronously(EmailAddress from, EmailAddress replyTo, EmailContent content, EmailAddress to, EmailAddress... tos);

    void sendEmailAsynchronously(EmailAddress from, EmailContent content, EmailAddress to, EmailAddress... tos);

}
