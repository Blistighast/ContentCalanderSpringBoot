package com.brianguterl.content_calendar.controller;

import com.brianguterl.content_calendar.model.Content;
import com.brianguterl.content_calendar.model.Status;
import com.brianguterl.content_calendar.repository.ContentCollectionRepository;
import com.brianguterl.content_calendar.repository.ContentJdbcTemplateRepository;
import com.brianguterl.content_calendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin("http://localhost:5173/")
public class ContentController {

    private final ContentRepository repository;

//    private final ContentCollectionRepository repository;

//    private final ContentJdbcTemplateRepository repository;

    //autowired marks to be autowired by Springs dependency injector
    //is implicit if there is only 1 autowiring, like here, just to demonstrate
    @Autowired
    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }

    //give path to call this method, blank, calls it from path above in RequestMapping
    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content) {
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content, @PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword) {
        return repository.findAllByTitleContains(keyword);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findByStatus(@PathVariable Status status) {
        return repository.listByStatus(status);
    }
}
