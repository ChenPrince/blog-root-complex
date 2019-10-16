//package com.zerodg.zdtylin.tylinUtilTest;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Map;
//
///**
// * @program: eroot
// * @description:
// * @author: ztz-prince
// * @create:
// * @date:2019-08-09-16:05
// * target
// **/
//@Slf4j
//public class OracleAPITest {
//
//    public static void main(String[] args) {
//
//    }
//
//
//    //构建请求报文
//    public String buildingMessage(String _AddressDM,String _InterURL,String _TOKEN){
//        String _MESSAGE=null;
//        // 解析token
//        Map userMa
// p = getUserPwdMap(authToken);
//        String userName = userMap.get("userName").toString();
//        String passwd = userMap.get("passwd").toString();
//
//        String requestXML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v2=\"http://xmlns.oracle.com/oxp/service/v2\">\n" +
//                "   <soapenv:Header/>\n" +
//                "   <soapenv:Body>\n" +
//                "      <v2:runDataModel>\n" +
//                "         <v2:reportRequest>\n" +
//                "            <v2:parameterNameValues>\n" +
//                "               <v2:listOfParamNameValues>\n" + makeRequestItems(map) +
//                "               </v2:listOfParamNameValues>\n" +
//                "            </v2:parameterNameValues>\n" +
//                "            <v2:reportAbsolutePath>"+dataModelUrl+"</v2:reportAbsolutePath>\n" +
//                "            <v2:reportData>cid:"+System.currentTimeMillis()+"</v2:reportData>\n" +
//                "            <v2:sizeOfDataChunkDownload>-1</v2:sizeOfDataChunkDownload>\n" +
//                "         </v2:reportRequest>\n" +
//                "         <v2:userID>"+userName+"</v2:userID>\n" +
//                "         <v2:password>"+passwd+"</v2:password>\n" +
//                "      </v2:runDataModel>\n" +
//                "   </soapenv:Body>\n" +
//                "</soapenv:Envelope>";
//
//        return requestXML;
//
//        return _MESSAGE;
//    }
//
//
//}
