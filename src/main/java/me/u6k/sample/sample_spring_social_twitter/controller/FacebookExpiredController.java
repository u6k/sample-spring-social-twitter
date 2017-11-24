
package me.u6k.sample.sample_spring_social_twitter.controller;

import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FacebookExpiredController {

    @RequestMapping("/facebook/expired")
    public void simulateExpiredToken() {
        throw new ExpiredAuthorizationException("facebook");
    }

}
