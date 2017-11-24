
package me.u6k.sample.sample_spring_social_twitter.controller;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;

@Slf4j
@Data
public class SignupForm {

    @NotEmpty
    private String username;

    @Size(min = 6, message = "must be at least 6 characters")
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    public static SignupForm fromProviderUser(UserProfile providerUser) {
        log.trace("#fromProviderUser: start");

        SignupForm form = new SignupForm();
        form.setFirstName(providerUser.getFirstName());
        form.setLastName(providerUser.getLastName());
        form.setUsername(providerUser.getUsername());

        log.trace("#fromProviderUser: end: result={}", form);
        return form;
    }

}
