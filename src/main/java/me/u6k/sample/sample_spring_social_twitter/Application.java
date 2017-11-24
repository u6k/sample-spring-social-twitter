
package me.u6k.sample.sample_spring_social_twitter;

import lombok.extern.slf4j.Slf4j;
import me.u6k.sample.sample_spring_social_twitter.social.SimpleSigninAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.connect.web.SignInAdapter;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SignInAdapter signInAdapter() {
        log.trace("#signInAdapter: start,end");

        return new SimpleSigninAdapter(new HttpSessionRequestCache());
    }

}
