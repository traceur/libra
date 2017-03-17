package com.weizhi.libra.web.encrypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PasswordDecrypter {
    private static final String        ALGORTTHM = "PBEWithMD5AndDES";

    private StandardPBEStringEncryptor encrypter;
    private String                     key;


    public PasswordDecrypter(String key) {
        this.key = key;
    }


    public String decrypt(String cipherText) throws Exception {
        try {
            if (this.encrypter == null) {
                this.encrypter = new StandardPBEStringEncryptor();
                encrypter.setPassword(this.key);
                encrypter.setAlgorithm(ALGORTTHM);
            }
            return this.encrypter.decrypt(cipherText);
        } catch (Exception e) {
            throw e;
        }
    }


    public String encrypt(String plainText) throws Exception {
        try {
            if (this.encrypter == null) {
                this.encrypter = new StandardPBEStringEncryptor();
                encrypter.setPassword(this.key);
                encrypter.setAlgorithm(ALGORTTHM);
            }
            return this.encrypter.encrypt(plainText);
        } catch (Exception e) {
            throw e;
        }
    }


    public static void main(String[] args) throws Exception {
        PasswordDecrypter passwordDecrypter = new PasswordDecrypter(
                "29aa8testd9989cb0");
        String code = "5f5743test262a29b";
        String cipherText = passwordDecrypter.encrypt(code);
        System.out.println(cipherText);
        PasswordDecrypter passwordDecrypter2 = new PasswordDecrypter("e7bf78testf618255");
        String plainText = passwordDecrypter2
                .decrypt("lJK0swoVHr3qVTHd1BqPYyXi+cY3HdU0EDnpOfvJzlI=");
        System.out.println(plainText);
    }

}
