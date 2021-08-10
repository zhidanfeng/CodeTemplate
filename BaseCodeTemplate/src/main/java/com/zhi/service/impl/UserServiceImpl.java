package com.zhi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.core.entity.User;
import com.zhi.dao.UserDao;
import com.zhi.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> listAll() {
        return super.list();
    }

    @Override
    public boolean addUser(User user) {
        if (this.checkIsExist(user.getUserName())) {
            throw new RuntimeException("用户名已存在");
        }
        ThreadUtil.sleep(500);
        user.setCreateId(1L);
        user.setCreateTime(DateUtil.current());
        user.setUpdateId(1L);
        user.setUpdateTime(user.getCreateTime());
        return super.save(user);
    }

    private boolean checkIsExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        List<User> list = super.list(queryWrapper);
        return CollectionUtil.isNotEmpty(list);
    }

    public static void main(String[] args) {
        //getDataDigest();

//        String httpMethod = "GET";
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        String stringToSign = consumerId + "\n" + baseUrl + "\n" + httpMethod + "\n" + timestamp + "\n";
//        String signedString = signData(stringToSign, privateEncodedStr);
//        System.out.println("WM_SEC.AUTH_SIGNATURE: " + signedString);
//        System.out.println("WM_SEC.TIMESTAMP: " + timestamp);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(getRandomLetterAndDigital(1, 0));
        }
        System.out.println(list.size());
    }

    private static void getDataDigest() {
        String token = "0be2f1dfae1e18ee585fb19a42847760";
        String xml = "<GetProductList>\n" +
                "    <User>maggie.zheng@vantop.com</User>\n" +
                "    <RequestTime>2021-05-11 15:45:44</RequestTime>\n" +
                "    <PageNumber>1</PageNumber>\n" +
                "    <ItemsPerPage>200</ItemsPerPage>\n" +
                "    <GetProductListRequest/>\n" +
                "</GetProductList>";
        String leftToken = token.substring(0, 16);
        String rightToken = token.substring(16);
        String sign = DigestUtil.md5Hex(leftToken + xml + rightToken);
        System.out.println(sign);
    }

    private static void getDataDigest2() {
        String token = "1341a610bff0840f16f0de15b43f4b1d";
        String xml = "<GetProductList>\n" +
                "    <User>295544925@qq.com</User>\n" +
                "    <RequestTime>2021-05-12 14:22:31</RequestTime>\n" +
                "    <PageNumber>1</PageNumber>\n" +
                "    <ItemsPerPage>10</ItemsPerPage>\n" +
                "</GetProductList>";
        String leftToken = token.substring(0, 16);
        String rightToken = token.substring(16);
        String sign = DigestUtil.md5Hex(leftToken + xml + rightToken);
        System.out.println(sign);
    }

    private static String consumerId = "a94e5467-871b-4287-97d3-505f7afa7fb8"; // Trimmed for security reason
    private static String baseUrl = "https://api-gateway.walmart.com/v3/getReport?type=vendor_item&version=2";
    private static String privateEncodedStr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI2NAN+AzUVsIXvssZGzaBG1BUKyb/AyhLHF0mmBfWHnc8zP0HkVIUf2QnRxy1+vKa/fWto28KjMoMn8muq4KpTJTV27RU6IOIcltyCvjbwok9vMqoAkHE+/lUxLBvluxlBuKLGMQ4wLBzNCerZ+R6nQT+SzGfGim/S+MS6KPl5XAgMBAAECgYBcHsbRrgZpRrzn5JuPYf25gGCDDpOQ85+t/js4lKAfxjxzr09SbbDyH08SbzhW9i0gGzEKULUAdeOycGX1mZODtUzLqKYn8VxrSmAKw1K2v3HXlwvFr4czYalBWz5BGje2FHG/6sid5REzIqRWCUmdFq18J9SlzJgYie+BF9eCYQJBANhUu//0jtXe/o0ZS4LBe26MYz9deEwe+MyF0cbKst6g4k3myvl+zKWMV71BI8eVA6THWUJbvm15dSRZMa4d8jECQQCngdkwfE2iOcQY5MWddl3rokR9UJqDAtaUxiZI3S4+zKlrvHUfWVWJlSVcR/G+KSLn9o9hx9kI9YwRPUG6Ie8HAkEAmjimIg21ulCUBgEPbPMETMY2htjaxuGZmyyXZXHh3Iazbrfm4cD8odSQRDJpEIJyK4mWX5FKC393/Ga93RQqMQJASvkGUfpA8SwzxgSXIow5K188km5CSgM6MeqidhhxO3B1ICCAt6BPdb4Is84LuFZHJlWoBVbioGzu3URyp7HKfwJBALQx8pyDSfoNKuxvHJhr8WO9NXbGyI7CukwW7MkeICrtTUiIWMjtQSuGZk4ZFuJRrPcQw5XeUzTdDxxtxOwdnY0=";

    public static String signData(String stringToBeSigned, String encodedPrivateKey) {
        String signatureString = null;
        try {
            byte[] encodedKeyBytes = Base64.decodeBase64(encodedPrivateKey);
            PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(encodedKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey myPrivateKey = kf.generatePrivate(privSpec);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(myPrivateKey);
            byte[] data = stringToBeSigned.getBytes(StandardCharsets.UTF_8);
            signature.update(data);
            byte[] signedBytes = signature.sign();
            signatureString = Base64.encodeBase64String(signedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(IdWorker.getId());
        return signatureString;
    }

    private static String getRandomLetterAndDigital(int length, boolean capital, boolean random) {
        StringBuilder valSb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = r.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 字符串
                // 取得大写字母还是小写字母
                int choice = 0;
                if (random) {
                    choice = r.nextInt(2) % 2 == 0 ? 65 : 97;
                } else {
                    choice = capital ? 65 : 97;
                }
                valSb.append((char) (choice + r.nextInt(26)));
            } else {
                // 数字
                valSb.append(r.nextInt(10));
            }
        }
        return valSb.toString();
    }

    private static String getRandomLetterAndDigital(int letterLength, int digitalLength) {
        StringBuilder valSb = new StringBuilder();
        Random r = new Random();
        int length = letterLength + digitalLength;
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = r.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                if (letterLength == 0) {
                    valSb.append(getRandomDigital(1));
                } else {
                    // 取得大写字母还是小写字母
                    valSb.append(getRandomLetter(1));
                    letterLength--;
                }
            } else {
                if (digitalLength == 0) {
                    valSb.append(getRandomLetter(1));
                } else {
                    // 数字
                    valSb.append(getRandomDigital(1));
                    digitalLength--;
                }
            }
        }
        return valSb.toString();
    }

    private static String getRandomLetter(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Character c;
            while (containsChar(c = (char) (65 + random.nextInt(26)))) {

            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String getRandomDigital(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static final Character[] excludeChar = new Character[]{'I', 'J', 'O', 'Q', 'Z'};

    private static boolean containsChar(char c) {
        System.out.println("c = " + c);
        for (Character character : excludeChar) {
            if (character.equals(c)) {
                return true;
            }
        }
        return false;
    }
}
