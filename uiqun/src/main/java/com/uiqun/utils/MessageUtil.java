package com.uiqun.utils;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.YunpianException;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.yunpian.sdk.util.HttpUtil.post;


public class MessageUtil {

    static Properties pro = new  Properties();
    static {
        InputStream resourceAsStream = MessageUtil.class.getClassLoader().getResourceAsStream("yunpian.properties");
        try {
            pro.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendMessage(){
        YunpianClient clnt = new YunpianClient(pro.getProperty("yp.apikey")).init();
        Map<String, String> param = clnt.newParam(2);
        param.put(YunpianClient.MOBILE, "18616020***");
        param.put(YunpianClient.TEXT, "【云片网】您的验证码是1234");
        Result<SmsSingleSend> r = clnt.sms().single_send(param);
//获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

//账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

//释放clnt
        clnt.close();
    }


    /**单条短信发送,智能匹配短信模板

     * @param apikey 成功注册后登录云片官网,进入后台可查看

     * @param text 需要使用已审核通过的模板或者默认模板

     * @param mobile 接收的手机号,仅支持单号码发送

     * @return json格式字符串

     */

    public static String singleSend(String apikey, String text, String mobile) throws YunpianException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post("https://sms.yunpian.com/v2/sms/single_send.json", params);
    }
    public static String getApiKey(){
        return pro.getProperty("yp.apikey");
    }


    public static String getRandomNumber(Integer size){
        StringBuilder sbNumber = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sbNumber.append((int)(Math.random()*9));
        }
        return sbNumber.toString();
    }
}
