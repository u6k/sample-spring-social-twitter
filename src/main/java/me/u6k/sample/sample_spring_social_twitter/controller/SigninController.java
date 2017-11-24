
package me.u6k.sample.sample_spring_social_twitter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class SigninController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public void signin() {
        log.trace("#signin: start,end");
    }

}
