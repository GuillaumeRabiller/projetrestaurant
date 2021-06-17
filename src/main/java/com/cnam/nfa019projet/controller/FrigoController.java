package com.cnam.nfa019projet.controller;

import com.cnam.nfa019projet.form.HistoriqueTemp;
import com.cnam.nfa019projet.model.Frigo;
import com.cnam.nfa019projet.model.ReleveTemp;
import com.cnam.nfa019projet.repository.FrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class FrigoController {

    //Création d'un FrigoRepository

    @Autowired
    private FrigoRepository frigoRepository ;


    /**
     * LISTE DES FRIGOS EN BASE DE DONNEE
     *
     * READ
     *
     */

    @GetMapping("/readFrigo")
    public String readFrigo(Model model) {
        List<Frigo> frigoList = frigoRepository.findAll();
        model.addAttribute("frigoList", frigoList);
        return "/Frigo/readFrigo";
    }

    /**
     * FORMULAIRE DE SAISIE D'UN NOUVEAU FRIGO
     *
     * CREATE
     *
     */

    @RequestMapping(value = {"/createFrigo"}, method = RequestMethod.GET)
    public String createFrigo(Model model) {
        Frigo aFrigo = new Frigo();
        model.addAttribute("aFrigo", aFrigo);
        return "/Frigo/createFrigo";
    }

    /**
     * SAUVEGARDE FORMULAIRE NOUVEAU FRIGO
     *
     * SAVE
     *
     */

    @RequestMapping(value = {"/saveFrigo"}, method = RequestMethod.POST)
    public String saveFrigo(@ModelAttribute("aFrigo") Frigo aFrigo, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || aFrigo == null) {
            return "/error";
        } else {
            frigoRepository.save(aFrigo);
            return "redirect:readFrigo";
        }
    }

    /**
     * PAGE DE UPDATE FRIGO VIA ID
     *
     * UPDATE
     *
     */

    @GetMapping("/updateFrigo/{id}")
    public String updateFrigoFormulaire(@PathVariable("id") long id, Model model) {
        Frigo frigo = frigoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid frigo id:" + id));
        model.addAttribute("aFrigo", frigo);
        return "/Frigo/updateFrigo";
    }

    @PostMapping("/updateFrigo")
    public String updateFrigo(@Valid Frigo aFrigo, BindingResult result) {
        if (result.hasErrors() || aFrigo == null) {
            return "/error";
        }
        frigoRepository.save(aFrigo);
        return "redirect:readFrigo";
    }

    /**
     * PAGE DE SUPPRESSION FRIGO VIA ID
     *
     * VERIFICATION
     *
     */


    @GetMapping("/verifDeleteFrigo/{id}")
    public String verifDeleteFrigo(@PathVariable("id") long id, Model model) {
        Frigo aFrigo = frigoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid frigo Id:" + id));
        //Vérif si le frigo est alloué à des températures
        boolean supr ;
        supr = aFrigo.getRelevesTemp().isEmpty();

        model.addAttribute("aFrigo", aFrigo);
        model.addAttribute("supr", supr);
        return "/Frigo/deleteFrigo";
    }

    /**
     * PAGE DE SUPPRESSION FRIGO VIA ID
     *
     * DELETE
     *
     */

    @GetMapping("/deleteFrigo/{id}")
    public String deleteFrigo(@PathVariable("id") long id, Model model) {
        Frigo aFrigo = frigoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid frigo Id:" + id));
        if(aFrigo != null) {
            frigoRepository.delete(aFrigo);
            List<Frigo> frigoList = frigoRepository.findAll();
            model.addAttribute("frigoList", frigoList);
            return "/Frigo/readFrigo";
        } else {
            return "/error";
        }
    }

    /**
     * PAGE D HISTORIQUE DE TEMPERATURE D'UN FRIGO
     *
     */

    @GetMapping("/historiqueFrigo/{id}")
    public String historiqueFrigo(@PathVariable("id") long id, Model model) {
        Frigo frigo = frigoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid frigo id:" + id));
        model.addAttribute("nomFrigo", frigo.getNomFrigo());
        List<HistoriqueTemp> historiqueList = new ArrayList<>();
        for (ReleveTemp releve: frigo.getRelevesTemp()) {
            HistoriqueTemp historique = new HistoriqueTemp();
            historique.setDate(releve.getDateEnregTemp().toLocalDate());
            historique.setHeure(releve.getDateEnregTemp().toLocalTime().truncatedTo(ChronoUnit.MINUTES));
            historique.setTemp(releve.getTemperature());
            historique.setUtilisateur(releve.getNomUtilisateur());
            //on met à jour les boolean pour savoir si la temp dépasse ou non les seuils de temp du frigo
            if(releve.getTemperature() < frigo.getTempMini()){
                historique.setTempDown(true);
            }
            if(releve.getTemperature() > frigo.getTempMaxi()){
                historique.setTempUp(true);
            }
            historiqueList.add(historique) ;
        }
        //on trie par date
        historiqueList.sort(Comparator.comparing(HistoriqueTemp::getDate));

        model.addAttribute("tempList", historiqueList) ;
        return "/Frigo/historiqueFrigo";
    }


}
