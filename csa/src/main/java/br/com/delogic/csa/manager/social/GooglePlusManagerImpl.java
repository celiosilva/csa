package br.com.delogic.csa.manager.social;

import org.springframework.web.client.RestTemplate;

import br.com.delogic.csa.manager.SocialManager;

public class GooglePlusManagerImpl implements SocialManager {

    private String       getUserMethod  = "https://www.googleapis.com/plus/v1/people/me?access_token={accessToken}";
    private String       getEmailMethod = "https://www.googleapis.com/oauth2/v2/userinfo?access_token={accessToken}";
    private RestTemplate restTemplate   = new RestTemplate();

    @Override
    public GooglePlusUser getUser(String accessToken) {
        GooglePlusUser user = restTemplate.getForObject(getUserMethod, GooglePlusUser.class, accessToken);
        if (user != null) {
            String email = restTemplate.getForObject(getEmailMethod, GooglePlusUser.class, accessToken).getEmail();
            user.setEmail(email);
        }
        return user;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGetUserMethod() {
        return getUserMethod;
    }

    public void setGetUserMethod(String getUserMethod) {
        this.getUserMethod = getUserMethod;
    }

    public String getGetEmailMethod() {
        return getEmailMethod;
    }

    public void setGetEmailMethod(String getEmailMethod) {
        this.getEmailMethod = getEmailMethod;
    }

}
