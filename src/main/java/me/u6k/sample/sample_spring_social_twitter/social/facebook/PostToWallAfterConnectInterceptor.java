
package me.u6k.sample.sample_spring_social_twitter.social.facebook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.ApiException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@Component
public class PostToWallAfterConnectInterceptor implements ConnectInterceptor<Facebook> {

    public void preConnect(ConnectionFactory<Facebook> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request) {
        log.trace("#preConnect: start");

        if (StringUtils.hasText(request.getParameter(POST_TO_WALL_PARAMETER))) {
            log.trace("request has POST_TO_WALL_PARAMETER");
            request.setAttribute(POST_TO_WALL_ATTRIBUTE, Boolean.TRUE, WebRequest.SCOPE_SESSION);
        } else {
            log.trace("request hasn't POST_TO_WALL_PARAMETER");
        }

        log.trace("#preConnect: end");
    }

    public void postConnect(Connection<Facebook> connection, WebRequest request) {
        log.trace("#postConnect: start");

        if (request.getAttribute(POST_TO_WALL_ATTRIBUTE, WebRequest.SCOPE_SESSION) != null) {
            log.trace("request.POST_TO_WALL_ATTRIBUTE != null");

            try {
                log.trace("update status: start");
                connection.updateStatus("I've connected with the Spring Social Showcase!");
                log.trace("update status: success");
            } catch (ApiException e) {
                log.warn("fail update status", e);
            }
            request.removeAttribute(POST_TO_WALL_ATTRIBUTE, WebRequest.SCOPE_SESSION);
        } else {
            log.trace("request.POST_TO_WALL_ATTRIBUTE == null");
        }

        log.trace("#postConnect: end");
    }

    private static final String POST_TO_WALL_PARAMETER = "postToWall";

    private static final String POST_TO_WALL_ATTRIBUTE = "facebookConnect." + POST_TO_WALL_PARAMETER;

}
