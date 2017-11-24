
package me.u6k.sample.sample_spring_social_twitter.web;

public enum MessageType {

    INFO, SUCCESS, WARNING, ERROR;

    private final String cssClass;

    private MessageType() {
        cssClass = name().toLowerCase();
    }

    public String getCssClass() {
        return cssClass;
    }

}
