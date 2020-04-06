package com.group.utilities;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.group.model.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final String SECRET = "Unicast.Authenticated.User.2020";

    public static String createToken(String stringToEncode){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            // return token.
            return JWT.create()
                    .withClaim("string", stringToEncode)
                    // expires in 5 minutes
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new IllegalStateException("Invalid Signing configuration / Couldn't convert claims", exception);
        }
    }

    public static String decodeToken(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("string").asString();
        }catch (JWTDecodeException exception){
            throw new IllegalArgumentException("Invalid Token", exception);
        }
    }

    public static User decodeUser(String encoded){
        if(encoded == null) return null;
        User user = null;
        DecodedJWT jwt;
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
            jwt = verifier.verify(encoded);

            user = new User();
            user.setUsername(jwt.getClaim("username").asString());
            user.setPassword(jwt.getClaim("password").asString());
        }catch (JWTVerificationException e){
            e.printStackTrace();
        }

        return user;
    }

    public static String encodeUser(User user) {
        String token;

        if (user == null)
            throw new IllegalArgumentException("User cannot be null");
        //Using Java-JWT to decode token
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create()
                    .withClaim("username", user.getUsername())
                    .withClaim("password", user.getPassword())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                    .sign(algorithm);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            throw new IllegalStateException("User encoding was not successful due to 'Invalid signature/ claims'", e);
        }
        return token;
    }

    public static Boolean tokenIsExpired(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt().before(new Date());
        }
        catch (JWTDecodeException exception){
            throw new IllegalArgumentException("Invalid Token", exception);
        }
    }

}
