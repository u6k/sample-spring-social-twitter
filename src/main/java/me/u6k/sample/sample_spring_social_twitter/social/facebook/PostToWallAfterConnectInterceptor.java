
package me.u6k.sample.sample_spring_social_twitter.social.facebook;

import org.springframework.social.ApiException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;

@Component
public class PostToWallAfterConnectInterceptor implements ConnectInterceptor<Facebook> {

    public void preConnect(ConnectionFactory<Facebook> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request) {
        if (StringUtils.hasText(request.getParameter(POST_TO_WALL_PARAMETER))) {
            request.setAttribute(POST_TO_WALL_ATTRIBUTE, Boolean.TRUE, WebRequest.SCOPE_SESSION);
        }
    }

    public void postConnect(Connection<Facebook> connection, WebRequest request) {
        if (request.getAttribute(POST_TO_WALL_ATTRIBUTE, WebRequest.SCOPE_SESSION) != null) {
            try {
                connection.updateStatus("I've connected with the Spring Social Showcase!");
            } catch (ApiException e) {
            }
            request.removeAttribute(POST_TO_WALL_ATTRIBUTE, WebRequest.SCOPE_SESSION);
        }
    }

    private static final String POST_TO_WALL_PARAMETER = "postToWall";

    private static final String POST_TO_WALL_ATTRIBUTE = "facebookConnect." + POST_TO_WALL_PARAMETER;

}
