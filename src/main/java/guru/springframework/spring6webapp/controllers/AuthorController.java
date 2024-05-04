package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/authors")
    public String authors(Model model) {
        model.addAttribute("authors", service.findAll());
        return "authors";
    }
}
