package com.somnus.firebasedemoproject.auth.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.somnus.firebasedemoproject.auth.exceptions.SomnusDemoException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FirebaseParser {
    public static FirebaseTokenHolder parseToken(String idToken) {
        if (StringUtils.isBlank(idToken)) {
            throw new IllegalArgumentException("FirebaseTokenBlank");
        }
        try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            return new FirebaseTokenHolder(firebaseToken);
        } catch (Exception e) {
            throw new SomnusDemoException("Invalid Auth token");
        }
    }
}
