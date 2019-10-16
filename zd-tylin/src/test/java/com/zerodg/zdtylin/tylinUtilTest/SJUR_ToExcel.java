package com.zerodg.zdtylin.tylinUtilTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: eroot
 * @description: loading Securities Data ,joining User Roles , export to excel
 * @author: ztz-prince
 * @create:
 * @date:2019-08-28-10:58
 **/
@SuppressWarnings({"unused", "Duplicates"})
public class SJUR_ToExcel {


    private String _INPUT_PATH1=null;
    private String _INPUT_PATH2=null;
    private String _OUT_PATH=null;
    private String _FILE_NAME = null;
    private JSONObject _UR_JSONOJB;
    private JSONObject _DS_JSONOBJ;
    private XSSFWorkbook _XssfWorkbook;
    private XSSFSheet _Sheet;
    private int _Column;
    private List<List<Object>> _ResultList;
    private static JSONArray _TempJSONResult;
    //测试环境
    private final static String authToken = "dHlsaW5hZDpXZWxjb21lQHR5bGluMQ==";

    public SJUR_ToExcel(){

    }

    /**
     *
     * @param inputPath1 user role path
     */
    public SJUR_ToExcel(String inputPath1){
        this._INPUT_PATH1 = inputPath1;
    }

    /**
     * @param inputPath1  user role path
     * @param inputPath2  data securities path
     */
    public SJUR_ToExcel(String inputPath1, String inputPath2) {
        this._INPUT_PATH1 = inputPath1;
        this._INPUT_PATH2 = inputPath2;
    }

    /**
     * @param inputPath1 user role path
     * @param inputPath2 data securities path
     * @param fileName output file name
     */
    public SJUR_ToExcel(String inputPath1, String inputPath2, String fileName) {
        this._INPUT_PATH1 = inputPath1;
        this._INPUT_PATH2 = inputPath2;
        this._FILE_NAME = fileName;
    }


    /**
     * 初始化
     * @param fileName
     * @throws IOException
     */
    private void init(String fileName) throws Exception {

        if (fileName != null) {
            FileSystemView fsv = FileSystemView.getFileSystemView();
            File path = fsv.getHomeDirectory();
            String deskTopPath = path.getPath();
            _OUT_PATH = deskTopPath + "\\" + fileName + ".xlsx";
        } else {
            FileSystemView fsv = FileSystemView.getFileSystemView();
            File path = fsv.getHomeDirectory();
            String deskTopPath = path.getPath();
            _OUT_PATH = deskTopPath + "\\" + "export_data" + ".xlsx";
        }

        if(_INPUT_PATH1==null){
            getUserRoles();
        }else{
            _UR_JSONOJB = getJsonData(_INPUT_PATH1);
            saveDataToFile("user_roles",_UR_JSONOJB.toString());
        }

        if(_INPUT_PATH2==null){
            _TempJSONResult=new JSONArray();
            getDataSecurities(0);
            _DS_JSONOBJ=new JSONObject();
            _DS_JSONOBJ.put("items",_TempJSONResult);
            saveDataToFile("data_securities",_DS_JSONOBJ.toString());
        }else{
            _DS_JSONOBJ = getJsonData(_INPUT_PATH2);
        }


        InputStream is = new FileInputStream(_OUT_PATH);
        _XssfWorkbook = new XSSFWorkbook(is);
        _Sheet = _XssfWorkbook.getSheetAt(0);
        _Column=2;

        _ResultList=new LinkedList<>();

    }

    /**
     * 执行 entry
     * @throws IOException
     */
    public void execute() throws Exception {
        init(_FILE_NAME);
        jsonJoin();
        createExcel();
    }

