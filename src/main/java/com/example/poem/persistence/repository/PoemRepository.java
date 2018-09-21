package com.example.poem.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.poem.persistence.model.Poem;

public interface PoemRepository extends PagingAndSortingRepository<Poem, Long> {
	
    List<Poem> findAllByTitle(String title);
    
    @Query("FROM Poem")
    List<Poem> poemList();
    
}
