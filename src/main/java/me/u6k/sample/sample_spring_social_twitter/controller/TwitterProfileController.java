
package me.u6k.sample.sample_spring_social_twitter.controller;

import java.security.Principal;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class TwitterProfileController {

    @Inject
    private ConnectionRepository connectionRepository;

    @RequestMapping(value = "/twitter", method = RequestMethod.GET)
    public String home(Principal currentUser, Model model) {
        log.trace("#home: start: currentUser={}", currentUser);

        Connection<Twitter> connection = connectionRepository.findPrimaryConnection(Twitter.class);
        if (connection == null) {
            log.trace("findPrimaryConnection == null");
            return "redirect:/connect/twitter";
        } else {
            log.trace("findPrimaryConnection != null");
        }
        model.addAttribute("profile", connection.getApi().userOperations().getUserProfile());

        log.trace("#home: end");
        return "twitter/profile";
    }

}
