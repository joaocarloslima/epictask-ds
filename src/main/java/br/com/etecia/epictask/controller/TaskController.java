package br.com.etecia.epictask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.etecia.epictask.model.Task;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> repository = new ArrayList<>();
    
    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", repository);
        return "tasks";
    }

    @GetMapping("/form")
    public String showForm(Task task){
        return "form";
    }

    @PostMapping("/form")
    public String create(Task task){
        System.out.println("Cadastrando tarefa..." + task);
        repository.add(task);
        return "form";
    }


}
