
package me.u6k.sample.sample_spring_social_twitter;

import me.u6k.sample.sample_spring_social_twitter.social.SimpleSigninAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.connect.web.SignInAdapter;

@ComponentScan(basePackages = "me.u6k.sample.sample_spring_social_twitter")
@EnableConfigurationProperties
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SignInAdapter signInAdapter() {
        return new SimpleSigninAdapter(new HttpSessionRequestCache());
    }

}
