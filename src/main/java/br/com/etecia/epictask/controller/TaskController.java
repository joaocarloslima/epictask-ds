package br.com.etecia.epictask.controller;

import br.com.etecia.epictask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private UserService userService;

    @GetMapping
    public String listTasks(Model model, @AuthenticationPrincipal OAuth2User user) {
        model.addAttribute("tasks", repository.findByStatusLessThan(100));
        model.addAttribute("user", user);
    
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

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect ){
        System.out.println("Deletando tarefa " + id);
        repository.deleteById(id);
        redirect.addFlashAttribute("message", "Tarefa deletada");
        return "redirect:/tasks";
    }

    @PutMapping("{id}/catch")
    public String catchTask(@PathVariable Long id, @AuthenticationPrincipal OAuth2User oauth2User){
        var task = repository.findById(id).get();
        var user = userService.register(oauth2User);
        task.setUser(user);
        repository.save(task);
        return "redirect:/tasks";
    }

    @PutMapping("{id}/add")
    public String addStatus(@PathVariable Long id){
        var task = repository.findById(id).get();
        task.setStatus(task.getStatus() + 10);
        repository.save(task);
        return "redirect:/tasks";
    }


}
