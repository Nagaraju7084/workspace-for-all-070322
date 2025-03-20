package com.spring.web.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.web.domain.Author;
import com.spring.web.domain.Book;
import com.spring.web.domain.Publisher;
import com.spring.web.repositories.AuthorRepository;
import com.spring.web.repositories.BookRepository;
import com.spring.web.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	
    	System.out.println("started in bootstrap");
    	
    	Publisher publisher = new Publisher();
    	publisher.setName("sfg publishing");
    	publisher.setCity("hnk");
    	publisher.setState("ts");
    	
    	publisherRepository.save(publisher);
    	
    	System.out.println("publisher count"+publisherRepository.count());
    	
    	
        Author James = new Author("James","Gosling");
        Book book1 = new Book("Java","123123");
        James.getBooks().add(book1);
        book1.getAuthors().add(James);
        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);
        
        authorRepository.save(James);
        bookRepository.save(book1);
        publisherRepository.save(publisher);

        Author author2 = new Author("Dennis","Ritche");
        Book book2 = new Book("C language","321321");

        author2.getBooks().add(book2);
        book2.getAuthors().add(author2);
        book2.setPublisher(publisher);
        publisher.getBooks().add(book2);

        authorRepository.save(author2);
        bookRepository.save(book2);
        publisherRepository.save(publisher);

        System.out.println("started in bootstrap");
        System.out.println("number of books : "+bookRepository.count());
        System.out.println("publisher number of books "+publisher.getBooks().size());
        
    }
}
