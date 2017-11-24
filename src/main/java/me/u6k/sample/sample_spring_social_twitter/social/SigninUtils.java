
package me.u6k.sample.sample_spring_social_twitter.social;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SigninUtils {

    public static void signin(String userId) {
        log.trace("#signin: start: userId={}", userId);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));

        log.trace("#signin: end");
    }

}
