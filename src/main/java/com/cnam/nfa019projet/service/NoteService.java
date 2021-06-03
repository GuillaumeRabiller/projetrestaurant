package com.cnam.nfa019projet.service;

import com.cnam.nfa019projet.form.UpdateNoteForm;
import com.cnam.nfa019projet.model.Note;
import com.cnam.nfa019projet.model.Table;
import com.cnam.nfa019projet.repository.NoteRepository;
import com.cnam.nfa019projet.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class NoteService {

    @Autowired
    NoteRepository noteRepository ;

    @Autowired
    TableRepository tableRepository;


    public Note changeNote(UpdateNoteForm aNote){
        Note note = noteRepository.findById(aNote.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid note id"));
        note.setCouvert(aNote.getNbCouvert());
        Optional<Table> table = tableRepository.findById(aNote.getTableId());
        table.ifPresent(newTable -> {
            note.changeTable(newTable);
        });
        return note ;
    }
}
