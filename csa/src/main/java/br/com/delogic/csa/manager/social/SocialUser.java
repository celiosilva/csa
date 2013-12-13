package br.com.delogic.csa.manager.social;

import java.util.Date;

/**
 * The main interface for social users which are authenticated into the
 * application by some social network. It may or may not present all information
 * depending on the network.
 *
 * @author celio@delogic.com.br
 *
 * @since 13/12/2013
 */
public interface SocialUser {

    public String getId();

    public String getName();

    public String getLink();

    public Date getBirthday();

    public String getEmail();

}