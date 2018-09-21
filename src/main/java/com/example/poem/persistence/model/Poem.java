package com.example.poem.persistence.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

@Entity
public class Poem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 
    @Column(nullable = false)
    @NotBlank
    private String title;
 
    @Column(nullable = false)
    @NotBlank
    private String author;
    
    @Column(nullable = false)
    @NotBlank
    private String text;
    
    @Column(name = "year", nullable = false)
    @NotBlank
    private String year;
    
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    
    @PrePersist
    public void prePersist() {
    	updatedOn = LocalDateTime.now();
    }
 
    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

	public Poem() {
		super();
	}

	public Poem( String title, String author, String text, String year) {
		this.title = title;
		this.author = author;
		this.text = text;
		this.year = year;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Poem [id=" + id + ", title=" + title + ", author=" + author + ", text=" + text + ", year=" + year
				+ ", updatedOn=" + updatedOn + "]";
	}

	
	
}
