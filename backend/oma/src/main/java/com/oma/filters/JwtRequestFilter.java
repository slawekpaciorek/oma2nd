package com.oma.filters;

import com.oma.security.JWTTokenUtil;
import com.oma.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String[] URLS_WITHOUT_AUTHENTICATION = new String[]{"/demo", "/login"};

    @Autowired
    private UserService userService;

    @Autowired
    JWTTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestToken = request.getHeader("Authentication");
        String username = "";
        String jwtToken;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String path =  request.getRequestURI();
        if(!Arrays.asList(URLS_WITHOUT_AUTHENTICATION).contains(path) ) {
            if (requestToken != null && requestToken.startsWith("Bearer ")) {
                jwtToken = requestToken.substring((7));
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                } catch (IllegalArgumentException illegalArgumentException) {
                    try {
                        throw new Exception("Unable to get JWT Token", illegalArgumentException);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (ExpiredJwtException expiredJwtException) {
                    try {
                        throw new Exception("Expired token", expiredJwtException);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (username != null && securityContext.getAuthentication() == null) {

                    UserDetails userDetails = userService.loadUserByUsername(username);

                    if (jwtTokenUtil.validateToken(requestToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        securityContext.setAuthentication(authToken);
                    }
                }

                filterChain.doFilter(request, response);

            } else {
                logger.warn("Token doesnt start with bearer");
            }
        }else {
            filterChain.doFilter(request, response);
        }

    }
}
