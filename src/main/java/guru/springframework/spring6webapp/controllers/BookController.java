package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", service.findAll());
        return "books";
    }
}
