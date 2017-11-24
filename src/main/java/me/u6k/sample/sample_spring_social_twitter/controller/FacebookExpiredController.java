
package me.u6k.sample.sample_spring_social_twitter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class FacebookExpiredController {

    @RequestMapping("/facebook/expired")
    public void simulateExpiredToken() {
        log.trace("#simulateExpiredToken: start");

        throw new ExpiredAuthorizationException("facebook");
    }

}
