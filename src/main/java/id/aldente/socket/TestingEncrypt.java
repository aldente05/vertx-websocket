package id.aldente.socket;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

/**
 * Created by f.putra on 11/5/20.
 */
public class TestingEncrypt {

    public static void main(String[] args){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        ZeroSaltGenerator salt = new ZeroSaltGenerator();
        encryptor.setPassword("4C78aqduku6QdLYeSYfr4z6p7mWiJGqK");
        encryptor.setSaltGenerator(salt);
        encryptor.encrypt()
    }
}
