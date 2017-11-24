
package me.u6k.sample.sample_spring_social_twitter.web;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public final class Message {

    private final MessageType type;

    private final String text;

    public String toString() {
        return type + ": " + text;
    }

}
