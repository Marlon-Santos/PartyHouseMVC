package br.com.gft.mvc.security;

import br.com.gft.mvc.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
class Security extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/login/**", "/login-error", "/codVerificator/**", "/singUp/**", "/css/**", "/images/**").permitAll()
                .antMatchers("/", "/sales", "/historic").hasAnyRole("ADMIN", "USER")
                .antMatchers("/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/eventos/**", "/api/users/**", "/api/casas/**", "/api/vendas/**", "/api/estilos/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/", true).failureUrl("/login").permitAll()
                .and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("JSESSIONID").logoutSuccessUrl("/login").permitAll()
                .and().oauth2Login().loginPage("/login").defaultSuccessUrl("/", true).failureUrl("/login").permitAll()
                .and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("JSESSIONID").logoutSuccessUrl("/login").permitAll()
                .and().httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin@gft.com").password("{noop}gft").roles("ADMIN");
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
