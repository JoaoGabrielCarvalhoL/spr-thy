package br.com.carv.app.controller;

import br.com.carv.app.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());

        return "book/list";
    }
}
