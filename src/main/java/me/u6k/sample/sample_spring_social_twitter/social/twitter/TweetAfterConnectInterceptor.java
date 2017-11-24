
package me.u6k.sample.sample_spring_social_twitter.social.twitter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.DuplicateStatusException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@Component
public class TweetAfterConnectInterceptor implements ConnectInterceptor<Twitter> {

    public void preConnect(ConnectionFactory<Twitter> provider, MultiValueMap<String, String> parameters, WebRequest request) {
        log.trace("#preConnect: start");

        if (StringUtils.hasText(request.getParameter(POST_TWEET_PARAMETER))) {
            log.trace("request has POST_TWEET_PARAMETER");
            request.setAttribute(POST_TWEET_ATTRIBUTE, Boolean.TRUE, WebRequest.SCOPE_SESSION);
        } else {
            log.trace("request hasn't POST_TWEET_PARAMETER");
        }

        log.trace("#preConnect: end");
    }

    public void postConnect(Connection<Twitter> connection, WebRequest request) {
        log.trace("#postConnect: start");

        if (request.getAttribute(POST_TWEET_ATTRIBUTE, WebRequest.SCOPE_SESSION) != null) {
            log.trace("request.POST_TWEET_ATTRIBUTE != null");
            try {
                log.trace("update status: start");
                connection.updateStatus("I've connected with the Spring Social Showcase!");
                log.trace("update status: success");
            } catch (DuplicateStatusException e) {
                log.warn("fail update status", e);
            }
            request.removeAttribute(POST_TWEET_ATTRIBUTE, WebRequest.SCOPE_SESSION);
        } else {
            log.trace("request.POST_TWEET_ATTRIBUTE == null");
        }

        log.trace("#postConnect: end");
    }

    private static final String POST_TWEET_PARAMETER = "postTweet";

    private static final String POST_TWEET_ATTRIBUTE = "twitterConnect." + POST_TWEET_PARAMETER;

}
