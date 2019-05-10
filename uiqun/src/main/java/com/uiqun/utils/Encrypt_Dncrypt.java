package com.uiqun.utils;

import com.uiqun.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

public abstract class Encrypt_Dncrypt {
        public static String getMD5(byte[] source) {
            String s = null;
            char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'A', 'B', 'C', 'D', 'E', 'F' };// 用来将字节转换成16进制表示的字符
            try {
                java.security.MessageDigest md = java.security.MessageDigest
                        .getInstance("MD5");
                md.update(source);
                byte tmp[] = md.digest();// MD5 的计算结果是一个 128 位的长整数，
                // 用字节表示就是 16 个字节
                char str[] = new char[16 * 2];// 每个字节用 16 进制表示的话，使用两个字符， 所以表示成 16
                // 进制需要 32 个字符
                int k = 0;// 表示转换结果中对应的字符位置
                for (int i = 0; i < 16; i++) {// 从第一个字节开始，对 MD5 的每一个字节// 转换成 16
                    // 进制字符的转换
                    byte byte0 = tmp[i];// 取第 i 个字节
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取字节中高 4 位的数字转换,// >>>
                    // 为逻辑右移，将符号位一起右移
                    str[k++] = hexDigits[byte0 & 0xf];// 取字节中低 4 位的数字转换
                }
                s = new String(str);// 换后的结果转换为字符串
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return s;
        }

    /**
     * 获取用户上传文件修改后的文件名
     * @param session 用户回话
     * @param upload  上传的文件
     * @param modelName 所需的上传操作的(例如:上传logo,modelName填写logo)
     * @return
     */
        public static String getUpLoadFileName(HttpSession session, MultipartFile upload,String modelName){
            StringBuilder stringBuilder = new StringBuilder(upload.getOriginalFilename());
            User u = (User)session.getAttribute("user");
            if(u != null) {
                stringBuilder.append(u.getUid());
                stringBuilder.append(modelName);
                String MD5_upload = Encrypt_Dncrypt.getMD5(stringBuilder.toString().getBytes());
                String ofn = upload.getOriginalFilename();
                return MD5_upload + ofn.substring(ofn.lastIndexOf("."), ofn.length());
            }
            return null;
        }
}
