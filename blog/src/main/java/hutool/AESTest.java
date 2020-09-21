package hutool;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author dingchenchen
 * @since 2020/9/16
 */
public class AESTest {

    public final static byte[] aes_key = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    public static void main(String[] args) {
        String content = "test中文";//361953c777d9c972e9a01ab402ccf342
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        // 构建
        AES aes1 = SecureUtil.aes(aes_key);
        // 加密为16进制表示
        String encryptHex = aes1.encryptHex(content);
        System.out.println(encryptHex);
        // 解密为字符串
        String decryptStr = aes1.decryptStr("361953c767d9c972e9a01ab402ccf342", CharsetUtil.CHARSET_UTF_8);
        System.out.println(decryptStr);
    }
}
