package com.tpe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    //    Getting token from request header.
        String jwtToken = parseJwt(request);
        try {
            if (jwtToken!=null && jwtUtils.validateToken(jwtToken)){
                //we are extracting username from token
                String userName = jwtUtils.getUserNameFromJwtToken(jwtToken);

                // we are getting user details which is recognized by security.
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                // authenticated user
                UsernamePasswordAuthenticationToken authenticatedUser =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,// user itself
                                null, // credentials
                                userDetails.getAuthorities());// user authority roles
                // after we validated user we need to place/populate user details in to a security context.
                SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            }
        } catch (UsernameNotFoundException e) {
            e.getStackTrace();
        }

        filterChain.doFilter(request, response); // this is to do filter. Without this filter will not be added.

    }

    private String parseJwt(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }

    // to specify which end points should not be filtered
    // this method is optional!
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return (antPathMatcher.match("/register", request.getServletPath()) ||
        antPathMatcher.match("/login", request.getServletPath()));
    }
}
