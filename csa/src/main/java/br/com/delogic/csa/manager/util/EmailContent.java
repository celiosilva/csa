package br.com.delogic.csa.manager.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import br.com.delogic.csa.util.is;

public class EmailContent {

    private String subject;
    private String htmlContent;
    private URL    url;

    public EmailContent(String subject, String htmlContent) {
        this.subject = subject;
        this.htmlContent = htmlContent;
    }

    public EmailContent() {

    }

    public EmailContent(String subject, URL url) {
        this.subject = subject;
        this.url = url;
    }

    private String getContent(URL url) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String line = null;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlContent() {
        if (is.empty(htmlContent) && is.notEmpty(url)) {
            htmlContent = getContent(url);
        }
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

}
