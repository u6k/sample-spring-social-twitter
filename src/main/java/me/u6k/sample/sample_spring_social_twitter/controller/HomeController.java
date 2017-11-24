
package me.u6k.sample.sample_spring_social_twitter.controller;

import java.security.Principal;

import javax.inject.Provider;

import lombok.extern.slf4j.Slf4j;
import me.u6k.sample.sample_spring_social_twitter.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private Provider<ConnectionRepository> connectionRepositoryProvider;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/")
    public String home(Principal currentUser, Model model) {
        log.trace("#home: start: currentUser={}", currentUser);

        model.addAttribute("connectionsToProviders", getConnectionRepository().findAllConnections());
        if (currentUser != null) {
            model.addAttribute(accountRepository.findAccountByUsername(currentUser.getName()));
        }

        log.trace("#home: end");
        return "home";
    }

    private ConnectionRepository getConnectionRepository() {
        return connectionRepositoryProvider.get();
    }

}
