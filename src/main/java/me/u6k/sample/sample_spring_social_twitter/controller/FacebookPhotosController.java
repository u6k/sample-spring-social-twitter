
package me.u6k.sample.sample_spring_social_twitter.controller;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class FacebookPhotosController {

    private final Facebook facebook;

    @Inject
    public FacebookPhotosController(Facebook facebook) {
        log.trace("#ctor: start");

        this.facebook = facebook;

        log.trace("#ctor: end");
    }

    @RequestMapping(value = "/facebook/albums", method = RequestMethod.GET)
    public String showAlbums(Model model) {
        log.trace("#showAlbums: start");

        model.addAttribute("albums", facebook.mediaOperations().getAlbums());

        log.trace("#showAlbums: end");
        return "facebook/albums";
    }

    @RequestMapping(value = "/facebook/album/{albumId}", method = RequestMethod.GET)
    public String showAlbum(@PathVariable("albumId") String albumId, Model model) {
        log.trace("#showAlbum: start: albumId={}", albumId);

        model.addAttribute("album", facebook.mediaOperations().getAlbum(albumId));
        model.addAttribute("photos", facebook.mediaOperations().getPhotos(albumId));

        log.trace("#showAlbum: end");
        return "facebook/album";
    }

}
