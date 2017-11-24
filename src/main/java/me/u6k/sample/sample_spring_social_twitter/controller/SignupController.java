
package me.u6k.sample.sample_spring_social_twitter.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import me.u6k.sample.sample_spring_social_twitter.exception.UsernameAlreadyInUseException;
import me.u6k.sample.sample_spring_social_twitter.model.Account;
import me.u6k.sample.sample_spring_social_twitter.model.AccountRepository;
import me.u6k.sample.sample_spring_social_twitter.social.SigninUtils;
import me.u6k.sample.sample_spring_social_twitter.web.Message;
import me.u6k.sample.sample_spring_social_twitter.web.MessageType;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignupController {

    private final AccountRepository accountRepository;

    private final ProviderSignInUtils providerSignInUtils;

    @Inject
    public SignupController(AccountRepository accountRepository,
        ConnectionFactoryLocator connectionFactoryLocator,
        UsersConnectionRepository connectionRepository) {
        this.accountRepository = accountRepository;
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public SignupForm signupForm(WebRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
            return SignupForm.fromProviderUser(connection.fetchUserProfile());
        } else {
            return new SignupForm();
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
        if (formBinding.hasErrors()) {
            return null;
        }
        Account account = createAccount(form, formBinding);
        if (account != null) {
            SigninUtils.signin(account.getUsername());
            providerSignInUtils.doPostSignUp(account.getUsername(), request);
            return "redirect:/";
        }
        return null;
    }

    private Account createAccount(SignupForm form, BindingResult formBinding) {
        try {
            Account account = new Account(form.getUsername(), form.getPassword(), form.getFirstName(), form.getLastName());
            accountRepository.createAccount(account);
            return account;
        } catch (UsernameAlreadyInUseException e) {
            formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
            return null;
        }
    }

}
