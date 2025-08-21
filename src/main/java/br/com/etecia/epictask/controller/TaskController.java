package br.com.etecia.epictask.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.etecia.epictask.model.Task;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    
    @GetMapping
    public String listTasks(Model model) {
        
        var tasks = List.of(
            new Task(1L, "Criar BD", "Criar banco mysql", 50, 0), 
            new Task(2L, "Prototipo", "Montar o figma", 40, 0), 
            new Task(1L, "Deploy", "Colocar em produção", 100, 0)
        );

        model.addAttribute("tasks", tasks);

        return "tasks";
    }

}
