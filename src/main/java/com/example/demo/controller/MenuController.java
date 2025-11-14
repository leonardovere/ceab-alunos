package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("menuAtivo", "index");
        
        return "index";
    }
    
    @GetMapping("/listar-conteudos")
    public String conteudos(Model model) {
        model.addAttribute("menuAtivo", "conteudos");
        
        return "conteudos/pesquisa-conteudos";
    }
    
    @GetMapping("/listar-professores")
    public String professores(Model model) {
        model.addAttribute("menuAtivo", "professores");
        
        return "professores/pesquisa-professores";
    }

    @GetMapping("/listar-aulas")
    public String aulas(Model model) {
        model.addAttribute("menuAtivo", "aulas");
        
        return "diario/pesquisa-aulas";
    }
    
    @GetMapping("/listar-frequencia")
    public String frequencia(Model model) {
        model.addAttribute("menuAtivo", "frequencia");
        
        return "diario/pesquisa-frequencia";
    }
    
    @GetMapping("/listar-avaliacoes")
    public String avaliacoes(Model model) {
        model.addAttribute("menuAtivo", "avaliacoes");
        
        return "diario/pesquisa-avaliacoes";
    }
}
