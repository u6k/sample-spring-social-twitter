
package me.u6k.sample.sample_spring_social_twitter.controller;

import javax.inject.Inject;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterTrendsController {

    private static final long WORLDWIDE_WOE = 1L;

    private final Twitter twitter;

    @Inject
    public TwitterTrendsController(Twitter twitter) {
        this.twitter = twitter;
    }

    @RequestMapping(value = "/twitter/trends", method = RequestMethod.GET)
    public String showTrends(Model model) {
        model.addAttribute("trends", twitter.searchOperations().getLocalTrends(WORLDWIDE_WOE));
        return "twitter/trends";
    }

}
