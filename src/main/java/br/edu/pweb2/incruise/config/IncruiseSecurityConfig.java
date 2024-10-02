package br.edu.pweb2.incruise.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/* import br.edu.pweb2.incruise.services.SecurityService;
 */
@Configuration
@EnableWebSecurity
public class IncruiseSecurityConfig {
    private final DataSource dataSource;

/*     @Autowired
    private SecurityService securityService;

*/    public IncruiseSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", 
                        "/internshipOffer/offers", 
                        "/internshipOffer/filter",
                                "/internshipOffer/info/**", 
                                "student/register", 
                                "company/register",
                                "/company/save", 
                                "student/save")
                        .permitAll()
                        .requestMatchers("/student/students").hasAnyRole("ADMIN", "COORDINATOR","STUDENT")
                        .requestMatchers("/company/companies").hasAnyRole("ADMIN", "COORDINATOR","STUDENT","COMPANY")
                        .requestMatchers("/candidature/candidatures").hasAnyRole("ADMIN", "COORDINATOR")
                        .requestMatchers("/internshipOffer/register").hasAnyRole("COMPANY")
                        .requestMatchers("/internshipOffer/cancel/**").hasAnyRole("COMPANY", "ADMIN")
                        .requestMatchers("/candidature/apply/**").hasRole("STUDENT")
/*                         .requestMatchers("/candidature/info/**").access((authentication, requestContext) -> {
    String username = authentication.get().getName();
    Long candidatureId = Long.parseLong(requestContext.getRequest().getParameter("candidatureId"));
    // Verifique as permissões
    if (securityService.isCandidatureOwner(username, candidatureId) ||
        securityService.isCompanyAllowedInCandidature(username, candidatureId) ||
        authentication.get().getAuthorities().stream().anyMatch(authCandidature -> 
        authCandidature.getAuthority().equals("ROLE_COORDINATOR") || authCandidature.getAuthority().equals("ROLE_ADMIN"))) {
        return new AuthorizationDecision(true);
    } else {
        return new AuthorizationDecision(false);
    }
}) */
                        .requestMatchers("/styles/**", "/system/**", "/imgs/**", "/scripts/**").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/system/access-denied"))
                .formLogin((form) -> form
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        manager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM tb_user WHERE username = ?");

        manager.setAuthoritiesByUsernameQuery(
                "SELECT u.username, r.name " +
                        "FROM tb_user u INNER JOIN tb_role r ON u.role_id = r.id " +
                        "WHERE u.username = ?");
        return manager;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService(dataSource));
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}