    /**
     * 连接两个json 数据集，返回结果
     *
     * @return
     */
    @SuppressWarnings("Duplicates")
    private JSONObject jsonJoin() {

        List<String> cellList ;
        int schedule;
        DecimalFormat df=new DecimalFormat("0.00");
        if (_DS_JSONOBJ == null || _UR_JSONOJB == null) {
            throw new NullPointerException();
        }
        try{
            JSONObject jsonData = _UR_JSONOJB;
            JSONArray resourcesModular=(JSONArray)jsonData.get("Resources");
            // 遍历resource
            if(resourcesModular.size()>0) {
                //第二级目录
                System.out.println("==>遍历resource");
                for (int j = 0; j < resourcesModular.size(); j++) {
                    System.out.println("schedule:"+df.format(new Double(j)/resourcesModular.size()*100)+"%");
                    cellList = new LinkedList<>();
                    JSONObject resourcesModularOBJ = resourcesModular.getJSONObject(j);
                    String resourceId = resourcesModularOBJ.get("id")==null?"/":resourcesModularOBJ.get("id").toString();
                    String resourceUserName = resourcesModularOBJ.get("userName")==null?"/":resourcesModularOBJ.get("userName").toString();
                    String resourceDisplayName = resourcesModularOBJ.get("displayName")==null?"/":resourcesModularOBJ.get("displayName").toString();
                    cellList.add(resourceId);
                    cellList.add(resourceUserName);
                    cellList.add(resourceDisplayName);
                    //获取下一层
                    JSONArray rolesModular = (JSONArray) resourcesModularOBJ.get("roles");
                    //若不存在role就读取下一个
                    if(rolesModular==null||rolesModular.size()==0){
                        //创建这一层
                        XSSFRow row = _Sheet.createRow(_Column);
                        int cellIndex = 0;
                        for(int k=0;k<cellList.size();k++){
                            XSSFCell cell_ele = row.createCell(cellIndex);
                            cell_ele.setCellValue(cellList.get(k));
                            cellIndex++;
                        }
                        _Column++;
                        continue;
                    }

                    for(int k=0;k<rolesModular.size();k++){
                        List<List<String>> joinList ;
                        List<String> roleList = new LinkedList<>();
                        JSONObject rolesModularOBJ = rolesModular.getJSONObject(k);
                        String rolesId = rolesModularOBJ.get("id")==null?"/":rolesModularOBJ.get("id").toString();
                        String rolesValue = rolesModularOBJ.get("value")==null?"/":rolesModularOBJ.get("value").toString();
                        String rolesDisplayName = rolesModularOBJ.get("displayName")==null?"/":rolesModularOBJ.get("displayName").toString();
                        roleList.add(rolesId);
                        roleList.add(rolesValue);
                        roleList.add(rolesDisplayName);
                        joinList = getSecurities(resourceUserName,rolesValue);
                        createRow(cellList,roleList,joinList);
                    }
                }
            }else{

            }

        } catch(Exception e){
            e.printStackTrace();
        }


        return null;
    }

