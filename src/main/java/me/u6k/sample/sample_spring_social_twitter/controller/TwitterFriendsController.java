
package me.u6k.sample.sample_spring_social_twitter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class TwitterFriendsController {

    @Autowired
    private Twitter twitter;

    @RequestMapping(value = "/twitter/friends", method = RequestMethod.GET)
    public String friends(Model model) {
        log.trace("#friends: start");

        model.addAttribute("profiles", twitter.friendOperations().getFriends());

        log.trace("#friends: end");
        return "twitter/friends";
    }

    @RequestMapping(value = "/twitter/followers", method = RequestMethod.GET)
    public String followers(Model model) {
        log.trace("#followers: start");

        model.addAttribute("profiles", twitter.friendOperations().getFollowers());

        log.trace("#followers: end");
        return "twitter/friends";
    }

}
