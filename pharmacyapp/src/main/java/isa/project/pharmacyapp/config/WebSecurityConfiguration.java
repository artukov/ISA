package isa.project.pharmacyapp.config;

import isa.project.pharmacyapp.security.TokenUtils;
import isa.project.pharmacyapp.security.authetication.RestAuthenticationEntryPoint;
import isa.project.pharmacyapp.security.authetication.TokenAuthenticationFilter;
import isa.project.pharmacyapp.service.implementation.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    //REST auth
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    public TokenUtils tokenUtils;


    //General web security on application level
    @Override
    public void configure(WebSecurity web) throws Exception {
        //super.configure(web);

        web.ignoring().antMatchers(HttpMethod.POST, "/auth/login","/auth/refresh","/auth/signup");
        web.ignoring().antMatchers(HttpMethod.GET,"/","/*.html", "/favicon.ico","/**/*.html",
                "/**/*.css", "/**/*.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //communication between client and server is stateless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                //for unauthorized requests response will be 401
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

                //alowe to all user entry at /auth/**
                .authorizeRequests().antMatchers("/auth/**").permitAll()
                //every request must be authenticated
                .anyRequest().authenticated().and()

                .cors().and()

                //preflight every request with filter
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();


       // super.configure(http);
    }
}