    @SuppressWarnings({"Duplicates", "SpellCheckingInspection"})
    private List<List<String>> getSecurities(String userName_i, String value_i){

        List<String> eleList ;
        List<List<String>> secList = new LinkedList<>();
        List<Integer> indexs = new LinkedList<>();
        try{
            JSONObject jsonObject = _DS_JSONOBJ;
            //找到一个就删一个
            JSONArray jsonData = (JSONArray)jsonObject.get("items");
            for (int j = 0; j < jsonData.size(); j++) {
                JSONObject subObj = jsonData.getJSONObject(j);
                String ActiveFlag = subObj.getString("ActiveFlag");
                String CreatedBy = subObj.getString("CreatedBy");
                String CreationDate = subObj.getString("CreationDate");
                String RoleCommonName = subObj.getString("RoleCommonName");
                String UserRoleDataAssignmentId = subObj.getString("UserRoleDataAssignmentId");
                String Rolerf = subObj.getString("Rolerf");
                String Userrf = subObj.getString("Userrf");
                String SecurityContext = subObj.getString("SecurityContext");
                String SecurityContextValue =subObj.getString("SecurityContextValue");
                String UserName = subObj.getString("UserName");
                String RoleNameCr = subObj.getString("RoleNameCr");
                //相同
                if(UserName.equals(userName_i)&&RoleCommonName.equals(value_i)){
                    eleList = new LinkedList<>();
                    eleList.add(ActiveFlag);
                    eleList.add(CreatedBy);
                    eleList.add(CreationDate);
                    eleList.add(RoleCommonName);
                    eleList.add(UserRoleDataAssignmentId);
                    eleList.add(Rolerf);
                    eleList.add(Userrf);
                    eleList.add(SecurityContext);
                    eleList.add(SecurityContextValue);
                    eleList.add(UserName);
                    eleList.add(RoleNameCr);
                    secList.add(eleList);
                    indexs.add(j);
                }
            }

            //删掉
            for(int i=0;i<indexs.size();i++){
                jsonData.discard(indexs.get(i));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return secList;
    }

    /**
     * 创建一个cell
     * @param cellList
     * @param joinList
     */
    @SuppressWarnings("Duplicates")
    private void createRow(List<String> cellList ,List<String> roleList, List<List<String>> joinList){
        //创建这一层
        XSSFRow row ;
        int cellIndex = 0;
        if(joinList.size()==0){
            row = _Sheet.createRow(_Column);
            for(int k=0;k<cellList.size();k++){
                XSSFCell cell_ele = row.createCell(cellIndex);
                cell_ele.setCellValue(cellList.get(k));
                cellIndex++;
            }

            for(int l=0;l<roleList.size();l++){
                XSSFCell cell_ele = row.createCell(cellIndex);
                cell_ele.setCellValue(roleList.get(l));
                cellIndex++;
            }
            _Column++;
            return ;
        }

        System.out.println("---------------->joinList size"+joinList.size());

        for(int i=0;i<joinList.size();i++){
            cellIndex=0;
            row = _Sheet.createRow(_Column);
            List<String> eleList = joinList.get(i);
            for(int k=0;k<cellList.size();k++){
                XSSFCell cell_ele = row.createCell(cellIndex);
                cell_ele.setCellValue(cellList.get(k));
                cellIndex++;
            }
            for(int l=0;l<roleList.size();l++){
                XSSFCell cell_ele = row.createCell(cellIndex);
                cell_ele.setCellValue(roleList.get(l));
                cellIndex++;
            }
            cellIndex++;
            for(int j=0;j<eleList.size();j++){
                XSSFCell cell_ele = row.createCell(cellIndex);
                cell_ele.setCellValue(eleList.get(j));
                cellIndex++;
            }
            _Column++;
        }
    }

    /**
     * 创建excel文件函数
     * src为待保存的文件路径,json为待保存的json数据
     * @param
     * @param
     * @return
     */
    @SuppressWarnings({"unchecked", "Duplicates"})
    private net.sf.json.JSONObject createExcel() {
        net.sf.json.JSONObject result = new net.sf.json.JSONObject(); // 用来反馈函数调用结果
        try {
            FileOutputStream outputStream=new FileOutputStream(_OUT_PATH);
            _XssfWorkbook.write(outputStream);
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        catch (Exception e) {
            result.put("result", "failed"); // 将调用该函数的结果返回
            result.put("reason", e.getMessage()); // 将调用该函数失败的原因返回
            e.printStackTrace();
            return result;
        }
        result.put("result", "succeeded");
        System.out.println("succeeded");
        return result;
    }


    public  void getDataSecurities(int offset) throws Exception {
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
        JSONObject jsonObject = JSONObject.fromObject(content);
        System.out.println(jsonObject);

        JSONObject projectObject = JSONObject.fromObject(content);
        JSONArray itemArray = projectObject.getJSONArray("items");

        jsonAdd(itemArray);
        if(itemArray.size()!=0){
            System.out.println("-----------------offset:"+offset+"-----------------");
            getDataSecurities(offset+50);
        }
    }

    public void getUserRoles() throws Exception {
        try{
            String projectTaskUrl="https://ejwn-test.fa.us2.oraclecloud.com/hcmRestApi/scim/Users";
            URIBuilder uriBuilder = new URIBuilder(projectTaskUrl);

            String content = HttpUtil.httpGetResponse(authToken, uriBuilder.build().toString());

            _UR_JSONOJB = JSONObject.fromObject(content);

            System.out.println("************user roles 获取成功************");
        }catch (Exception e){
            System.out.println("************user roles 获取失败************");
            throw new Exception();
        }

    }

    private  void jsonAdd(JSONArray jsonArray){
        for(int i=0; i<jsonArray.size(); i++){
            System.out.println(jsonArray.getJSONObject(i));
            _TempJSONResult.add(jsonArray.getJSONObject(i));
        }

    }

    /**
     * 读取json数据并解析
     *
     * @return
     * @throws IOException
     */
    @SuppressWarnings("Duplicates")
    private net.sf.json.JSONObject getJsonData(String outPath) throws IOException {

        File file = new File(outPath);
        net.sf.json.JSONObject jsonObject = null;
        try {
            String input = FileUtils.readFileToString(file, "UTF-8");
            jsonObject = net.sf.json.JSONObject.fromObject(input);
            if (jsonObject == null) {
                throw new Exception(outPath + "未读取到json");
            }
        } catch (Exception e) {

            System.out.println("****" + outPath + "格式有误****");
            throw new IOException();
        }
        System.out.println("****" + outPath + "缓存成功****");
        return jsonObject;
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

