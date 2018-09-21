package com.example.poem.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.poem.persistence.model.Poem;

@Service
@CacheConfig(cacheNames = { "poemCache" } )
public class PoemServiceImpl implements PoemService {
	
	@Autowired
	private PoemRepository poemRepository;

	@Override
	@Cacheable
	public List<Poem> poemList() {
		System.out.println("were in method");
		return poemRepository.poemList();
	}

	@Override
	@Cacheable
	public Page<Poem> findAll(Pageable pageable) {
		return poemRepository.findAll(pageable);
	}

	@Override
	public Optional<Poem> findOne(Long id) {
		return poemRepository.findById(id);
	}

	@Override
	@CacheEvict(allEntries = true)
	public Poem addPoem(Poem poem) {
		return poemRepository.save(poem);
	}

	@Override
	public void deletePoem(Long id) {
	  poemRepository.deleteById(id);
	}

	@Override
	@CacheEvict(allEntries = true)
	public void refreshCache() {
		
	}

	@Override
	public List<Poem> findAllByTitle(String title) {
		return poemRepository.findAllByTitle(title);
	}

}
