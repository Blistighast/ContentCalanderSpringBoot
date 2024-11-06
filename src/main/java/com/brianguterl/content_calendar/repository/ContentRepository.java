package com.brianguterl.content_calendar.repository;

import com.brianguterl.content_calendar.model.Content;
import com.brianguterl.content_calendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// extension gives it pre-made crud functions, just need to give it what's in it "Content"
// and the identifier type "Integer" for id
//ListCrud gives returns as list, vs iterables from CrudRepository, ListCrud extends Crud
//doesn't need @Repository due to it being interface, at runtime it converts it
public interface ContentRepository extends ListCrudRepository<Content, Integer> {

    //this method is created at runtime for us by Spring
    List<Content> findAllByTitleContains(String keyword);

    //creating this one ourselves
    @Query("""
            SELECT * FROM Content
            where status = :status
            """)
    List<Content> listByStatus(@Param("status") Status status);
}
