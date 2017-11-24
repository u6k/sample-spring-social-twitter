
package me.u6k.sample.sample_spring_social_twitter.model;

import me.u6k.sample.sample_spring_social_twitter.exception.UsernameAlreadyInUseException;

public interface AccountRepository {

    void createAccount(Account account) throws UsernameAlreadyInUseException;

    Account findAccountByUsername(String username);

}
