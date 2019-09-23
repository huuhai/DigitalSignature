/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chungthucdientu.digital_signature;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huuha
 */
public class Check {

    public boolean check(byte[] publicKey, byte[] fileNeedCheck, byte[] bsign, String algorithm) {

        try {
            Signature s;
            X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(spec);
            s = Signature.getInstance("SHA1WithRSA");
            s.initVerify(pubKey);
            s.update(fileNeedCheck);

            boolean result = s.verify(bsign);
            if (result == true) {
                System.out.println("Message is verified");
                return true;
            } else {
                System.out.println("Message isn't verified");
                return false;
            }

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException ex) {
            Logger.getLogger(DigitalSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
