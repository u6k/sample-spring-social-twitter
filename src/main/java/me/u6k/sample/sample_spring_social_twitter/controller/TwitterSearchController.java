
package me.u6k.sample.sample_spring_social_twitter.controller;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class TwitterSearchController {

    private final Twitter twitter;

    @Inject
    public TwitterSearchController(Twitter twitter) {
        log.trace("#ctor: start");

        this.twitter = twitter;

        log.trace("#ctor: end");
    }

    @RequestMapping(value = "/twitter/search", method = RequestMethod.GET)
    public String showTrends(@RequestParam("query") String query, Model model) {
        log.trace("#showTrends: start: query={}", query);

        model.addAttribute("timeline", twitter.searchOperations().search(query).getTweets());

        log.trace("#showTrends: end");
        return "twitter/timeline";
    }

}
