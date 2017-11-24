
package me.u6k.sample.sample_spring_social_twitter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class TwitterTimelineController {

    @Autowired
    private Twitter twitter;

    @RequestMapping(value = "/twitter/timeline", method = RequestMethod.GET)
    public String showTimeline(Model model) {
        log.trace("#showTimeline: start");

        String result = showTimeline("Home", model);

        log.trace("#showTimeline: end: result={}", result);
        return result;
    }

    @RequestMapping(value = "/twitter/timeline/{timelineType}", method = RequestMethod.GET)
    public String showTimeline(@PathVariable("timelineType") String timelineType, Model model) {
        log.trace("#showTimeline: start: timelineType={}", timelineType);

        if (timelineType.equals("Home")) {
            model.addAttribute("timeline", twitter.timelineOperations().getHomeTimeline());
        } else if (timelineType.equals("User")) {
            model.addAttribute("timeline", twitter.timelineOperations().getUserTimeline());
        } else if (timelineType.equals("Mentions")) {
            model.addAttribute("timeline", twitter.timelineOperations().getMentions());
        } else if (timelineType.equals("Favorites")) {
            model.addAttribute("timeline", twitter.timelineOperations().getFavorites());
        }
        model.addAttribute("timelineName", timelineType);

        log.trace("#showTimeline: end");
        return "twitter/timeline";
    }

    @RequestMapping(value = "/twitter/tweet", method = RequestMethod.POST)
    public String postTweet(String message) {
        log.trace("#postTweet: start: message={}", message);

        log.trace("twitter update status: start");
        twitter.timelineOperations().updateStatus(message);
        log.trace("twitter update status: success");

        log.trace("#postTweet: end");
        return "redirect:/twitter";
    }

}
