
package me.u6k.sample.sample_spring_social_twitter.controller;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class FacebookFriendsController {

    private final Facebook facebook;

    @Inject
    public FacebookFriendsController(Facebook facebook) {
        log.trace("#ctor: start");

        this.facebook = facebook;

        log.trace("#ctor: end");
    }

    @RequestMapping(value = "/facebook/friends", method = RequestMethod.GET)
    public String showFeed(Model model) {
        log.trace("#showFeed: start");

        model.addAttribute("friends", facebook.friendOperations().getFriendProfiles());

        log.trace("#showFeed: end");
        return "facebook/friends";
    }

}
