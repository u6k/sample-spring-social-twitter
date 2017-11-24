
package me.u6k.sample.sample_spring_social_twitter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class FacebookProfileController {

    @Autowired
    private ConnectionRepository connectionRepository;

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String home(Model model) {
        log.trace("#home: start");

        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (connection == null) {
            log.trace("findPrimaryConnection == null");

            log.trace("#home: end: redirect:/connect/facebook");
            return "redirect:/connect/facebook";
        }

        model.addAttribute("profile", connection.getApi().userOperations().getUserProfile());

        log.trace("#home: end: facebook/profile");
        return "facebook/profile";
    }

}
