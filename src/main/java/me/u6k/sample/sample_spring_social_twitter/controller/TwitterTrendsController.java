
package me.u6k.sample.sample_spring_social_twitter.controller;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class TwitterTrendsController {

    private static final long WORLDWIDE_WOE = 1L;

    private final Twitter twitter;

    @Inject
    public TwitterTrendsController(Twitter twitter) {
        log.trace("#ctor: start: twitter={}", twitter);

        this.twitter = twitter;

        log.trace("#ctor: end");
    }

    @RequestMapping(value = "/twitter/trends", method = RequestMethod.GET)
    public String showTrends(Model model) {
        log.trace("#showTrends: start");

        model.addAttribute("trends", twitter.searchOperations().getLocalTrends(WORLDWIDE_WOE));

        log.trace("#showTrends: end");
        return "twitter/trends";
    }

}
