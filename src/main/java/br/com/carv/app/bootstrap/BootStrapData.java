package br.com.carv.app.bootstrap;

import br.com.carv.app.model.Address;
import br.com.carv.app.model.Author;
import br.com.carv.app.model.Book;
import br.com.carv.app.model.Publisher;
import br.com.carv.app.repository.AddressRepository;
import br.com.carv.app.repository.AuthorRepository;
import br.com.carv.app.repository.BookRepository;
import br.com.carv.app.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    private final AddressRepository addressRepository;
    private final Logger logger = Logger.getLogger(BootStrapData.class.getSimpleName());

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository, AddressRepository addressRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design", "123456789");
        author.getBooks().add(book);
        book.getAuthors().add(author);

        Author author1 = new Author("Rod", "Johnson");
        Book book1 = new Book("J2EE Development without EJB", "987654321");
        author.getBooks().add(book1);
        book.getAuthors().add(author1);

        Publisher publisher = new Publisher("Code House");
        Address address = new Address("Ribeirão do Sul", "São Paulo", "19930000");
        publisher.setAddress(address);
        publisher.getBooks().add(book);
        publisher.getBooks().add(book1);
        book1.setPublisher(publisher);
        book.setPublisher(publisher);

        publisherRepository.save(publisher);
        authorRepository.save(author);
        bookRepository.save(book);
        authorRepository.save(author1);
        bookRepository.save(book1);

        logger.info("Number of publishers: " + publisherRepository.count());
        logger.info("Number of addresses: " + addressRepository.count());
        logger.info("Started in Bootstrap");
        logger.info("Number of books: " + bookRepository.count());

    }
}
