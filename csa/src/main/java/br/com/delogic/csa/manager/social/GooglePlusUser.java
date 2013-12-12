package br.com.delogic.csa.manager.social;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePlusUser implements SocialUser {

    private String id;
    private String name;
    private String email;
    private String link;
    private Date   birthday;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @JsonProperty("displayName")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLink() {
        return link;
    }

    @JsonProperty("url")
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @JsonDeserialize(using = GooglePlusDateDeserializer.class)
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
