package br.com.carv.app.controller;

import br.com.carv.app.repository.AuthorRepository;
import br.com.carv.app.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());

        return "author/list";
    }
}
