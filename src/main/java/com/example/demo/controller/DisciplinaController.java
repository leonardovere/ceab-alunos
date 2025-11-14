package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Disciplina;
import com.example.demo.repository.DisciplinaRepository;
import com.example.demo.repository.TurmaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("disciplinas", disciplinaRepository.findAll());
        model.addAttribute("menuAtivo", "disciplinas");
        
        return "disciplinas/listar";
    }
    
    @GetMapping("/nova")
    public String novadisciplina(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("menuAtivo", "disciplinas");
        
        return "disciplinas/salvar";
    }
    
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute @NonNull Disciplina disciplina, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("menuAtivo", "disciplinas");
            model.addAttribute("turmas", turmaRepository.findAll());
            
            return "disciplinas/salvar";
        }
        
        disciplinaRepository.save(disciplina);
        return "redirect:/disciplinas";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable @NonNull Long id, Model model) {
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(id);
        
        if (disciplinaOpt.isEmpty()) {
            return "redirect:/disciplinas";
        }
        
        model.addAttribute("disciplina", disciplinaOpt.get());
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("menuAtivo", "disciplinas");
        
        return "disciplinas/salvar";
    }
    
    @PostMapping("/remover/{id}")
    public String remover(@PathVariable @NonNull Long id) {
        disciplinaRepository.deleteById(id);
        return "redirect:/disciplinas";
    }
}