package it.prova.ebay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
         .userDetailsService(customUserDetailsService)
         .passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.authorizeRequests()
    	 .antMatchers("/","/home").permitAll()//da inserire la search di annunci
         .antMatchers("/assets/**").permitAll()
         .antMatchers("/login", "/registration/**" ,"/search", "/annuncio/list").permitAll()
         .antMatchers("/utente/**").hasRole("ADMIN")
         .antMatchers("/areapersonale","/**").hasAnyRole("ADMIN", "CLASSIC_USER")
         //.antMatchers("/anonymous*").anonymous()
         .anyRequest().authenticated()
         .and()
         	.formLogin()
         	.loginPage("/login")
         	.defaultSuccessUrl("/home")
         	.failureUrl("/login?error=true")
         	.permitAll()
         .and()
         	.logout()
         	.logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .permitAll()
         .and()
            .csrf()
            .disable();
    }
}
