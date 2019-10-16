package com.zerodg.zdtylin.tylinUtilTest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.json.XML;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class XMLUtil {

    /**
     * 将xml转换为com.alibaba.fastjson.JSONObject
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static com.alibaba.fastjson.JSONObject toJSONObject(String xmlText) throws Exception{
        JSONObject reportXmlObj = XML.toJSONObject(xmlText);
        com.alibaba.fastjson.JSONObject jsonObject=com.alibaba.fastjson.JSONObject.parseObject(reportXmlObj.toString());
        return jsonObject;
    }


    /**
     * 获取Soap返回报文中的Body信息，包含RetCode，RetMsg
     * RetCode : S：成功 其他：失败
     * RetMsg : 成功则返回com.alibaba.fastjson.JSONArray
     * @param soapResponContent
     * @return
     * @throws Exception
     */
    public static com.alibaba.fastjson.JSONObject getRetSoapenvBody(String soapResponContent)throws Exception{

        com.alibaba.fastjson.JSONObject xmlJSONObj = XMLUtil.toJSONObject(soapResponContent);
        com.alibaba.fastjson.JSONObject soapenvBodyObj = xmlJSONObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body");

        com.alibaba.fastjson.JSONObject retBodyObj = new com.alibaba.fastjson.JSONObject();
        if(soapenvBodyObj.get("soapenv:Fault")!=null){
            retBodyObj.put("RetCode", "E1001");
            retBodyObj.put("RetMsg",soapenvBodyObj);
        }
        if(soapenvBodyObj.getJSONObject("runDataModelResponse")!=null){
            if(getDataDSG1(soapResponContent).size()==0){
                retBodyObj.put("RetCode", "E2001");
                retBodyObj.put("RetMsg", "reportText中未取到指定G_1内容");
            }else{
                retBodyObj.put("RetCode", "S");
                retBodyObj.put("RetMsg", getDataDSG1(soapResponContent));
            }

        }

        return retBodyObj;
    }


    /**
     * 该方法目前是Worker相关报文处理
     * 获取Soap返回报文中的Body信息，包含RetCode，RetMsg
     * RetCode : S：成功 其他：失败
     * RetMsg : 成功则返回com.alibaba.fastjson.JSONArray
     * @param soapResponContent
     * @return
     * @throws Exception
     */
    public static com.alibaba.fastjson.JSONObject getRetEnvBody(String soapResponContent)throws Exception{

        com.alibaba.fastjson.JSONObject xmlJSONObj = XMLUtil.toJSONObject(soapResponContent);
        com.alibaba.fastjson.JSONObject soapenvBodyObj = xmlJSONObj.getJSONObject("env:Envelope").getJSONObject("env:Body");

        com.alibaba.fastjson.JSONObject retBodyObj = new com.alibaba.fastjson.JSONObject();
        if(soapenvBodyObj.get("env:Fault")!=null){
            retBodyObj.put("RetCode", "E1001");
            retBodyObj.put("RetMsg",soapenvBodyObj.getJSONObject("env:Fault").getString("faultstring"));
        }else {
            retBodyObj.put("RetCode", "S");
            retBodyObj.put("RetMsg", soapenvBodyObj);
        }

        return retBodyObj;
    }

    /**
     * 该方法目前是Worker相关报文处理
     * 获取创建员工信息后的报文中的xml部分
     * @param
     * @return
     * @throws Exception
     */
    public static String getWorkerCreateRetXML(String content){
        return  content.substring(content.indexOf("<?xml"),content.lastIndexOf(">")+1);
    }


    /**
     * 获取DataModel返回数据中的G_1对应的值,直接转换为JSONArray
     * @param soapResponContent dataModel返回的soap xml中的reportBytes对应解析的报文
     * @return
     * @throws Exception
     */
    public static com.alibaba.fastjson.JSONArray getDataDSG1(String soapResponContent)throws Exception{
        String g1Content = null;

        com.alibaba.fastjson.JSONArray returnData = null;

        String reportXMLData = getReportText(soapResponContent);
        com.alibaba.fastjson.JSONObject reportXmlObj = XMLUtil.toJSONObject(reportXMLData);

        com.alibaba.fastjson.JSONObject reportObject = com.alibaba.fastjson.JSONObject.parseObject(reportXmlObj.toString());
        if ("".equals(reportObject.getString("DATA_DS")) || reportObject.getString("DATA_DS") == null) {
            returnData = com.alibaba.fastjson.JSONArray.parseArray("[]");
        } else {
            com.alibaba.fastjson.JSONObject dsJSONObject = reportObject.getJSONObject("DATA_DS");

            if(dsJSONObject.containsKey("G_1")){
                Object G_1 = com.alibaba.fastjson.JSON.parse(dsJSONObject.get("G_1").toString());
                //当G_1只有一行时，会将G_1数据转换为一个JSONObject，并封装为一个JSONArray
                if (G_1 instanceof com.alibaba.fastjson.JSONObject) {
                    g1Content = dsJSONObject.getJSONObject("G_1").toString();

                    returnData = com.alibaba.fastjson.JSONArray.parseArray("["+g1Content+"]");
                }
                //当G_1为多行时，会将G_1数据转换为一个JSONArray
                else if (G_1 instanceof com.alibaba.fastjson.JSONArray) {
                    returnData = dsJSONObject.getJSONArray("G_1");
                } else {
                    returnData = com.alibaba.fastjson.JSONArray.parseArray("[]");
                }
            }else{
                returnData = com.alibaba.fastjson.JSONArray.parseArray("[]");
            }
        }

        return returnData;

    }


    /**
     * 获取DataModel中返回数据reportBytes解析后的值
     * @param soapResponContent
     * @return
     * @throws Exception
     */
    public static String getReportText(String soapResponContent)throws Exception{
        com.alibaba.fastjson.JSONObject xmlJSONObj = XMLUtil.toJSONObject(soapResponContent);
        String reportText = null;

        com.alibaba.fastjson.JSONObject soapenvBodyObj = xmlJSONObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body");

        String reportBytes = soapenvBodyObj.getJSONObject("runDataModelResponse").getJSONObject("runDataModelReturn").getString("reportBytes");
        byte[] asBytes = java.util.Base64.getDecoder().decode(reportBytes);
        reportText = new String(asBytes, StandardCharsets.UTF_8);

        return reportText;
    }


    /**
     * 解析authToken
     * @param authToken
     * @return
     */
    private static Map getUserPwdMap(String authToken){
        byte[] decoded = Base64.decodeBase64(authToken.getBytes());
        String userText = null;
        try{
            userText =  new String(decoded,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }


        String[] userStr = userText.split(":");
        String userName = userStr[0];
        String passwd = userStr[1];
        HashMap userMap = new HashMap(2);
        userMap.put("userName",userName);
        userMap.put("passwd",passwd);

        return userMap;
    }


    /**
     * 构造SOAP中的完整item信息
     * @param map
     * @return
     */
    private static String makeRequestItems(Map<String,String> map){
        String itemsXML = null;
        StringBuffer stringBuffer = new StringBuffer(100);
        if(map!=null && !map.isEmpty()){
            for(String key:map.keySet()){
                stringBuffer.append(putElement(key, map.get(key).toString()));
            }
        }
        itemsXML = stringBuffer.toString();
        return itemsXML;
    }


    /**
     * 构造item信息
     * @param key
     * @param value
     * @return
     */
    private static String putElement(String key,String value){
        String itemStr = "<v2:item>\n" +
                "<v2:name>"+key+"</v2:name>\n" +
                "<v2:values>\n" +
                "<v2:item>"+value+"</v2:item>\n" +
                "</v2:values>\n" +
                "</v2:item>";
        return  itemStr;
    }



    /**
     * 构造SOAP报文
     * @param authToken
     * @param dataModelUrl
     * @param map
     * @return
     */
    public static String makeSOAPRequestXML(String authToken, String dataModelUrl, Map<String,String> map){
        Map userMap = getUserPwdMap(authToken);
        String userName = userMap.get("userName").toString();
        String passwd = userMap.get("passwd").toString();

        String requestXML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v2=\"http://xmlns.oracle.com/oxp/service/v2\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <v2:runDataModel>\n" +
                "         <v2:reportRequest>\n" +
                "            <v2:parameterNameValues>\n" +
                "               <v2:listOfParamNameValues>\n" + makeRequestItems(map) +
                "               </v2:listOfParamNameValues>\n" +
                "            </v2:parameterNameValues>\n" +
                "            <v2:reportAbsolutePath>"+dataModelUrl+"</v2:reportAbsolutePath>\n" +
                "            <v2:reportData>cid:"+System.currentTimeMillis()+"</v2:reportData>\n" +
                "            <v2:sizeOfDataChunkDownload>-1</v2:sizeOfDataChunkDownload>\n" +
                "         </v2:reportRequest>\n" +
                "         <v2:userID>"+userName+"</v2:userID>\n" +
                "         <v2:password>"+passwd+"</v2:password>\n" +
                "      </v2:runDataModel>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        return requestXML;
    }
}
