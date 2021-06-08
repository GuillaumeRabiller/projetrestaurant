package com.cnam.nfa019projet.controller;

import com.cnam.nfa019projet.form.*;
import com.cnam.nfa019projet.model.*;
import com.cnam.nfa019projet.repository.CategoriePlatRepository;
import com.cnam.nfa019projet.repository.NoteRepository;
import com.cnam.nfa019projet.repository.PlatRepository;
import com.cnam.nfa019projet.repository.TableRepository;
import com.cnam.nfa019projet.service.NoteService;
import com.cnam.nfa019projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class NoteController {

    //Instanciation des Repository et Services+

    @Autowired
    private TableRepository tableRepository ;

    @Autowired
    private UtilisateurService utilisateurService ;

    @Autowired
    private NoteRepository noteRepository ;

    @Autowired
    private CategoriePlatRepository categoriePlatRepository ;

    @Autowired
    private PlatRepository platRepository ;

    @Autowired
    private NoteService noteService ;

    /**
     * FORMULAIRE DE SAISIE CREATION NOUVELLE NOTE
     *
     * CREATE
     *
     *
     */

    @RequestMapping(value = {"createNote"}, method = RequestMethod.GET)
    public String createNote(Model model){
        CreateNote aNote = new CreateNote();
        model.addAttribute("aNote", aNote);
        List<Table> tableList = tableRepository.findAll();
        model.addAttribute("tables", tableList);
        return "/Note/createNote";
    }

    /**
     * SAUVEGARDE NOUVELLE NOTE
     *
     * SAVE
     *
     */

    @RequestMapping(value = {"saveNote"}, method = RequestMethod.POST)
    public String saveNote(@ModelAttribute("aNote") CreateNote aNote, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors() || aNote == null){
            return "/error";
        } else {
            Note note = new Note();
            note.setReglement(false);
            Table aTable = tableRepository.findById(aNote.getTableId()).orElseThrow(() -> new IllegalArgumentException("Invalid table Id"));
            note.setTable(aTable);
            note.setCouvert(aNote.getCouvert());
            note.setServeur(utilisateurService.getNomUser());

            noteRepository.save(note) ;

            //on passe à l'étape suivante : le choix des plats
            long noNote = note.getId() ;
            model.addAttribute("note", noNote);
            List<CategoriePlat> categoriePlatList = categoriePlatRepository.findAll();
            model.addAttribute("categories", categoriePlatList) ;
            return "/Note/createNoteCategorie" ;
        }
    }

    /**
     * FORMULAIRE CHOIX DE LA CATEGORIE
     *
     *
     *
     */

    @RequestMapping(value = {"/createNotePlat/{id}&{note}"}, method = RequestMethod.GET)
    public String createNotePlat(@PathVariable("id") long id, Model model, @PathVariable("note") long noNote) {
        CategoriePlat aCategorie = categoriePlatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categorie Id:" + id));
        List<Plat>listPlat ;
        if(aCategorie != null) {
            listPlat = aCategorie.getPlats();
        } else {
            return "/error";
        }
        model.addAttribute("note", noNote);
        model.addAttribute("listPlat", listPlat);
        return "/Note/createNotePlat";
    }


    /**
     * FORMULAIRE CHOIX DU PLAT
     *
     *
     *
     */

    @RequestMapping(value = {"/saveNotePlat/{id}&{note}"}, method = RequestMethod.GET)
    public String saveNotePlat(@PathVariable("id") long id, Model model, @PathVariable("note") long noNote) {
        Plat aPlat = platRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid plat Id:" + id));
        Note aNote = noteRepository.findById(noNote).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + noNote));
        if(aPlat != null && aNote != null) {
            aPlat.addNotes(aNote);
            platRepository.save(aPlat);
            noteRepository.save(aNote);

            //on renvoie directement au choix d'un plat dans la même catégorie
            model.addAttribute("note", noNote);
            List<Plat> listPlat = aPlat.getCategorie().getPlats();
            model.addAttribute("listPlat", listPlat);
            return "/Note/createNotePlat";
        } else {
            return "/error" ;
        }

    }


    /**
     * RENVOI A LA LISTE DES CATEGORIES AVEC UN NUMERO DE NOTE
     *
     *
     *
     */

    @RequestMapping(value = {"/createNoteCategorie/{note}"}, method = RequestMethod.GET)
    public String createNoteCategorie(@PathVariable("note") long noNote, Model model) {
        model.addAttribute("note", noNote);
        List<CategoriePlat> categoriePlatList = categoriePlatRepository.findAll();
        model.addAttribute("categories", categoriePlatList) ;
        return "/Note/createNoteCategorie" ;

    }


    /**
     * LISTE DES TABLES EN COURS DE COMMANDE
     *
     * READ
     *
     */

    @RequestMapping(value = {"/readNote"}, method = RequestMethod.GET)
    public String readNote(Model model){
        List<Note> noteList = noteRepository.findAllByReglementFalse();  //liste de toutes les notes non réglées (donc en cours)
        List<ShowNote> notesEnCours = new ArrayList<>();
        for (Note note: noteList) {
            ShowNote aNote = new ShowNote();
            aNote.setIdNote(note.getId());
            aNote.setNoTable(note.getTable().getNoTable());
            aNote.setDescription(note.getTable().getDescription());
            aNote.setCouvert(note.getCouvert());
            notesEnCours.add(aNote);
        }
        model.addAttribute("notes", notesEnCours);

        return "/Note/readNote";
    }

    /**
     * LISTE DES PLATS LIES A UNE NOTE
     *
     * READ
     *
     */

    @RequestMapping(value = {"/listePlatNote/{id}"}, method = RequestMethod.GET)
    public String listePlatNote(@PathVariable("id") long idNote, Model model){
        Note aNote = noteRepository.findById(idNote).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + idNote));
        if(aNote != null){
            List<ShowPlatNote> platList = noteService.envoiListePlat(idNote);
            model.addAttribute("plats", platList);
            model.addAttribute("idNote", idNote);
            model.addAttribute("noTable", aNote.getTable().getNoTable());
            return "/Note/listePlatNote";
        } else {
            return "/error";
        }
    }


    /**
     * MODIFICATION D'UNE NOTE
     *
     * CHANGER LA TABLE, LE NB DE COUVERTS, OU SUPPRIMER LA NOTE
     *
     * UPDATE
     *
     */

    @RequestMapping(value = {"/updateNote/{id}"}, method = RequestMethod.GET)
    public String updateNote(@PathVariable("id") long idNote, Model model){
        Note note = noteRepository.findById(idNote).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + idNote));
        List<Table> tableList = tableRepository.findAll();
        UpdateNoteForm aNote = new UpdateNoteForm(note.getId(), note.getCouvert(), tableList);
        if(aNote != null){
            model.addAttribute("note", aNote);
            model.addAttribute("lastselected", note.getTable().getId());
            return "/Note/updateNote";
        } else {
            return "/error";
        }
    }

    @PostMapping("/updateNote")
    public String updateNote(@Valid UpdateNoteForm aNote, BindingResult result){
        if (result.hasErrors() || aNote == null) {
            return "/error";
        }
        Note note = noteService.changeNote(aNote) ;
        noteRepository.save(note);
        return "redirect:readNote";
    }

    /**
     * AJOUT DE +1 PLAT EXISTANT SUR UNE NOTE
     *
     *
     *
     */

    @RequestMapping(value = {"/ajoutPlat/{idNote}/{idPlat}"}, method = RequestMethod.GET)
    public RedirectView ajoutPlat(@PathVariable("idNote") long idNote, RedirectView redirectView, @PathVariable("idPlat") long idPlat) {
        Plat aPlat = platRepository.findById(idPlat).orElseThrow(() -> new IllegalArgumentException("Invalid plat Id:" + idPlat));
        Note aNote = noteRepository.findById(idNote).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + idNote));
        if(aPlat != null && aNote != null) {
            aPlat.addNotes(aNote);
            platRepository.save(aPlat);
            noteRepository.save(aNote);

            //on renvoie à la liste des plats de notre note
            redirectView.setContextRelative(true);
            redirectView.setUrl("/listePlatNote/"+idNote);
            return redirectView ;
        } else {
            redirectView.setContextRelative(false);
            redirectView.setUrl("/error");
            return redirectView;
        }

    }

    /**
     * RETRAIT DE -1 PLAT EXISTANT SUR UNE NOTE
     *
     *
     *
     */

    @RequestMapping(value = {"/retraitPlat/{idNote}/{idPlat}"}, method = RequestMethod.GET)
    public RedirectView retraitPlat(@PathVariable("idNote") long idNote, RedirectView redirectView, @PathVariable("idPlat") long idPlat) {
        Plat aPlat = platRepository.findById(idPlat).orElseThrow(() -> new IllegalArgumentException("Invalid plat Id:" + idPlat));
        Note aNote = noteRepository.findById(idNote).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + idNote));
        if(aPlat != null && aNote != null) {
            aPlat.deleteNote(aNote);
            platRepository.save(aPlat);
            noteRepository.save(aNote);

            //on renvoie à la liste des plats de notre note
            redirectView.setContextRelative(true);
            redirectView.setUrl("/listePlatNote/"+idNote);
            return redirectView ;
        } else {
            redirectView.setContextRelative(false);
            redirectView.setUrl("/error");
            return redirectView;
        }

    }


    /**
     * FACTURATION D'UNE NOTE
     *
     *
     *
     */

    @RequestMapping(value = {"/factureNote/{id}"}, method = RequestMethod.GET)
    public String factureNote(@PathVariable("id") long idNote, Model model){
        Note aNote = noteRepository.findById(idNote).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + idNote));
        if(aNote != null){
            FactureNote facture = noteService.facturer(aNote) ;
            model.addAttribute("facture", facture);
            return "/Note/factureNote";
        } else {
            return "/error";
        }
    }

    /**
     * VALIDATION DE LA FACTURATION
     *
     * ON SORT LE PDF DE LA FACTURE + ON PASSE LA VARIABLE REGLEMENT DE NOTE SUR TRUE POUR QUE LA NOTE SOIT RETIREE DE LA LISTE DES EN-COURS
     *
     */

    @RequestMapping(value = {"/factureValidNote/{id}"}, method = RequestMethod.GET)
    public String factureValidNote(@PathVariable("id") long idNote, Model model){
        Note aNote = noteRepository.findById(idNote).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + idNote));
        if(aNote != null){
            aNote.setReglement(true);
            noteRepository.save(aNote);

            return "/indexServeur";
        } else {
            return "/error";
        }
    }


}
