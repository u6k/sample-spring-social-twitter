
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
public class FacebookFeedController {

    private final Facebook facebook;

    @Inject
    public FacebookFeedController(Facebook facebook) {
        log.trace("#ctor: start");

        this.facebook = facebook;

        log.trace("#ctor: end");
    }

    @RequestMapping(value = "/facebook/feed", method = RequestMethod.GET)
    public String showFeed(Model model) {
        log.trace("#showFeed: start");

        model.addAttribute("feed", facebook.feedOperations().getFeed());

        log.trace("#showFeed: end");
        return "facebook/feed";
    }

    @RequestMapping(value = "/facebook/feed", method = RequestMethod.POST)
    public String postUpdate(String message) {
        log.trace("#postUpdate: start: message={}", message);

        facebook.feedOperations().updateStatus(message);

        log.trace("#postUpdate: end");
        return "redirect:/facebook/feed";
    }

}
