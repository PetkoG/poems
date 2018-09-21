package com.example.poem.persistence.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.poem.persistence.model.Poem;

public interface PoemService {
	
	List<Poem> poemList();
	
	Page<Poem> findAll(Pageable pageable);
	
	Optional<Poem> findOne(Long id);
	
	List<Poem> findAllByTitle(String title);
	
	Poem addPoem(Poem poem);
	
	void deletePoem(Long id);
	
	void refreshCache();

}
