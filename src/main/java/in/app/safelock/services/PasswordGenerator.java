package in.app.safelock.services;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {

    
        private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        private static final String NUMBER = "0123456789";
        private static final String SCHAR = "`~!@#$%^&*()_+-=|{[]}:'?'><";
        private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER + SCHAR;
        private static final SecureRandom random = new SecureRandom();


        public String generateRandomPassword(int length) {
    
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                
                int randomCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
                char randomChar = DATA_FOR_RANDOM_STRING.charAt(randomCharAt);
    
                sb.append(randomChar);
            }
        return sb.toString();
        }

}
