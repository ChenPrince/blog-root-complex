package com.zerodg.zdtylin.tylinUtilTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;


@Slf4j
public class GetCustomerInfoTest {
    @Test
    public void getCustomer() {
        try {
            String authToken = "dHlsaW5hZDpXZWxjb21lQHR5bGluMQ==";
            String dataModelUrl = "/Custom/TYLIN_InterfaceDM/TYLIN_Customers/TYLIN_Customers.xdm";
            Map<String,String> map = new HashMap();
            map.put("UPDATE_DATE","2019-06-01");
            String wsdlUrl = "https://ejwn-test.fa.us2.oraclecloud.com/xmlpserver/services/v2/ReportService?wsdl";
            //这里改成null测试一下
            //String requsetXML = XMLUtil.makeSOAPRequestXML(authToken,dataModelUrl,map);
            String requsetXML = XMLUtil.makeSOAPRequestXML(authToken,dataModelUrl,null);
            log.info("requestXML:" + requsetXML + "\n");

            String content = HttpUtil.httpPostResponseByXML(authToken, wsdlUrl, requsetXML);
            JSONArray jsonArray = XMLUtil.getDataDSG1(content);

            for(int i=0;i<jsonArray.size();i++) {
                log.info("客户" + i + ":" + jsonArray.get(i).toString());
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
