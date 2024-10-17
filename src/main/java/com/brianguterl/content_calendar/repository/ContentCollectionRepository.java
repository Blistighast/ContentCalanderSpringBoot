package com.brianguterl.content_calendar.repository;

import com.brianguterl.content_calendar.model.Content;
import com.brianguterl.content_calendar.model.Status;
import com.brianguterl.content_calendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {
    }

    public List<Content> findAll() {
        return contentList;
    }

    //lets you deal with nulls easily, item may or may not exist
    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content) {
        contentList.add(content);
    }

    @PostConstruct
    private void init() {
        Content c = new Content(1,
                "My first blog",
                "The description",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
                );

        Content b = new Content( 2,
        "Title",
        "description",
        Status.IN_PROGRESS,
        Type.VIDEO,
        LocalDateTime.now(),
        null,
        "");

        contentList.add(c);
        contentList.add(b);
    }
}
