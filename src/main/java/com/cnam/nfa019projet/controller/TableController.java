package com.cnam.nfa019projet.controller;

import com.cnam.nfa019projet.model.Table;
import com.cnam.nfa019projet.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TableController {

    //Creation du Repository
    @Autowired
    private TableRepository tableRepository ;

    /**
     * LISTE DES TABLES EN BASE DE DONNEE
     *
     * READ
     *
     */

    @GetMapping("/readTable")
    public String readTable(Model model) {
        List<Table> tableList = tableRepository.findAll();
        model.addAttribute("tableList", tableList);
        return "/Table/readTable";
    }

    /**
     * FORMULAIRE DE SAISIE D'UNE NOUVELLE TABLE
     *
     * CREATE
     *
     */

    @RequestMapping(value = {"/createTable"}, method = RequestMethod.GET)
    public String createTable(Model model) {
        Table aTable = new Table();
        model.addAttribute("aTable", aTable);
        return "/Table/createTable";
    }

    /**
     * SAUVEGARDE FORMULAIRE NOUVELLE TABLE
     *
     * SAVE
     *
     */

    @RequestMapping(value = {"/saveTable"}, method = RequestMethod.POST)
    public String saveTable(@ModelAttribute("aTable") Table aTable, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || aTable == null) {
            return "/error";
        } else {
            tableRepository.save(aTable);
            return "redirect:readTable";
        }
    }

    /**
     * PAGE DE UPDATE TABLE VIA ID
     *
     * UPDATE
     *
     */

    @GetMapping("/updateTable/{id}")
    public String updateTableFormulaire(@PathVariable("id") long id, Model model) {
        Table aTable = tableRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid table id:" + id));
        model.addAttribute("aTable", aTable);
        return "/Table/updateTable";
    }

    @PostMapping("/updateTable")
    public String updateTable(@Valid Table aTable, BindingResult result) {
        if (result.hasErrors() || aTable == null) {
            return "/error";
        }
        tableRepository.save(aTable);
        return "redirect:readTable";
    }

    /**
     * PAGE DE SUPPRESSION TABLE VIA ID
     *
     * DELETE
     *
     */

    @GetMapping("/verifDeleteTable/{id}")
    public String verifDeleteTable(@PathVariable("id") long id, Model model) {
        Table aTable = tableRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid table Id:" + id));
        boolean supr ;
        //Vérification si la table est allouée à certaines notes (donc suppression possible ou non)
        if(aTable.getNotes().isEmpty()){
            supr = true;
        } else {
            supr = false ;
        }
        model.addAttribute("aTable", aTable);
        model.addAttribute("supr", supr);
        return "/Table/deleteTable";
    }

    @GetMapping("/deleteTable/{id}")
    public String deleteTable(@PathVariable("id") long id, Model model) {
        Table aTable = tableRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid table Id:" + id));
        if(aTable != null) {
            tableRepository.delete(aTable);
            List<Table> tableList = tableRepository.findAll();
            model.addAttribute("tableList", tableList);
            return "/Table/readTable";
        } else {
            return "/error";
        }
    }


}

