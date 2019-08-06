package com.zhongdao.gather;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import javax.crypto.Cipher;
import java.io.StringWriter;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * java生成openssl兼容的rsa 公私钥
 *
 * KeyPairGenerator生成的 私钥是pkcs8格式，需要先转为pkcs1格式，再由pkcs1转为pem格式(openssl生成的pem)
 * KeyPairGenerator生成的 公钥是x509格式,需要替换_为/,替换-为+，格式化后，加-----BEGIN PUBLIC KEY-----头，-----END PUBLIC KEY-----尾，就与openssl生成的公钥一致了
 *
 * 根据私钥生成公钥，公私钥是一对一 openssl rsa -pubout -in rsa_private_key.pem -out rsa_public_key.pem
 */
public class Test {

    public static void main(String[] args) throws Exception{
        KeyPairGenerator kpg =KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair keyPair = kpg.generateKeyPair();

        String  publicKey = Base64.encodeBase64URLSafeString(keyPair.getPublic().getEncoded());
        String  privateKey = Base64.encodeBase64URLSafeString(keyPair.getPrivate().getEncoded());

        System.out.println(privateKey);
        String pem = privatePem(privateKey);
        System.out.println("openssl 生成的私钥.pem:");
        System.out.println(pem);

        System.out.println(publicKey);
        System.out.println("openssl 生成的公钥.pem");
        System.out.println("-----BEGIN PUBLIC KEY-----");
        formatKey(publicKey.replace("_","/").replace("-","+"));
        System.out.println("-----END PUBLIC KEY-----");

      String p = new String(encryptByRSA(publicKey.getBytes(),new String("aaa").getBytes())) ;
      System.out.println(p);


    }


    public String gets(int pid){
        int p = 10;
         p--;
         if (p<0)
             return "";
       return gets(pid);
    }


    public static String publicPem(String publicKey) throws Exception{
        byte[] pubBytes = Base64.decodeBase64(publicKey);

        SubjectPublicKeyInfo spkInfo = SubjectPublicKeyInfo.getInstance(pubBytes);
        ASN1Primitive primitive = spkInfo.parsePublicKey();
        byte[] publicKeyPKCS1 = primitive.getEncoded();

        PemObject pemObject = new PemObject("RSA PUBLIC KEY", publicKeyPKCS1);
        StringWriter stringWriter = new StringWriter();
        PemWriter pemWriter = new PemWriter(stringWriter);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        String pemString = stringWriter.toString();

        return pemString;
    }

    public static String privatePem(String privateKey) throws Exception{
        byte[] privBytes = Base64.decodeBase64(privateKey);

        PrivateKeyInfo pkInfo = PrivateKeyInfo.getInstance(privBytes);
        ASN1Encodable encodable = pkInfo.parsePrivateKey();
        ASN1Primitive primitive = encodable.toASN1Primitive();
        byte[] privateKeyPKCS1 = primitive.getEncoded();

        return pkcs1ToPem(privateKeyPKCS1,false);
    }

    public static String pkcs1ToPem(byte[] pcks1KeyBytes,boolean isPublic) throws Exception{
        String type;
        if(isPublic){
            type = "RSA PUBLIC KEY";
        }else{
            type = "RSA PRIVATE KEY";
        }

        PemObject pemObject = new PemObject(type, pcks1KeyBytes);
        StringWriter stringWriter = new StringWriter();
        PemWriter pemWriter = new PemWriter(stringWriter);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        String pemString = stringWriter.toString();

        return pemString;
    }

    /**
     * 格式化java生成的key，一行长的，不适合pem中的-----BEGIN PUBLIC KEY-----，pem已经有换行了
     * @param key
     */
    public static void formatKey(String key){
        if(key==null) return;

        key = key.replace("\n","");

        int count = (key.length()-1)/64+1;
        for(int i=0;i<count;i++){
            if(i+1==count){
                //循环的最后一次
                System.out.println(key.substring(i*64));
            }else{
                System.out.println(key.substring(i*64,i*64+64));
            }
        }
    }

    /**
     * 从pem格式(-----BEGIN PUBLIC KEY-----)的key获取一行key
     * @param pem
     * @return
     */
    public static String pemToKey(String pem){
        if(pem==null) return "";
        if(pem.indexOf("KEY-----")>0){
            pem = pem.substring(pem.indexOf("KEY-----")+"KEY-----".length());
        }
        if(pem.indexOf("-----END")>0){
            pem = pem.substring(0,pem.indexOf("-----END"));
        }
        return pem.replace("\n","");
    }

    /**
     * 使用RSA公钥加密数据
     *
     * @param pubKeyInByte
     *            打包的byte[]形式公钥
     * @param data
     *            要加密的数据
     * @return 加密数据
     */
    public static byte[] encryptByRSA(byte[] pubKeyInByte, byte[] data) {
        try {
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pub_spec = new X509EncodedKeySpec(pubKeyInByte);
            PublicKey pubKey = mykeyFactory.generatePublic(pub_spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 用RSA私钥解密
     *
     * @param privKeyInByte
     *            私钥打包成byte[]形式
     * @param data
     *            要解密的数据
     * @return 解密数据
     */
    public static byte[] decryptByRSA(byte[] privKeyInByte, byte[] data) {
        try {
            PKCS8EncodedKeySpec priv_spec = new PKCS8EncodedKeySpec(
                    privKeyInByte);
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = mykeyFactory.generatePrivate(priv_spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * 使用RSA私钥加密数据
     *
     * @param privKeyInByte
     *            打包的byte[]形式私钥
     * @param data
     *            要加密的数据
     * @return 加密数据
     */
    public static byte[] encryptByRSA1(byte[] privKeyInByte, byte[] data) {
        try {
            PKCS8EncodedKeySpec priv_spec = new PKCS8EncodedKeySpec(
                    privKeyInByte);
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = mykeyFactory.generatePrivate(priv_spec);
            Cipher cipher = Cipher.getInstance(mykeyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 用RSA公钥解密
     *
     * @param pubKeyInByte
     *            公钥打包成byte[]形式
     * @param data
     *            要解密的数据
     * @return 解密数据
     */
    public static byte[] decryptByRSA1(byte[] pubKeyInByte, byte[] data) {
        try {
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pub_spec = new X509EncodedKeySpec(pubKeyInByte);
            PublicKey pubKey = mykeyFactory.generatePublic(pub_spec);
            Cipher cipher = Cipher.getInstance(mykeyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }


}
