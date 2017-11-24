
package me.u6k.sample.sample_spring_social_twitter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.RateLimitExceededException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler(RateLimitExceededException.class)
    public String rateLimitExceeded(RateLimitExceededException e, Model model) {
        log.trace("#rateLimitExceeded: start: e={}", e);

        model.addAttribute("providerId", e.getProviderId());

        log.trace("#rateLimitExceeded: end");
        return "ratelimit";
    }

}
