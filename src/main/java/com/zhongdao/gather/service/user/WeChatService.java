package com.zhongdao.gather.service.user;

import com.zhongdao.gather.config.WeiXinConfig;
import com.zhongdao.gather.service.user.entity.WeChatAccessToken;
import com.zhongdao.gather.service.user.entity.WeChatUser;
import com.zhongdao.gather.utils.UHttpAgent;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatService {

    @Autowired
    private WeiXinConfig  config;
    /**
     *
     *官方文档：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419317851&token=24d1cee8e6a15fa86d008d642cf9775a54cc753d&lang=zh_CN
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     * 请求参数说明
     * 参数	 是否必须	说明
     * appid	是	应用唯一标识，在微信开放平台提交应用审核通过后获得
     * secret	是	应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
     * code	是	填写第一步获取的code参数
     * grant_type	是	填authorization_code
     *
     * 微信access_token返回实例
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE",
     * "unionid":"o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     * 返回参数说明
     *access_token	接口调用凭证
     * expires_in	access_token接口调用凭证超时时间，单位（秒）
     * refresh_token	用户刷新access_token
     * openid	授权用户唯一标识
     * scope	用户授权的作用域，使用逗号（,）分隔
     * unionid	当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
     * @return
     */
    public WeChatAccessToken getAccess_token(String code){
        WeChatAccessToken token = null;
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String,String> map = new HashMap<>();
        map.put("appid",config.getAppId());
        map.put("secret",config.getAppSecret());
        map.put("code",code);
        map.put("grant_type","authorization_code");//固定值
        String reuslt = UHttpAgent.getInstance().get(url,map);
        JSONObject json = JSONObject.fromObject(reuslt);
        if(json.containsKey("access_token")) {
            token = parse(reuslt);
        }
        return token;
    }

    /**
     * 刷新token
     * @param refresh_token
     * @return
     */
    public WeChatAccessToken getFreshToken(String refresh_token){
        WeChatAccessToken token = new WeChatAccessToken();
     // https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        Map<String,String> map = new HashMap<>();
        map.put("appid",config.getAppId());
        map.put("refresh_token",refresh_token);
        map.put("grant_type","refresh_token");//固定值
        String reuslt = UHttpAgent.getInstance().get(url,map);
        JSONObject json = null;
        if(reuslt!=null && reuslt.contains("{")) {
            json = JSONObject.fromObject(reuslt);
            if (json.containsKey("access_token")) {
                token = parse(reuslt);
            }
        }
     return token;
    }


    public WeChatUser getWeChatUserInfo(String accessToken, String code) {
        String url = "https://api.weixin.qq.com/sns/userinfo";
        WeChatUser weChatUser = new WeChatUser();
        Map<String, String> map = new HashMap<>();
        map.put("accessToken", accessToken);
        map.put("code", code);
        String reuslt = UHttpAgent.getInstance().get(url, map);
        JSONObject json = null;
        if (reuslt != null && reuslt.contains("{")){
            json =JSONObject.fromObject(reuslt);
        }
        weChatUser = parseUser(json);
        return weChatUser;

    }
    private WeChatAccessToken parse(String result){
        JSONObject json = JSONObject.fromObject(result);
        WeChatAccessToken weChatUser = new WeChatAccessToken();
        if(json.containsKey("access_token")) {
            weChatUser.setAccess_token(json.getString("access_token"));
        }
        if(json.containsKey("expires_in")) {
            weChatUser.setExpires_in("" + json.getInt("expires_in"));
        }
        if(json.containsKey("refresh_token")) {
            weChatUser.setRefresh_token(json.getString("refresh_token"));
        }
        if(json.containsKey("openid")) {
            weChatUser.setOpenid(json.getString("openid"));
        }
        if(json.containsKey("scope")) {
            weChatUser.setScope(json.getString("scope"));
        }
        if(json.containsKey("unionid")) {
            weChatUser.setUnionid(json.getString("unionid"));
        }
        return weChatUser;
    }

   private WeChatUser  parseUser(JSONObject jsonObject){

       WeChatUser weChatUser = new WeChatUser();
       if (jsonObject.getString("openid") != null) {
           /**
            * 官方返回格式:
            * {
            * "openid":"OPENID",
            * "nickname":"NICKNAME",
            * "sex":1,
            * "province":"PROVINCE",
            * "city":"CITY",
            * "country":"COUNTRY",
            * "headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
            * "privilege":[PRIVILEGE1","PRIVILEGE2"],
            * "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
            * }
            */

           weChatUser.setOpenid(jsonObject.getString("openid"));
           weChatUser.setNickname(jsonObject.getString("nickname"));
           weChatUser.setSex(jsonObject.getString("sex"));
           weChatUser.setProvince(jsonObject.getString("province"));
           weChatUser.setCity(jsonObject.getString("city"));
           weChatUser.setCountry(jsonObject.getString("country"));
           weChatUser.setHeadimgurl(jsonObject.getString("headimgurl"));
           weChatUser.setPrivilege(jsonObject.getString("privilege"));
           weChatUser.setUnionid(jsonObject.getString("unionid"));
       } else {
           /**
            * 错误返回样例：
            * "errcode":40003,
            * "errmsg":"invalid openid"
            */
           weChatUser.setErrcode(jsonObject.getString("errcode"));
           weChatUser.setErrmsg(jsonObject.getString("errmsg"));
       }
       return weChatUser;
   }

}


