package br.com.etecia.epictask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.etecia.epictask.model.Task;
import br.com.etecia.epictask.repository.TaskRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    //private List<Task> repository = new ArrayList<>();

    @Autowired
    private TaskRepository repository;
    
    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", repository.findAll());
        return "tasks";
    }

    @GetMapping("/form")
    public String showForm(Task task){
        return "form";
    }

    @PostMapping("/form")
    public String create(@Valid Task task, BindingResult result, RedirectAttributes redirect ){
        if(result.hasErrors()) return "form";

        System.out.println("Cadastrando tarefa..." + task);
        repository.save(task);
        redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso");
        return "redirect:/tasks";
    }


}
