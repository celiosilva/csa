package br.com.delogic.csa.manager;

import br.com.delogic.csa.manager.email.EmailAddress;
import br.com.delogic.csa.manager.email.EmailContent;

/**
 * Manages the e-mail functionality using a simple interface. The main
 * implementation relies on Spring infrastructure for sending e-mails but can be
 * implemented as needed. Developers should rely on this service for email.
 *
 * @author celio@delogic.com.br
 *
 * @since 13/12/2013
 */
public interface EmailManager {

    /**
     * Sends the email declaring where it came "from", whom should be
     * "replied to", the e-mail content (text, html or a URL), who should this
     * be e-mailed "to" and additional "tos".
     *
     * @param from
     *            - where is it coming from
     * @param replyTo
     *            - the person to be replied
     * @param content
     *            - content to be sent (text, html or URL which will be turned
     *            into html)
     * @param to
     *            - the one to receive
     * @param tos
     *            - other people to receive
     * @return boolean whether it was sent or not
     */
    boolean send(EmailAddress from, EmailAddress replyTo, EmailContent content, EmailAddress to, EmailAddress... tos);

    /**
     * Sends the email declaring where it came "from", the e-mail content (text,
     * html or a URL), who should this be e-mailed "to" and additional "tos".
     * The reply to will be the same person which sent (from)
     *
     * @param from
     *            - where is it coming from
     * @param content
     *            - content to be sent (text, html or URL which will be turned
     *            into html)
     * @param to
     *            - the one to receive
     * @param tos
     *            - other people to receive
     * @return boolean whether it was sent or not
     */
    boolean send(EmailAddress from, EmailContent content, EmailAddress to, EmailAddress... tos);

    /**
     * Sends the email asynchronously in another thread, declaring where it came
     * "from", whom should be "replied to", the e-mail content (text, html or a
     * URL), who should this be e-mailed "to" and additional "tos".
     *
     * @param from
     *            - where is it coming from
     * @param replyTo
     *            - the person to be replied
     * @param content
     *            - content to be sent (text, html or URL which will be turned
     *            into html)
     * @param to
     *            - the one to receive
     * @param tos
     *            - other people to receive
     */
    void sendAsynchronously(EmailAddress from, EmailAddress replyTo, EmailContent content, EmailAddress to, EmailAddress... tos);

    /**
     * Sends the email asynchronously in another thread, declaring where it came
     * "from", the e-mail content (text, html or a URL), who should this be
     * e-mailed "to" and additional "tos". The reply to will be the same person
     * which sent (from)
     *
     * @param from
     *            - where is it coming from
     * @param content
     *            - content to be sent (text, html or URL which will be turned
     *            into html)
     * @param to
     *            - the one to receive
     * @param tos
     *            - other people to receive
     * @return boolean whether it was sent or not
     */
    void sendAsynchronously(EmailAddress from, EmailContent content, EmailAddress to, EmailAddress... tos);

}
