package br.com.delogic.csa.manager;

import br.com.delogic.csa.manager.social.FacebookManagerImpl;
import br.com.delogic.csa.manager.social.GooglePlusManagerImpl;
import br.com.delogic.csa.manager.social.SocialUser;

/**
 * Manages the social users used by this application. This service has
 * implementations for many social networks, but mainly for
 * {@link FacebookManagerImpl} and {@link GooglePlusManagerImpl}. The
 * applications might have more than one implementation for this service so they
 * should named accordingly.
 *
 * @author celio@delogic.com.br
 *
 * @since 13/12/2013
 */
public interface SocialManager {

    /**
     * Gets the user based on access tokens provided by social networks when the
     * user accepts this app uses his information.
     *
     * @param accessToken
     *            provided by social networks
     * @return the user information
     */
    SocialUser getUser(String accessToken);

}
