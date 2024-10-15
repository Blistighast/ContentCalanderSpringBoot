package com.brianguterl.content_calendar.controller;

import com.brianguterl.content_calendar.model.Content;
import com.brianguterl.content_calendar.repository.ContentCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;

    //autowired marks to be autowired by Springs dependency injector
    //is implicit if there is only 1 autowiring, like here, just to demonstrate
    @Autowired
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    //give path to call this method, blank, calls it from path above in RequestMapping
    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }
}
