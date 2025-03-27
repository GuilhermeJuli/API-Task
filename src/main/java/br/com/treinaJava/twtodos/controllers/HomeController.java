package br.com.treinaJava.twtodos.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.servlet.ModelAndView;

import br.com.treinaJava.twtodos.repositories.TodoRepository;

@Controller
public class HomeController {

    private final TodoRepository todoRepository;

    public HomeController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
   
    @GetMapping("/")
    public ModelAndView home() {
        var mv = new ModelAndView("home");
        mv.addObject("name", "Bem-vindo ao TW Todos");
        var alunos = List.of("Ana", "João", "Maria", "José", "Pedro", "Paula", "Carlos", "Mariana", "Fernanda", "Ricardo");
        mv.addObject("alunos", alunos);


        var todos = todoRepository.findAll();
        mv.addObject("todos", todos);

        return mv;
    }
    
    

}
