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

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.TurmaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("menuAtivo", "alunos");
        
        return "alunos/listar";
    }
    
    @GetMapping("/novo")
    public String novaaluno(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("menuAtivo", "alunos");
        
        return "alunos/salvar";
    }
    
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute @NonNull Aluno aluno, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("menuAtivo", "alunos");
            model.addAttribute("turmas", turmaRepository.findAll());
            
            return "alunos/salvar";
        }
        
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable @NonNull Long id, Model model) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id);
        
        if (alunoOpt.isEmpty()) {
            return "redirect:/alunos";
        }
        
        model.addAttribute("aluno", alunoOpt.get());
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("menuAtivo", "alunos");
        
        return "alunos/salvar";
    }
    
    @PostMapping("/remover/{id}")
    public String remover(@PathVariable @NonNull Long id) {
        alunoRepository.deleteById(id);
        return "redirect:/alunos";
    }
}