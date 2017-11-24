
package me.u6k.sample.sample_spring_social_twitter.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Provider;

import me.u6k.sample.sample_spring_social_twitter.model.AccountRepository;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final Provider<ConnectionRepository> connectionRepositoryProvider;

    private final AccountRepository accountRepository;

    @Inject
    public HomeController(Provider<ConnectionRepository> connectionRepositoryProvider, AccountRepository accountRepository) {
        this.connectionRepositoryProvider = connectionRepositoryProvider;
        this.accountRepository = accountRepository;
    }

    @RequestMapping("/")
    public String home(Principal currentUser, Model model) {
        model.addAttribute("connectionsToProviders", getConnectionRepository().findAllConnections());
        if (currentUser != null) {
            model.addAttribute(accountRepository.findAccountByUsername(currentUser.getName()));
        }
        return "home";
    }

    private ConnectionRepository getConnectionRepository() {
        return connectionRepositoryProvider.get();
    }

}