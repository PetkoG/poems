package com.example.poem.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.poem.persistence.model.Poem;
import com.example.poem.persistence.repository.PoemService;
import com.example.poem.web.exception.PoemIdMismatchException;
import com.example.poem.web.exception.PoemNotFoundException;

@RestController
@RequestMapping("/api/poems")
public class PoemController {
	
	@Autowired
    private PoemService poemService;
	
	@GetMapping(value="/list")
    public ModelAndView listOfPoems(Model model, Pageable pageable) {
		ModelAndView mav = new ModelAndView("jsp/index");
        Page<Poem> pages = poemService.findAll(pageable);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());	
        model.addAttribute("poems", pages.getContent());
        model.addAttribute("allPoems", poemService.poemList());
        mav.addObject("model", model);
        return mav;
    }
	
	@GetMapping(value="/search")
	public String search(Model viewModel,
			@RequestParam(required = false, defaultValue="") String years,
			@RequestParam(required = false, defaultValue="") String title) {
				ArrayList<Poem> matched = new ArrayList<Poem>();
				System.out.println(years + "," + title);
				for(Poem p : poemService.poemList()) {
					System.out.println(p.getYear() + "," + p.getTitle());
					if(p.getTitle().equals(title) && p.getYear().equals(years)) {
						System.out.println(p);
						matched.add(p);
					}
				}
				return matched.size() != 0 ? matched.toString() : "There are no matched poems in our db";
	}
	
	@GetMapping
    public List<Poem> findAll() {
		for(int i = 0 ; i<=50 ; i++) {
			poemService.addPoem(new Poem("Test"+i,"Author","Text",""+i));
		}
        return poemService.poemList();
    }
	
	@RequestMapping(value = "/addPoem", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("jsp/addPoem", "poem", new Poem());
    }
	
	@RequestMapping(value = "/addPoem", method = RequestMethod.POST)
	public ModelAndView savePoem(@Valid @ModelAttribute("poem") Poem poem, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("jsp/addPoem");
		}
		poemService.addPoem(poem);
		return new ModelAndView("jsp/addPoem","message","Successfully added poem");
		}
	
	@GetMapping("/title/{poemTitle}")
    public List findByTitle(@PathVariable String poemTitle) {
        return poemService.findAllByTitle(poemTitle);
    }
 
    @GetMapping("/{id}")
    public Poem findOne(@PathVariable Long id) {
        return poemService.findOne(id)
          .orElseThrow(PoemNotFoundException::new);
    }
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Poem create(@RequestBody Poem poem) {
        return poemService.addPoem(poem);
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	poemService.findOne(id)
          .orElseThrow(PoemNotFoundException::new);
    	poemService.deletePoem(id);
    }
 
    @PutMapping("/{id}")
    public Poem updatePoem(@RequestBody Poem poem, @PathVariable Long id) {
        if (poem.getId() != id) {
          throw new PoemIdMismatchException();
        }
        poemService.findOne(id)
          .orElseThrow(PoemNotFoundException::new);
        return poemService.addPoem(poem);
    }

}
