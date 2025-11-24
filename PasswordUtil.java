package com.uams.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash a plaintext password
    public static String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt(12));
    }

    // Verify plaintext vs hashed
    public static boolean check(String plain, String hashed) {
        if (plain == null || hashed == null) return false;
        return BCrypt.checkpw(plain, hashed);
    }
}
