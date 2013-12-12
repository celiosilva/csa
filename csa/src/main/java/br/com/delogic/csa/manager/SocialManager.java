package br.com.delogic.csa.manager;

import br.com.delogic.csa.manager.social.SocialUser;

public interface SocialManager {

    SocialUser getUser(String accessToken);

}
