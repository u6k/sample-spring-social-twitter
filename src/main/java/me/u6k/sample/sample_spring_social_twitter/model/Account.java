
package me.u6k.sample.sample_spring_social_twitter.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Account {

    private final String username;

    private final String password;

    private final String firstName;

    private final String lastName;

}
