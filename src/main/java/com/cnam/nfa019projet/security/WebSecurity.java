
package com.cnam.nfa019projet.security;

import com.cnam.nfa019projet.service.UtilisateurDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UtilisateurDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                    .antMatchers("/indexAdmin","/readUtil","/updateUtil/**", "/createUtil", "/deleteUtil/**",
                            "/readHistoriqueStock",
                            "/readStatut", "/createStatut", "/updateStatut",
                            "/updateProduit/**","/deleteProduit", "/createProduit",
                            "/readCategorie", "/updateCategorie/*", "/createCategorie","/deleteCategorie",
                            "/createFrigo","/updateFrigo/**","/deleteFrigo",
                            "/readCategoriePlat", "createCategoriePlat", "updateCategoriePlat/**", "deleteCategoriePlat",
                            "readPlat", "createPlat", "updatePlat/**", "deletePlat",
                            "createTable", "updateTable/**", "deleteTable").hasRole("ADMIN")
                    .antMatchers("/indexUtil", "/createTemp").hasRole("UTILISATEUR")
                    .antMatchers("/indexServeur", "createNote", "createNoteCategorie", "createNotePlat", "updateNote", "factureNote").hasRole("SERVEUR")
                    .antMatchers( "/readStock", "/readProduit",
                            "/updateStock","/chooseProduct", "/createStock", "/readFrigo", "/historiqueFrigo").hasAnyRole("ADMIN", "UTILISATEUR")
                    .antMatchers("/readTable", "readNote","listePlatNote").hasAnyRole("SERVEUR", "ADMIN")
                    .antMatchers("/", "/index", "/about").hasAnyRole("ADMIN","UTILISATEUR","SERVEUR")
                    .antMatchers( "/login", "/styles/**", "/pics/**", "/error", "/bdd/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }








}
