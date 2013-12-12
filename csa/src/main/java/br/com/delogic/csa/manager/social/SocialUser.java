package br.com.delogic.csa.manager.social;

import java.util.Date;

public interface SocialUser {

    public String getId();

    public String getName();

    public String getLink();

    public Date getBirthday();

    public String getEmail();

}