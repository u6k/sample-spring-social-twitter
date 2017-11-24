
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
public class TwitterTrendsController {

    private static final long WORLDWIDE_WOE = 1L;

    @Autowired
    private Twitter twitter;

    @RequestMapping(value = "/twitter/trends", method = RequestMethod.GET)
    public String showTrends(Model model) {
        log.trace("#showTrends: start");

        model.addAttribute("trends", twitter.searchOperations().getLocalTrends(WORLDWIDE_WOE));

        log.trace("#showTrends: end");
        return "twitter/trends";
    }

}
