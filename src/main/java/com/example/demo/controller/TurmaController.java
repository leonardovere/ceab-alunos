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

import com.example.demo.model.Turma;
import com.example.demo.repository.TurmaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("menuAtivo", "turmas");

        return "turmas/listar";
    }

    @GetMapping("/nova")
    public String novaTurma(Model model) {
        model.addAttribute("turma", new Turma());
        model.addAttribute("menuAtivo", "turmas");

        return "turmas/salvar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute @NonNull Turma turma, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("menuAtivo", "turmas");
            return "turmas/salvar";
        }

        Optional<Turma> outra = turmaRepository.findByAnoAndTipoAndIdentificacao(turma.getAno(), turma.getTipo(),
                turma.getIdentificacao());

        if (outra.isPresent() && !turma.equals(outra.get())) {
            model.addAttribute("menuAtivo", "turmas");
            model.addAttribute("erro", "Já existe uma turma com esses dados.");

            return "turmas/salvar";
        }

        turmaRepository.save(turma);
        return "redirect:/turmas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable @NonNull Long id, Model model) {
        Optional<Turma> turmaOpt = turmaRepository.findById(id);

        if (turmaOpt.isEmpty()) {
            return "redirect:/turmas";
        }

        model.addAttribute("turma", turmaOpt.get());
        model.addAttribute("menuAtivo", "turmas");

        return "turmas/salvar";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable @NonNull Long id) {
        turmaRepository.deleteById(id);
        return "redirect:/turmas";
    }
}