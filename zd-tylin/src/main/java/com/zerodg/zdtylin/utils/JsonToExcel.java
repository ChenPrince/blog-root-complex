package com.zerodg.zdtylin.utils;


import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @program: eroot
 * @description:
 * 工具类
 * 在构造函数中传入JSONObject||_JsonFilePath
 * 调用act()导出excel(ps:默认导出路径为桌面)
 * 备注：
 * JSONObject能根据key获取对应的value
 * @author: tokie_chen
 * @create:
 * @date:2019-08-12-08:54
 **/
public class JsonToExcel {

    private JSONObject _JsonObject;
    private String _JsonPath;
    private XSSFWorkbook _XssfWorkbook;
    private XSSFSheet _Sheet;
    private String _OutPath=null;
    private List<String> _BlockLevel;
    private List<String> _CurrPath;
    private int _Column;

    //通过构造函数获取jsonobject
    public JsonToExcel(JSONObject data){
        this._JsonObject=data;
    }

    public JsonToExcel(String _JsonPath) throws IOException {
        this._JsonPath=_JsonPath;
        this._JsonObject=getJsonData();
    }

    /**
     * 入口
     * @param name
     * @throws IOException
     */
    public void act(String name) throws IOException {
        if(_JsonObject==null){
            throw new NullPointerException("_JsonObject is NULL");
        }
        System.out.println(_JsonObject);
        init(name);
        JSONObject result = createExcel( _JsonObject);
        System.out.println(result);
    }

    /**
     * 初始化：
     * workbook
     * sheet
     * 输出路径
     * 输出对象
     * @param fileName
     * @throws IOException
     */
    private void init(String fileName) throws IOException {
        FileSystemView fsv  = FileSystemView.getFileSystemView();
        File path=fsv.getHomeDirectory();
        String deskTopPath = path.getPath();
        _OutPath= deskTopPath+"\\"+fileName+".xlsx";
        InputStream is = new FileInputStream(_OutPath);
        // 2007版本后使用XSSFWorkbook
        _XssfWorkbook = new XSSFWorkbook(is);
        _Sheet = _XssfWorkbook.getSheetAt(0);
        _Column=2;
        _BlockLevel=new LinkedList<>();
        _CurrPath=new LinkedList<>();
    }

    /**
     * 读取json数据并解析
     * @return
     * @throws IOException
     */
    private JSONObject getJsonData() {

        File file = new File(_JsonPath);
        JSONObject _JsonObject = null;
        try {
            String input = FileUtils.readFileToString(file, "UTF-8");
            _JsonObject = JSONObject.fromObject(input);
            if(_JsonObject ==null){
                throw new Exception("未读取到 json data");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("数据载入成功");
        return _JsonObject;
    }


    /**
     * 创建excel文件函数
     * src为待保存的文件路径,json为待保存的json数据
     * @param
     * @param
     * @return
     */
    @SuppressWarnings("unchecked")
    private JSONObject createExcel(JSONObject jsonObject) {
        StringBuffer sb=new StringBuffer("1");
        JSONObject result = new JSONObject(); // 用来反馈函数调用结果
        try {
            createWorkbookEntry(jsonObject);
            FileOutputStream outputStream=new FileOutputStream(_OutPath);
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
     * 递归创建工作目录-入口
     * 需要找到每一个jsonobject遍历完成的地方，删掉最后一个值
     * @param JsonObject
     * @return
     */
    private List<String> createWorkbookEntry(JSONObject JsonObject){

        List<String> keyList = new LinkedList<>();
        Iterator<String> keys = JsonObject.keySet().iterator();

        //这里能获取一层的字段
        while (keys.hasNext()) {
            String key = keys.next();
            if (JsonObject.get(key) instanceof JSONObject) {

                JSONObject innerObject = (JSONObject) JsonObject.get(key);
                _CurrPath.add(key);
                keyList.addAll(createWorkbookEntry(innerObject));
                _CurrPath.remove(_CurrPath.size()-1);
            } else if (JsonObject.get(key) instanceof JSONArray) {

                JSONArray innerArray = (JSONArray) JsonObject.get(key);
                _CurrPath.add(key);
                keyList.addAll(createWorkbook(innerArray));
                _CurrPath.remove(_CurrPath.size()-1);
            }
            else{
                _CurrPath.add(key);
                _BlockLevel.add(JsonObject.get(key).toString());
                print();
                createRow(JsonObject.get(key).toString());
                //删掉最后一个
                _CurrPath.remove(_CurrPath.size()-1);
            }
        }
        //删掉最后一个
//        _BlockLevel.remove(_BlockLevel.size()-1);
        return keyList;
    }


    private List<String> createWorkbook(JSONArray jsonArray){
        List<String> keyList = new LinkedList<>();
        if (jsonArray != null ) {
            Iterator i1 = jsonArray.iterator();
            while (i1.hasNext()) {
                Object key = i1.next();
                if (key instanceof  JSONObject) {
                    JSONObject innerObject = (JSONObject) key;
                    keyList.addAll(createWorkbookEntry(innerObject));
                } else if (key instanceof JSONArray) {
                    JSONArray innerArray = (JSONArray) key;
                    keyList.addAll(createWorkbook(innerArray));
                }
            }
        }
        return keyList;
    }

    /**
     * 创建当前行
     * @param value
     */
    private void createRow(String value){
        //创建这一层
        XSSFRow row = _Sheet.createRow(_Column);
        int i=0;
        for(;i<_BlockLevel.size();i++){
            XSSFCell cell_ele = row.createCell(i);
            cell_ele.setCellValue(_BlockLevel.get(i));
        }
        XSSFCell cell_ele = row.createCell(i);
        cell_ele.setCellValue(value);
        _Column++;
    }

    private void print(){
        System.out.print("（1）curr_value_path::");
        for(int i=0;i<_BlockLevel.size();i++){
            System.out.print("==>"+_BlockLevel.get(i));
        }
        System.out.print("（2）curr_path::");
        for(int i=0;i<_CurrPath.size();i++){
            System.out.print("==>"+_CurrPath.get(i));
        }
        System.out.println();
    }

    private void saveDataToFile(String fileName, String data) {

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



