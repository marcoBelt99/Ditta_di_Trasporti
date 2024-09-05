package com.beltra.sistema1.config;

/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
*/
//@Configuration
// @EnableWebSecurity

/*
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Per criptare le password a DB
   // @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    // TODO qui sono nella fase autenticazione, dove inserisco
    //  tutte gli user id e le password degli utenti

    // Per specificare gli utenti che possono accedere alla web app.
    //@Bean
    @Override
    protected UserDetailsService userDetailsService() {

        // Per costruire gli utenti
        User.UserBuilder users = User.builder();

        // Per gestire gli utenti direttamente in memoria nella mia web app (non viene inserito
        // nulla dentro a file o DB relazionali - Per ora)
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Utente 1: utente standard, che può accedere a determinate parti della mia web app
        manager.createUser(
                users
                    .username("Meluccia")
                    .password(new BCryptPasswordEncoder().encode("123Stella"))
                    .roles("USERS")
                    .build() );

        // Utente amministratore: può accedere agli elementi della web app dove bisogna
        // essere dei power users Es) form per modificare turni, inserirne di nuovi, eliminarli

        manager.createUser(
                users
                    .username("Admin")
                    .password(new BCryptPasswordEncoder().encode("megapassword"))
                    .roles("USERS", "ADMIN")
                    .build() );

    return manager;
    }

    // In configure specifico: quale metodo sto usando per ottenere il dettaglio dei miei utenti;
    // e quale codificatore di password sto utilizzando
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder((passwordEncoder()));
    }

    // TODO: fase di autorizzazione
    // specifico le autorizzazioni che gli utenti possono avere --> a quali elementi
    // della applicazione possono accedere

    // array contenente gli URL amministrativi della parte di applicazione che gestisce gli autisti.
    // Qui metto quegli URL ai quali potrà accedere solo ed esclusivamente l'amministratore,
    // e saranno relativi solo alla parte relativa ai clienti
    private static final String[] ADMIN_AUTISTI_MATCHER =
            {
                "/autisti/aggiungi/**",
                "/autisti/modifica/**",
                "/autisti/aggiungi/**"
            };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // TODO: uso di antMatchers()
                .authorizeRequests()
                // Inizio a specificare quali parti della mia web application sono liberamente
                // accessibili a prescindere dall'autenticazione ed autorizzazione di sicurezza

                // Abilito tutti gli URL riguardanti le risorse (come immagini, javascript, etc.).
                // Quindi, per accedere a questo URL "/resources" non ci sarà bisogno di essere autenticati e autorizzati
                .antMatchers("/resources/**").permitAll()

                // Faccio in modo che l'utente possa accedere alla schermata di login
                .antMatchers("/login/**").permitAll()

                // La root dell'applicazione voglio sia accessibile sia all'utente "ANONYMOUS", sia all'utente "USER"
                // (quindi riesco a vedere la home page anche senza autenticarmi
                .antMatchers("/").hasAnyRole("ANONYMOUS", "USER")

                // antMatchers che si riferisce all'array creato in precedenza.
                // per poter accedere a tale array il ruolo deve essere di ADMIN
                // se sono un utente ("USER") o un anonymous ("ANONYMOUS") non ci posso
                // accedere
                .antMatchers(ADMIN_AUTISTI_MATCHER).access("hasRole('ADMIN')")


                // Gli altri elementi che interessano gli autisti voglio siano accessibili dagli utenti "USER"
                // "/autisti/**" significa: la pagina "/clienti" seguita da qualsiasi URL
                // però devo rispettare questo ordine su tutte le chiamate di antMatchers():

                .antMatchers("/autisti/**").hasRole("USER")

        // infatti ==> TODO: la logica di funzionamento dell'antMatchers() va dal più specifico al più generico
        // TODO: per fare in modo che siano solo gli utenti amministratori a poter accedere agli URL "/autisti/aggiungi/**"
        // TODO: "/autisti/modifica/**" e "/autisti/elimina/**" devo mettere tale regola prima dell'elemento più generico
        // TODO: che è "/autisti/**". Se avessi invertito questo ordine, allora l'utente "USER" sarebbe riuscito ad entrare
        // TODO: a degli elementi della nostra web app dove in teoria non sarebbe dovuto entrare ==> attenzione all'ordine degli
        // TODO: antMatchers(): si va dagli elementi + restrittivi a quelli - restrittivi:
        // TODO: prima specifico gli elementi che rigurdano il ruolo di amministratore, e poi quello degli utenti


                // il metodo and() aggiunge un ulteriore elemento di configurazione al di là degli antMatchers()
                .and()


                // TODO:Configurazione del form di login:
                //  (cioè il form che appare quando si cerca di accedere a parti della web)
                // app che richiedono di autenticarsi / autorizzarsi.
                .formLogin()

                // Specifico l'URL col quale accederò al form di login
                .loginPage("login/form")

                // Specifico l'URL di autenticazione dell'utente
                .loginProcessingUrl("/login")

                // L'URL di fallimento (quando tento di inserire uno username o password che non esistono.
                // Per questo caso devo prevedere un URL).
                // Notare che ho usato uno specifico parametro
                .failureUrl("/login/form?error")

                // Ottengo la userId e la password di riferimento
                .usernameParameter("userId")
                .passwordParameter("password")

                .and()

                // TODO: Gestione delle eccezioni: se tento di entrare in una parte della web app
                // dove non ho autorizzazioni cosa succede?
                .exceptionHandling()

                // in tal caso uso uno specifico URL, col parametro forbidden
                // potevo anche usare una pagina completamente differente
                .accessDeniedPage("/login/form?forbidden")

                .and()


                // TODO: Possibilità di inserire il log-out
                .logout()

                // Uso questo URL col parametro logout
                .logoutUrl("/login/form?logout");


                // disabilito il csrf (anche se non andrebbe fatto in produzione)
                //.and().csrf().disable();




    }
}

*/

