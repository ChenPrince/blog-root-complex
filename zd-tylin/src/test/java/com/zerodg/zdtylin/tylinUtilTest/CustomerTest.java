package com.zerodg.zdtylin.tylinUtilTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class CustomerTest {
//    static final Logger log = LoggerFactory.getLogger(CustomerTest.class);
    public static void main(String[] args){

        String cusAddrDM = "/Custom/TYLIN_InterfaceDM/TYLIN_Customers/TYLIN_CustomerAddress.xdm";
        String interUrl = "https://ejwn-test.fa.us2.oraclecloud.com/xmlpserver/services/v2/ReportService?wsdl";
        String token = "dHlsaW5hZDpXZWxjb21lQHR5bGluMQ==";

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("ACCOUNT_NUMBER","16018");
            String requestXML = XMLUtil.makeSOAPRequestXML(token, cusAddrDM, map);
            String content = HttpUtil.httpPostResponseByXML(token, interUrl, requestXML);
            JSONArray addressArray = XMLUtil.getDataDSG1(content);
            log.info(addressArray.toString());
            for(int i=0; i<addressArray.size(); i++){
                JSONObject job = addressArray.getJSONObject(i);

                System.out.println(job);

            }
        }catch (Exception e){
            log.info(e.getMessage(),e);
        }

    }


    @Test
    public void getCustomer() {
        String authToken = "dHlsaW5hZDpXZWxjb21lQHR5bGluMQ==";
        // 模块路径
        String dataModelURL = "/Custom/TYLIN_InterfaceDM/TYLIN_Customers/TYLIN_Customers.xdm";
        Map<String,String> map = new HashMap();
        map.put("UPDATE_DATE","2019-07-10");

        String wsdlUrl = "https://ejwn-test.fa.us2.oraclecloud.com/xmlpserver/services/v2/ReportService?wsdl";

//        String requestXML = XMLUtil.makeSOAPRequestXML(authToken, dataModelURL, null);
        // 生成xml请求报文
        String requestXML = XMLUtil.makeSOAPRequestXML(authToken, dataModelURL, map);

        log.info(requestXML);
        try {
            // 获取数据
            String content = HttpUtil.httpPostResponseByXML(authToken, wsdlUrl, requestXML);
//            log.info(content);
//            String reportText = XMLUtil.getReportText(content);
//            log.info(reportText);
            JSONArray array = XMLUtil.getDataDSG1(content);

            for(int i=0; i<array.size(); i++){
                log.info(array.get(i).toString());
            }

        }catch (Exception e){
            log.error(e.getMessage(), e);
        }



    }
}
