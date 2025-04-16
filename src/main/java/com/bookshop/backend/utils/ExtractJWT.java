package com.bookshop.backend.utils;

import org.json.JSONObject;
import java.util.Base64;

public class ExtractJWT {

    public static String payloadJWTExtraction(String token, String extraction) {

        // Step 1: Split the JWT token into its parts
        String[] parts = token.split("\\.");

        if (parts.length != 3) {
            System.out.println("Invalid JWT token");
            return null;
        }

        // Step 2: Base64 decode the payload (second part)
        String payload = parts[1];

        // Decode the payload (which is Base64 URL-encoded)
        String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));

        // Step 3: Parse the decoded payload into a JSON object
        JSONObject jsonPayload = new JSONObject(decodedPayload);

        System.out.println(jsonPayload);

        //step4
        String email = jsonPayload.optString("sub");
        String admin = jsonPayload.optString("userType");

        if (extraction.equals("\"userType\""))
            return admin;
        else if (extraction.equals("\"sub\""))
            return email;
        return email;
    }
}
