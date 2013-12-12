package br.com.delogic.csa.manager;

import br.com.delogic.csa.manager.util.EmailAddress;
import br.com.delogic.csa.manager.util.EmailContent;

public interface EmailManager {

    boolean send(EmailAddress from, EmailAddress replyTo, EmailContent content, EmailAddress to, EmailAddress... tos);

    boolean send(EmailAddress from, EmailContent content, EmailAddress to, EmailAddress... tos);

    void sendAsynchronously(EmailAddress from, EmailAddress replyTo, EmailContent content, EmailAddress to, EmailAddress... tos);

    void sendAsynchronously(EmailAddress from, EmailContent content, EmailAddress to, EmailAddress... tos);

}
