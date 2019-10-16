package com.zerodg.zdtylin.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;


/**
 * @program: eroot
 * @description: userRole 连接 dataSecurities
 * 连接关系：
 * userRoles.userName ----1:n---> dataSecurities.Userrf
 * userRoles
 * @author: ztz-prince
 * @create:
 * @date:2019-08-16-08:59
 **/

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class UserRolesJoinDataSecurities {

    private String _INPUT_PATH1;
    private String _INPUT_PATH2;
    private String _OUT_PATH;
    private String _FILE_NAME = null;
    private JSONObject _UR_JSONOJB;
    private JSONObject _DS_JSONOBJ;
    private XSSFWorkbook _XssfWorkbook;
    private XSSFSheet _Sheet;
    private int _Column;
    private List<List<Object>> resultList;

    /**
     * @param inputPath1
     * @param inputPath2
     */
    public UserRolesJoinDataSecurities(String inputPath1, String inputPath2) {
        this._INPUT_PATH1 = inputPath1;
        this._INPUT_PATH2 = inputPath2;
    }

    /**
     * @param inputPath1
     * @param inputPath2
     * @param fileName
     */
    public UserRolesJoinDataSecurities(String inputPath1, String inputPath2, String fileName) {
        this._INPUT_PATH1 = inputPath1;
        this._INPUT_PATH2 = inputPath2;
        this._FILE_NAME = fileName;
    }

    /**
     * 初始化
     * @param fileName
     * @throws IOException
     */
    private void init(String fileName) throws IOException {

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

        _UR_JSONOJB = getJsonData(_INPUT_PATH1);
        _DS_JSONOBJ = getJsonData(_INPUT_PATH2);

        InputStream is = new FileInputStream(_OUT_PATH);
        _XssfWorkbook = new XSSFWorkbook(is);
        _Sheet = _XssfWorkbook.getSheetAt(0);
        _Column=2;

        resultList=new LinkedList<>();

    }

    /**
     * 执行 entry
     * @throws IOException
     */
    public void execute() throws IOException {
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
    private JSONObject createExcel() {
        JSONObject result = new JSONObject(); // 用来反馈函数调用结果
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
        result.put("result", "successed");
        return result;
    }

    /**
     * 读取json数据并解析
     *
     * @return
     * @throws IOException
     */
    @SuppressWarnings("Duplicates")
    private JSONObject getJsonData(String outPath) throws IOException {

        File file = new File(outPath);
        JSONObject jsonObject = null;
        try {
            String input = FileUtils.readFileToString(file, "UTF-8");
            jsonObject = JSONObject.fromObject(input);
            if (jsonObject == null) {
                throw new Exception(outPath + " 未读取到json");
            }

        } catch (Exception e) {

            System.out.println("****" + outPath + "格式有误****");
        }
        System.out.println("****" + outPath + "缓存成功****");
        return jsonObject;
    }



}
