package com.brianguterl.content_calendar.repository;

import com.brianguterl.content_calendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

// extension gives it pre-made crud functions, just need to give it what's in it "Content"
// and the identifier type "Integer" for id
//ListCrud gives returns as list, vs iterables from CrudRepository, ListCrud extends Crud
//doesn't need @Repository due to it being interface, at runtime it converts it
public interface ContentRepository extends ListCrudRepository<Content, Integer> {
}
