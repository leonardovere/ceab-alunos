package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Getter;

@Controller
@Getter
public class MenuController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("menuAtivo", "index");
        
        return "index";
    }
    
    @GetMapping("/turmas")
    public String turmas(Model model) {
        model.addAttribute("menuAtivo", "turmas");
        
        return "pesquisa-turmas";
    }
    
    @GetMapping("/disciplinas")
    public String disciplinas(Model model) {
        model.addAttribute("menuAtivo", "disciplinas");
        
        return "pesquisa-disciplinas";
    }
    
    @GetMapping("/conteudos")
    public String conteudos(Model model) {
        model.addAttribute("menuAtivo", "conteudos");
        
        return "conteudos/pesquisa-conteudos";
    }
    
    @GetMapping("/professores")
    public String professores(Model model) {
        model.addAttribute("menuAtivo", "professores");
        
        return "professores/pesquisa-professores";
    }
    
    @GetMapping("/alunos")
    public String alunos(Model model) {
        model.addAttribute("menuAtivo", "alunos");
        
        return "alunos/pesquisa-alunos";
    }
    
    @GetMapping("/frequencia")
    public String frequencia(Model model) {
        model.addAttribute("menuAtivo", "frequencia");
        
        return "alunos/pesquisa-frequencia";
    }
    
    @GetMapping("/avaliacoes")
    public String avaliacoes(Model model) {
        model.addAttribute("menuAtivo", "avaliacoes");
        
        return "alunos/pesquisa-avaliacoes";
    }
}
