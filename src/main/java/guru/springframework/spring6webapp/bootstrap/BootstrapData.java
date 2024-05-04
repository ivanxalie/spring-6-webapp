package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @PostConstruct
    public void run() {
        Author eric = new Author();
        eric.setFistName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123466");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFistName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("567890");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        noEJBSaved.getAuthors().add(rodSaved);
        dddSaved.getAuthors().add(ericSaved);

        Publisher oReally = new Publisher();
        oReally.setName("O'Really");
        oReally.setCity("Sebastopol");
        oReally.setAddress("""
                1005 Gravenstein Highway North
                Sebastopol, CA 95472
                USA
                """);
        oReally.setZipCode("1005");
        oReally.setState("CA");

        Publisher savedPublisher = publisherRepository.save(oReally);

        dddSaved.setPublisher(savedPublisher);
        noEJB.setPublisher(savedPublisher);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.printf(
                """
                        In Bootstrap:
                        Publisher Count: %s
                        Author Count: %s
                        Book Count: %s
                        %n""",
                publisherRepository.count(), authorRepository.count(), bookRepository.count());
    }
}
