
package me.u6k.sample.sample_spring_social_twitter.web;

public final class Message {

    private final MessageType type;

    private final String text;

    public Message(MessageType type, String text) {
        this.type = type;
        this.text = text;
    }

    public MessageType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return type + ": " + text;
    }

}
