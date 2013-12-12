package br.com.delogic.csa.manager.social;

import org.springframework.web.client.RestTemplate;

import br.com.delogic.csa.manager.SocialManager;

public class FacebookManagerImpl implements SocialManager {

    private String       getUserMethod = "https://graph.facebook.com/me?access_token={accessToken}";
    private RestTemplate restTemplate  = new RestTemplate();

    @Override
    public FacebookUser getUser(String accessToken) {
        return restTemplate.getForObject(getUserMethod, FacebookUser.class, accessToken);
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

}
