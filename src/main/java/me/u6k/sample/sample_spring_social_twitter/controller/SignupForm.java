
package me.u6k.sample.sample_spring_social_twitter.controller;

import javax.validation.constraints.Size;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;

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
        SignupForm form = new SignupForm();
        form.setFirstName(providerUser.getFirstName());
        form.setLastName(providerUser.getLastName());
        form.setUsername(providerUser.getUsername());
        return form;
    }

}
