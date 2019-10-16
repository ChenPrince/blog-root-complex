package com.zerodg.zdtylin.tylinUtilTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
//import org.tylin.cons.Constants;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class ProjectTest {
    //测试环境
    private final static String authToken = "dHlsaW5hZDpXZWxjb21lQHR5bGluMQ==";

    private static JSONArray _JSONResult;

    public static void main(String[] args)throws Exception{
//        getIncrimeProjectData();
        _JSONResult=new JSONArray();

        ProjectTest projectTest =new ProjectTest();
        projectTest.getData(0);
        projectTest.saveDataToFile("test",_JSONResult.toJSONString());
//        long projectId=300000006847731l;
//        getSingleProject(projectId);

    }
//
//    /**
//     * 获取增量的项目数据
//     * 该接口仅做示例，不包含TYPE类型
//     * @throws Exception
//     */
//    public static void getIncrimeProjectData()throws Exception{
//        Map<String ,String> itemMap = new HashMap<>(1);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String currentDate = sdf.format(date);
//        itemMap.put("UPDATE_DATE", currentDate);
//
//        String dataModelUrl ="/Custom/TYLIN_InterfaceDM/TYLIN_Project/CDS/TYLIN_ProjectLastUpdate.xdm";
////        dataModelUrl = "/Custom/TYLIN_InterfaceDM/TYLIN_Project/TYLIN_UpdatedProjects.xdm";
//
//        String interfaceUrl= Constants.wsdlTestService;
//
//        String requestXML = XMLUtil.makeSOAPRequestXML(authToken, dataModelUrl, itemMap);
//
//        String content = HttpUtil.httpPostResponseByXML(authToken, interfaceUrl, requestXML);
//
//        JSONObject envBody = XMLUtil.getRetSoapenvBody(content);
//
////        log.info(envBody.toString());
//
//        if("S".equals(envBody.getString("RetCode"))){
//            JSONArray jsonArray = XMLUtil.getRetSoapenvBody(content).getJSONArray("RetMsg");
//            for(int i=0; i<jsonArray.size(); i++){
//                long projectId = jsonArray.getJSONObject(i).getLongValue("PROJECT_ID");
//                getSingleProject(projectId);
//            }
//        }
//
////        JSONArray g1Content = XMLUtil.getDataDSG1(content);
////        System.out.println(g1Content.size());
////        for (int i=0; i<g1Content.size(); i++){
////            System.out.println(g1Content.getJSONObject(i));
////            long projectId = g1Content.getJSONObject(i).getLongValue("PROJECT_ID");
////            getSingleProject(projectId);
////            getProjectTeamMember(projectId, 0);
////            getProjectCustomer(projectId, 0);
////            getProjectAllTasks(projectId, 0);
////        }
//
//    }
//
//    public static void getSingleProject(long projectId)throws Exception{
//        String projectUrl = Constants.singleTestProjectUrl;
//
//        projectUrl = projectUrl.replace("{ProjectId}", String.valueOf(projectId));
//
//        URIBuilder uriBuilder = new URIBuilder(projectUrl);
//
//        //添加带中文的参数
//        List<NameValuePair> list = new LinkedList<>();
//        list.add(new BasicNameValuePair("onlyData", "true"));
//        uriBuilder.setParameters(list).setCharset(Consts.UTF_8);
//
//        String content = HttpUtil.httpGetResponse(authToken, uriBuilder.build().toString());
//        System.out.println(content);
//
//        //这个content不能用XMLUtil里面的方法解析，直接就是JSONObject
//        JSONObject jsonObject = JSONObject.parseObject(content);
//        System.out.println(jsonObject);
//
//
//    }


