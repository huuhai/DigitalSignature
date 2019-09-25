/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chungthucdientu.digital_signature;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huuha
 */
public class ReadWriteFile {

    public ReadWriteFile() {
    }

    public boolean save(byte[] valueKey, File locationSaveFile) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(locationSaveFile);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.write(valueKey);
            dataOutputStream.flush();
            dataOutputStream.close();
            return true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
