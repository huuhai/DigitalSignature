/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chungthucdientu.digital_signature;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huuha
 */
public class DigitalSignature {

    public byte[] create(byte[] privateKey, byte[] fileNeedSignature, String algorithm) {
        byte[] bsign = null;
        Signature signature;
        try {

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = factory.generatePrivate(spec);
//            Signature signature = Signature.getInstance("SHA256withRSA");
            signature = Signature.getInstance(algorithm);
            signature.initSign(priKey);

            System.out.println("Noi dung van ban can ky: " + new String(fileNeedSignature));
            signature.update(fileNeedSignature);
            bsign = signature.sign();
            System.out.println("Chu ky so: " + new String(bsign));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException ex) {
            Logger.getLogger(DigitalSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bsign;
    }

    public boolean check(byte[] publicKey, byte[] fileNeedCheck, byte[] bsign, String algorithm) {
        Signature s;

        try {

            X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(spec);
            s = Signature.getInstance(algorithm);
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
