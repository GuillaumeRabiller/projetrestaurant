package com.cnam.nfa019projet.service;

import com.cnam.nfa019projet.model.Utilisateur;
import com.cnam.nfa019projet.repository.UtilisateurRepository;
import com.cnam.nfa019projet.model.UtilisateurDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;


public class UtilisateurDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        Optional<Utilisateur> user = utilisateurRepository.findByLogin(name);

        user.orElseThrow(()-> new UsernameNotFoundException("Not found : " + name));

        return user.map(UtilisateurDetails::new).get();
    }


}
