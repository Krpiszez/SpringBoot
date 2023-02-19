package com.tpe.security;

import com.tpe.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    /*
        This class is our filter class which is a layer of security?
            In this class
                1) Create JWT
                2) Validate JWT
                3) Extract userName from JWT
     */
    // We need to have secret key
    private String jwtSecretKey = "sboot";

    //jwt token life cycle
    private long jwtExpiration = 86400000; // 24 hours ==> on milisec

    // *********  CREATE(GENERATE) JWT TOKEN  **********

    /*
        To create JWT we need 3 things
            1. userName
            2. expire date
            3. secret key with encode type
     */

    public String createToken(Authentication authentication){
        // to get user details of currently logged in user in the format of user details which is recognized by security
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts
                .builder()
                .setSubject(userDetails.getUsername()) // getting user name
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration)) // setting life cycle
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey) // secret key with encoded type
                .compact(); // to collect, to compress provided data

    }

    // ********** VALIDATE TOKEN **********

    public boolean validateToken(String token){
        // by providing secret key and token we are validating the token

        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ********** EXTRACT USERNAME FROM JWT TOKEN **********.

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
