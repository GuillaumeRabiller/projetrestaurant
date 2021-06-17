package com.cnam.nfa019projet.controller;

import com.cnam.nfa019projet.repository.FrigoRepository;
import com.cnam.nfa019projet.service.FrigoService;
import com.cnam.nfa019projet.service.NoteService;
import com.cnam.nfa019projet.service.StockService;
import com.cnam.nfa019projet.form.CreateTemp;
import com.cnam.nfa019projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    //Instanciation des Reposittory et Service

    @Autowired
    private FrigoService frigoService ;

    @Autowired
    private StockService stockService ;

    @Autowired
    private FrigoRepository frigoRepository;

    @Autowired
    private UtilisateurService utilisateurService ;

    @Autowired
    private NoteService noteService;


    /**
     * SPRING SECURITY
     *
     * LOGIN PAGE
     */

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }



    /**
     * PAGE INDEX AVEC REDIRECTION EN FONCTION DU RÔLE
     *
     * READ
     *
     *
     */

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String home() {
        String roleUtilisateur = utilisateurService.getRoleUser();
        switch (roleUtilisateur) {
            case "ROLE_ADMIN" : return "redirect:indexAdmin" ;
            case "ROLE_UTILISATEUR" : return "redirect:indexUtil" ;
            case "ROLE_SERVEUR" : return "redirect:indexServeur" ;
            default : return "redirect:login" ;
        }
    }


    /**
     * PAGE INDEX ADMINISTRATEUR AVEC TABLEAU DE BORD
     *
     * READ
     *
     *
     */

    @RequestMapping(value = { "/indexAdmin"}, method = RequestMethod.GET)
    public String homeAdmin(Model model) {
        model.addAttribute("stockList", stockService.listeStock());
        model.addAttribute("tempList", frigoService.lastTempList());

        //Gestion du popup renseignement temp
        CreateTemp aTemp = new CreateTemp();
        aTemp.setFrigoList(frigoRepository.findAll());
        model.addAttribute("aTemp", aTemp);

        return "/indexAdmin";
    }


    /**
     * PAGE INDEX UTILISATEUR AVEC TABLEAU DE BORD
     *
     * READ
     *
     *
     */

    @RequestMapping(value = {"/indexUtil"}, method = RequestMethod.GET)
    public String homeUtil(Model model) {
        model.addAttribute("stockList", stockService.listeStock());
        model.addAttribute("tempList", frigoService.lastTempList());

        //Gestion du popup renseignement temp
        CreateTemp aTemp = new CreateTemp();
        aTemp.setFrigoList(frigoRepository.findAll());
        model.addAttribute("aTemp", aTemp);

        return "/indexUtil";
    }


    /**
     * PAGE INDEX SERVEUR
     *
     * READ
     *
     *
     */

    @RequestMapping(value = { "/indexServeur"}, method = RequestMethod.GET)
    public String homeServeur(Model model) {
        model.addAttribute("noteEnCours", noteService.nbNoteEnCours());
        model.addAttribute("tableDispo", noteService.nbTableDispo());
        return "/indexServeur";
    }


    /**
     * PAGE ABOUT
     *
     *
     */

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String about() {
        return "/about";
    }



}
