
package me.u6k.sample.sample_spring_social_twitter.exception;

@SuppressWarnings("serial")
public class UsernameAlreadyInUseException extends Exception {

    public UsernameAlreadyInUseException(String username) {
        super("The username '" + username + "' is already in use.");
    }

}
