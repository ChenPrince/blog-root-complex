package com.zerodg.zdutil.util.JsonToExcel;


import jdk.nashorn.api.scripting.JSObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;



import java.io.*;
import java.util.Scanner;

/**
 * @program: qfsx_root
 * @description: 导出excel
 * @author: ztz-prince
 * @create:
 * @date:2019-08-07-11:17
 **/
public class ExportExcel {

    //    json读取路径
    private String JSON_PATH = null;
    private String OUT_PATH = "C:/Users/ztz-prince/Desktop/test.xlsx";

    public ExportExcel(){

    }

    public ExportExcel(String JSON_PATH){
        this.JSON_PATH=JSON_PATH;
    }

    /**
     * 设置json数据路径
     * @param JSON_PATH
     */
    public void setJSON_PATH(String JSON_PATH) {
        this.JSON_PATH = JSON_PATH;
    }

    public void setOUT_PATH(String OUT_PATH){
        this.OUT_PATH=OUT_PATH;
    }


    /**
     * 执行导出
     * @return
     */
    public Boolean executeExport(){

        if(JSON_PATH==null){
            return false;
        }
        try{

            InputStream is = new FileInputStream(OUT_PATH);
            // 2007版本后使用XSSFWorkbook
            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            JSONObject jsonData =getJsonData();
            int colunm = 1;

            System.out.println("****读入resource");
            JSONArray resourcesModular=(JSONArray)jsonData.get("Resources");
            // 遍历resource
            if(resourcesModular.size()>0) {
                //第二级目录
                System.out.println("==>遍历resource");
                for (int j = 0; j < resourcesModular.size(); j++) {
                    System.out.println("-->当前resource["+j+"]");

                    JSONObject resourcesModularOBJ = resourcesModular.getJSONObject(j);

                    String resourceId = resourcesModularOBJ.get("id")==null?"/":resourcesModularOBJ.get("id").toString();
                    String resourceUserName = resourcesModularOBJ.get("userName")==null?"/":resourcesModularOBJ.get("userName").toString();
                    String resourceDisplayName = resourcesModularOBJ.get("displayName")==null?"/":resourcesModularOBJ.get("displayName").toString();

                    //获取下一层
                    JSONArray rolesModular = (JSONArray) resourcesModularOBJ.get("roles");

                    //若不存在role就读取下一个
                    if(rolesModular==null){
                        //保存当前行
                        XSSFRow row = sheet.createRow(colunm);
                        //这三个要纵向合并
                        XSSFCell cell_resourceId = row.createCell(0);
                        cell_resourceId.setCellValue(resourceId);
                        XSSFCell cell_resourceUserName = row.createCell(1);
                        cell_resourceUserName.setCellValue(resourceUserName);
                        XSSFCell cell_resourceDisplayName = row.createCell(2);
                        cell_resourceDisplayName.setCellValue(resourceDisplayName);
                        colunm++;

                        continue;
                    }
                    //遍历下一层
                    int firstCol=colunm;
                    if(rolesModular.size()>0){
                        //遍历roles
                        System.out.println("==>遍历roles");

                        try{

                            System.out.println("firstCol:"+firstCol+"-lastCol:"+(colunm+rolesModular.size()));
                            sheet.addMergedRegion(new CellRangeAddress(firstCol, colunm+rolesModular.size(),0,0));//起始行号，终止行号， 起始列号，终止列号
                            sheet.addMergedRegion(new CellRangeAddress(firstCol, colunm+rolesModular.size(),1,1));
                            sheet.addMergedRegion(new CellRangeAddress(firstCol, colunm+rolesModular.size(),2,2));
                            System.out.println("****合并成功");

                        }catch(Exception e){
                            System.out.println("错误提示：（1）单元格合并有冲突,");
                            System.out.println(e.getMessage());
                            Scanner sc=new Scanner(System.in);
                            sc.nextInt();
                            continue;
                        }
                        for(int k=0;k<rolesModular.size();k++){
                            System.out.println("-->当前role["+k+"]");
                            JSONObject rolesModularOBJ = rolesModular.getJSONObject(k);
                            String rolesId = rolesModularOBJ.get("id")==null?"/":rolesModularOBJ.get("id").toString();
                            String rolesValue = rolesModularOBJ.get("value")==null?"/":rolesModularOBJ.get("value").toString();
                            String roleDisplayName = rolesModularOBJ.get("displayName")==null?"/":rolesModularOBJ.get("displayName").toString();

                            //写入输出流
                            XSSFRow row = sheet.createRow(colunm);
                            XSSFCell cell_resourceId = row.createCell(0);
                            cell_resourceId.setCellValue(resourceId);
                            XSSFCell cell_resourceUserName = row.createCell(1);
                            cell_resourceUserName.setCellValue(resourceUserName);
                            XSSFCell cell_resourceDisplayName = row.createCell(2);
                            cell_resourceDisplayName.setCellValue(resourceDisplayName);



                            XSSFCell cell_rolesId = row.createCell(3);
                            cell_rolesId.setCellValue(rolesId);
                            XSSFCell cell_rolesValue = row.createCell(4);
                            cell_rolesValue.setCellValue(rolesValue);
                            XSSFCell cell_roleDisplayName = row.createCell(5);
                            cell_roleDisplayName.setCellValue(roleDisplayName);
                            colunm++;

                        }
                        colunm++;

                    }
                    else{
                        XSSFRow row = sheet.createRow(colunm);
                        XSSFCell cell_resourceId = row.createCell(0);
                        cell_resourceId.setCellValue(resourceId);
                        XSSFCell cell_resourceUserName = row.createCell(1);
                        cell_resourceUserName.setCellValue(resourceUserName);
                        XSSFCell cell_resourceDisplayName = row.createCell(2);
                        cell_resourceDisplayName.setCellValue(resourceDisplayName);
                        colunm++;

                    }
                }
            }else{
                colunm++;
            }


            FileOutputStream outputStream = new FileOutputStream(OUT_PATH);
            workbook.write(outputStream);
            System.out.println("写入成功");
            outputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 读取json数据并解析
     * @return
     * @throws IOException
     */
    private JSONObject getJsonData() throws IOException {

        File file = new File(JSON_PATH);
        JSONObject jsonObject = null;
        try {
            String input = FileUtils.readFileToString(file, "UTF-8");
            jsonObject = JSONObject.fromObject(input);
            if(jsonObject ==null){
                throw new Exception("未读取到json");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("json格式有误");
        }
        System.out.println("****缓存成功****");
        return jsonObject;
    }


}
