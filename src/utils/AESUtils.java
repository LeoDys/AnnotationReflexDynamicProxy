package utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * TODO 这里请补充该类型的简述说明
 *
 * @author <a href="mailto:leij@emrubik.com">lei jia</a>
 * @version $Revision 1.0 $ 2016年1月19日 上午10:32:34
 */
public class AESUtils {

    //    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding"; // CBC加密算法
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding"; // ECB加密算法

    private static final String ALGORITHM = "AES"; // 算法

    private static final String INITIALIZATION_VECTOR = "wwww.inossem.com";// ECB加密算法时使用的初始化向量

    public static final String PRIVATE_KEY = "ENCODE_AES_KEY16";// 秘钥,必须16位

    private static final byte[] parseVectorToBytes() {
        return INITIALIZATION_VECTOR.getBytes();
    }

    /**
     * 加密
     *
     * @param data      待加密信息
     * @param publicKey 密钥
     * @return 加密后的字符串
     * @author leij
     */
    public static String encrypt(String data, String publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec skeySpec = new SecretKeySpec(publicKey.getBytes(), ALGORITHM);
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(parseVectorToBytes()));//设置IV
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypt = cipher.doFinal(data.getBytes());
            return parseByteToHexStr(encrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解密
     *
     * @param codeData  待解密信息
     * @param publicKey 密钥
     * @return 解密后的字符串
     * @author leij
     */
    public static String decrypt(String codeData, String publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec skeySpec = new SecretKeySpec(publicKey.getBytes(), ALGORITHM);
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(parseVectorToBytes()));//设置IV
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decrypt = cipher.doFinal(parseHexStrToByte(codeData));
            return new String(decrypt).replace(AESUtils.unicodeToCn("\\\\u0006"), "");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将byte[]转换成16进制字符串
     *
     * @param buf 待转换的byte[]数组
     * @return 16进制字符串
     * @author leij
     */
    private static String parseByteToHexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转换成byte[]
     *
     * @param hexStr 待转换的16进制字符串
     * @return byte[]类型
     * @author leij
     */
    private static byte[] parseHexStrToByte(String hexStr) {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 去除加密后的拼接字符串的特殊字符
     *
     * @param hexStr 待转换的16进制字符串
     * @return byte[]类型
     * @author leij
     */
    public static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }
}
