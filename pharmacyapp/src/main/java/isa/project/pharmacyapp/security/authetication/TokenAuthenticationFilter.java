package isa.project.pharmacyapp.security.authetication;

import isa.project.pharmacyapp.security.TokenUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/********************************
 * This class will represent the filter that will preflight all request
 * Except those listed in  <code>WebSecurityConfiguration.config(WebSecurity web)</code>
 ********************************/
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenUtils tokenUtils;

    private UserDetailsService userDetailsService;


    public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
        this.tokenUtils = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String email;
        String authToken = tokenUtils.getToken(request);

        if(authToken != null){
            //get email from the token
            email =tokenUtils.getEmailFromToken(authToken);

            if(email != null){
                //find user with the email
                UserDetails userDetails =userDetailsService.loadUserByUsername(email);

                //check if the token is valid
                if(tokenUtils.validToken(authToken, userDetails)){
                    //create credentials
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }

        }

        //pass request to the next filter in filterchain
        filterChain.doFilter(request,response);
    }
}
