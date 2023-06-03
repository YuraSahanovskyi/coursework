package com.example.coursework.controllers;

import com.example.coursework.model.Telecast;
import com.example.coursework.services.TelecastService;
import com.example.coursework.services.TelecastSortCriteria;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/telecast")
public class TelecastController {
    private final TelecastService telecastService;

    public TelecastController(TelecastService telecastService) {
        this.telecastService = telecastService;
    }

    @GetMapping()
    protected String telecast(Model model, @RequestParam(value = "sort", required = false) String sort) {
        Iterable<Telecast> telecasts;
        if (sort != null) {
            TelecastSortCriteria sortCriteria = TelecastSortCriteria.valueOf(sort.toUpperCase().substring(1, sort.length() - 1));
            telecasts = telecastService.getAllTelecasts(sortCriteria);
        } else {
            telecasts = telecastService.getAllTelecasts();
        }
        model.addAttribute("telecasts", telecasts);
        return "telecast";
    }

    @GetMapping("/add")
    protected String addForm() {
        return "add";
    }

    @PostMapping("/add")
    protected String add(@RequestParam String name,
                         @RequestParam String chanel,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDateTime,
                         @RequestParam String description) {
        Telecast telecast = new Telecast(name, chanel, startDateTime, description);
        telecastService.add(telecast);
        return "redirect:/telecast/add";
    }

    @GetMapping("/edit/{id}")
    protected String editForm(@PathVariable(value = "id") long id, Model model) {
        if (!telecastService.isPresent(id)) {
            return "redirect:/telecast";
        }
        Telecast telecast = telecastService.getTelecastById(id);
        model.addAttribute("telecast", telecast);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    protected String edit(@RequestParam String name,
                          @RequestParam String chanel,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDateTime,
                          @RequestParam String description,
                          @PathVariable(value = "id") long id) {
        telecastService.edit(id, name, chanel, startDateTime, description);
        return "redirect:/telecast";
    }

    @PostMapping("/delete/{id}")
    protected String delete(@PathVariable(value = "id") long id) {
        Telecast telecast = telecastService.getTelecastById(id);
        telecastService.delete(telecast);
        return "redirect:/telecast";
    }

}