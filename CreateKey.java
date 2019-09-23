/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chungthucdientu.digital_signature;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huuha
 */
public class CreateKey {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public void createKeyPair(int lengthKey) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(lengthKey, secureRandom);

            KeyPair keyPair = keyPairGenerator.genKeyPair();

            setPrivateKey(keyPair.getPrivate());
            setPublicKey(keyPair.getPublic());

            // in ra console cặp khóa ở dạng nhị phân (byte[] to String)
            System.out.println("privateKey: " + new String(privateKey.getEncoded()));
            System.out.println("publicKey: " + new String(publicKey.getEncoded()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CreateKey.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CreateKey() {
    }

    public CreateKey(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

}
