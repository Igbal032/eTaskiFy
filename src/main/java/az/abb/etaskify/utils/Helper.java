package az.abb.etaskify.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class Helper {

    public static String passEncode(String password){
        String encodePass = new BCryptPasswordEncoder().encode(password);
        log.info(encodePass);
        return encodePass;
    }
}
