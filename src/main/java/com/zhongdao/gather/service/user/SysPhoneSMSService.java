package com.zhongdao.gather.service.user;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zhongdao.gather.config.SMSCode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class SysPhoneSMSService implements InitializingBean {

    @Autowired
    private SMSCode smsCode;

    private static final String SMS_CODE_CONTENT_PREFIX = "SMS::CODE:CONTENT::";

    private static final String[] NUMS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static final Random random = new Random();


    private IAcsClient acsClient;
   //缓存先不考虑
   // @Autowired
  //  private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取短信验证
     * @param telephone
     * @return
     */
  public String  getSmsCode(String telephone){
      // 检查间隔时间
      String gapKey = "SMS::CODE:INTERVAL::" + telephone;
      //从redis缓存中通过key获取
//      String result = redisTemplate.opsForValue().get(gapKey);
//      //获取得到说明缓存中已经有验证码信息了，稍后再请求
//      if (result != null) {
//          return  "请求次数太频繁，请稍后再试！";
//      }
      // 否则，获取短信验证码
      String code = generateRandomSmsCode();

      String templateParam = String.format("{\"code\": \"%s\"}", code);
      // 组装请求对象
      SendSmsRequest request = new SendSmsRequest();
      request.setMethod(MethodType.POST);
      // 代发手机号
      request.setPhoneNumbers(telephone);
      // 给模板传参
      request.setTemplateParam(templateParam);
      // 短信模板 SMS_CODE
      request.setTemplateCode(smsCode.getTemplateCode());
      // 短信签名
      request.setSignName("Orcas栈");

      boolean success = false;
      try {
          SendSmsResponse response = acsClient.getAcsResponse(request);
          //判断sms验证码发送是否成功
          if ("OK".equals(response.getCode())) {
              success = true;
          } else {
              return  "验证码发送失败！";
          }
      } catch (ClientException e) {
          e.printStackTrace();
      }
      if (success) {
          // 短信发送成功， 存入redis缓存中
//          redisTemplate.opsForValue().set(gapKey, code, 60, TimeUnit.SECONDS);
//          redisTemplate.opsForValue().set(SMS_CODE_CONTENT_PREFIX + telephone, code, 10, TimeUnit.MINUTES);
          return code;
      } else {
          return "服务忙，请稍后重试！";
      }

   }


   public String getCodeFromRedis(String phone){
       String gapKey = "SMS::CODE:INTERVAL::" + phone;
      // String result = redisTemplate.opsForValue().get(gapKey);
      return "";
   }
    private static String generateRandomSmsCode() {
        StringBuilder sb = new StringBuilder();
        //NUMS[10]
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(10);
            sb.append(NUMS[index]);
        }
        return sb.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsCode.getAccessKey(), smsCode.getScretKey());
        String product = "Dysmsapi";   // 短信API产品名称，固定
        String domain = "dysmsapi.aliyuncs.com";   // 短信API产品域名，固定
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        this.acsClient = new DefaultAcsClient(profile);
    }
}