//    public static void getProjectTeamMember(long projectId, int offset)throws Exception{
//        String projectTeamMemberUrl = "https://ejwn-test.fa.us2.oraclecloud.com/fscmRestApi/resources/11.13.18.05/projects/{ProjectId}/child/ProjectTeamMembers";
//
//        projectTeamMemberUrl = projectTeamMemberUrl.replace("{ProjectId}", String.valueOf(projectId));
//
//        URIBuilder uriBuilder = new URIBuilder(projectTeamMemberUrl);
//
//        //添加参数
//        List<NameValuePair> list = new LinkedList<>();
//        list.add(new BasicNameValuePair("onlyData", "true"));
//        list.add(new BasicNameValuePair("limit","50"));
//        list.add(new BasicNameValuePair("offset",String.valueOf(offset)));
//        uriBuilder.setParameters(list).setCharset(Consts.UTF_8);
//
//        String content = HttpUtil.httpGetResponse(authToken, uriBuilder.build().toString());
//
//        JSONObject jsonObject = JSONObject.parseObject(content);
//        System.out.println(jsonObject);
//
//        JSONObject projectObject = JSONObject.parseObject(content);
//        JSONArray itemArray = projectObject.getJSONArray("items");
//        for(int i=0; i<itemArray.size(); i++){
//            System.out.println(itemArray.getJSONObject(i));
//        }
//
//        if(projectObject.getBoolean("hasMore")){
//            getProjectTeamMember(projectId,offset+50);
//        }
//
//    }
//
//
//    public static void getProjectCustomer(long projectId, int offset)throws Exception{
//        String projectCustomerUrl = "https://ejwn-test.fa.us2.oraclecloud.com/fscmRestApi/resources/11.13.18.05/projects/{ProjectId}/child/ProjectCustomers";
//
//        projectCustomerUrl = projectCustomerUrl.replace("{ProjectId}", String.valueOf(projectId));
//
//        URIBuilder uriBuilder = new URIBuilder(projectCustomerUrl);
//
//        //添加参数
//        List<NameValuePair> list = new LinkedList<>();
//        list.add(new BasicNameValuePair("onlyData", "true"));
//        list.add(new BasicNameValuePair("limit","50"));
//        list.add(new BasicNameValuePair("offset",String.valueOf(offset)));
//        uriBuilder.setParameters(list).setCharset(Consts.UTF_8);
//
//        String content = HttpUtil.httpGetResponse(authToken, uriBuilder.build().toString());
//
//        JSONObject jsonObject = JSONObject.parseObject(content);
//        System.out.println(jsonObject);
//
//        JSONObject projectObject = JSONObject.parseObject(content);
//        JSONArray itemArray = projectObject.getJSONArray("items");
//        for(int i=0; i<itemArray.size(); i++){
//            System.out.println(itemArray.getJSONObject(i));
//        }
//
//        if(projectObject.getBoolean("hasMore")){
//            getProjectCustomer(projectId,offset+50);
//        }
//    }
//
//    public static void  getProjectAllTasks(long projectId, int offset )throws Exception{
//        String projectTaskUrl = "https://ejwn-test.fa.us2.oraclecloud.com/fscmRestApi/resources/11.13.18.05/projects/{ProjectId}/child/Tasks/{TaskId}";
//        projectTaskUrl = projectTaskUrl.replace("{ProjectId}", String.valueOf(projectId));
//
//        URIBuilder uriBuilder = new URIBuilder(projectTaskUrl);
//
//        //添加参数
//        List<NameValuePair> list = new LinkedList<>();
//        list.add(new BasicNameValuePair("onlyData", "true"));
//        list.add(new BasicNameValuePair("limit","50"));
//        list.add(new BasicNameValuePair("offset",String.valueOf(offset)));
//        uriBuilder.setParameters(list).setCharset(Consts.UTF_8);
//
//        String content = HttpUtil.httpGetResponse(authToken, uriBuilder.build().toString());
//
//        //这个content不能用XMLUtil里面的方法解析，直接就是JSONObject
//        JSONObject jsonObject = JSONObject.parseObject(content);
//        System.out.println(jsonObject);
//
//        JSONObject projectObject = JSONObject.parseObject(content);
//        JSONArray itemArray = projectObject.getJSONArray("items");
//        for(int i=0; i<itemArray.size(); i++){
//            System.out.println(itemArray.getJSONObject(i));
//        }
//        if(projectObject.getBoolean("hasMore")){
//            getProjectAllTasks(projectId,offset+50);
//        }
//    }

    @SuppressWarnings("Duplicates")
    public   void getData(int offset) throws Exception {
        String projectTaskUrl="https://ejwn-test.fa.us2.oraclecloud.com/fscmRestApi/resources/11.13.18.05/dataSecurities";
        URIBuilder uriBuilder = new URIBuilder(projectTaskUrl);

        //添加参数
        List<NameValuePair> list = new LinkedList<>();
        list.add(new BasicNameValuePair("onlyData", "true"));
        list.add(new BasicNameValuePair("limit","50"));
        list.add(new BasicNameValuePair("offset",String.valueOf(offset)));
        uriBuilder.setParameters(list).setCharset(Consts.UTF_8);

        String content = HttpUtil.httpGetResponse(authToken, uriBuilder.build().toString());

        //这个content不能用XMLUtil里面的方法解析，直接就是JSONObject
        JSONObject jsonObject = JSONObject.parseObject(content);
        System.out.println(jsonObject);

        JSONObject projectObject = JSONObject.parseObject(content);
        JSONArray itemArray = projectObject.getJSONArray("items");

        jsonAdd(itemArray);
        if(itemArray.size()!=0){
            System.out.println("-----------------offset:"+offset+"-----------------");
            getData(offset+50);
        }
    }

    private  void jsonAdd(JSONArray jsonArray){
        for(int i=0; i<jsonArray.size(); i++){
            System.out.println(jsonArray.getJSONObject(i));
            _JSONResult.add(jsonArray.getJSONObject(i));
        }

    }

    @SuppressWarnings("Duplicates")
    private  void saveDataToFile(String fileName, String data) {

        FileSystemView fsv = FileSystemView.getFileSystemView();
        File path = fsv.getHomeDirectory();
        String deskTopPath = path.getPath();
        String _OUT_PATH = deskTopPath + "\\" + fileName + ".json";

        BufferedWriter writer = null;
        File file = new File(_OUT_PATH);
        //如果文件不存在，则新建一个
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件写入成功！");
    }

}
