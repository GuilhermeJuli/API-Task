package br.com.treinaJava.twtodos.controllers;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.servlet.ModelAndView;

import br.com.treinaJava.twtodos.models.Todo;
import br.com.treinaJava.twtodos.repositories.TodoRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;



@Controller
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
   
    @GetMapping("/")
    public ModelAndView list() {
        return new ModelAndView(
            "todo/list",
            Map.of("todos", todoRepository.findAll(Sort.by("deadline").descending()))
        );
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("todo/form", 
        Map.of("todo", new Todo()));
    }

    @PostMapping("/create")
    public String create(@Valid Todo todo , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "todo/form";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var todo = todoRepository.findById(id);
        if (todo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo não encontrado");
        }
        return new ModelAndView("todo/form",
            Map.of("todo", todo.get())
        );
    }
    
    @PostMapping("/edit/{id}")
    public String edit(@Valid Todo todo, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "todo/form";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
       var todo = todoRepository.findById(id);
        if (todo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        todoRepository.delete(todo.get());
        return "redirect:/";
    }

    @PostMapping("/finish/{id}")
    public String finish(@PathVariable Long id) {
    var todo = todoRepository.findById(id);
    if (todo.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
    }
    
    var todoEntity = todo.get();
    if (todoEntity.getDeadline().isBefore(LocalDate.now())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de entrega não pode ser no passado");
    }
    
    todoEntity.finish();
    todoRepository.save(todoEntity);
    return "redirect:/";
}



}
