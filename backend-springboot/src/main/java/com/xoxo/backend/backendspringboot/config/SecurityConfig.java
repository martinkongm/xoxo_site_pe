package com.xoxo.backend.backendspringboot.config;

import com.xoxo.backend.backendspringboot.config.filter.JwtTokenValidator;
import com.xoxo.backend.backendspringboot.service.implementation.UserDetailServiceImpl;
import com.xoxo.backend.backendspringboot.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //Permite trabajar Spring Security con anotaciones
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                //.httpBasic(Customizer.withDefaults()) //Se usa cuando el login se realiza únicamente con USUARIO - CONTRASEÑA
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    //Configurar los endpoints públicos
                    http.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/img/coleccion/body-splash/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/img/coleccion/exfoliante/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/img/coleccion/manteca/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/api/v1/**").permitAll();
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/**").permitAll();
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();

                    //Configurar los endpoints privados
                    //http.requestMatchers(HttpMethod.POST, "/auth/post").hasAnyAuthority("CREATE", "READ");
                    //http.requestMatchers(HttpMethod.POST, "/auth/post").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/methods/post").hasAnyRole("ADMIN", "DEVELOPER");
                    http.requestMatchers(HttpMethod.PATCH, "/methods/patch").hasAuthority("REFACTOR");
                    http.requestMatchers(HttpMethod.GET, "/methods/get").hasAnyAuthority("READ");

                    //Configurar el resto de endpoints - NO ESPECIFICADOS EN LA PARTE DE ARRIBA
                    http.anyRequest().permitAll();//.denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
