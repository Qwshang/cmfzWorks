package com.baizhi.test;//package com.baizhi.test;
//import com.aliyuncs.CommonRequest;
//import com.aliyuncs.CommonResponse;
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.exceptions.ServerException;
//import com.aliyuncs.http.MethodType;
//import com.aliyuncs.profile.DefaultProfile;
//import org.junit.Test;
//
//public class TestText {
//    @Test
//    public void text(){
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", " LTAIZ2pehgpbDYu7", "p2xV4gS7vPaze9e7lhao3xNbPvbYh0");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", "18803310881,16603120881");
//        request.putQueryParameter("SignName", "尚科技");
//        request.putQueryParameter("TemplateCode", "SMS_171116778");
//        request.putQueryParameter("TemplateParam", "{\"name\":\"尚科技\"}");
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//    }
//}
