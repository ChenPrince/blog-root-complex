package com.zerodg.zdtylin.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @program: eroot
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-08-19-12:16
 **/
public class JsonTest {

    private String _JsonPath;
    private JSONObject _JsonObject;

    public JsonTest(String jsonPath){
        this._JsonPath=jsonPath;
    }

    public void execute() throws IOException {
        _JsonObject=getJsonData();
        JSONArray jsonArray = (JSONArray) _JsonObject.get("data");
        for(int i=0;i<jsonArray.size();i++){
            System.out.println(i);
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.getString("PARTYID"));
            System.out.println(jsonObject.getString("PARTYNUMBER"));
            System.out.println(jsonObject.getString("ORGANIZATIONNAME"));

        }
    }


    /**
     * 读取json数据并解析
     * @return
     * @throws IOException
     */
    @SuppressWarnings("Duplicates")
    public JSONObject getJsonData() throws IOException {
        File file = new File(_JsonPath);
        JSONObject jsonObject = null;
        try {
            String input = FileUtils.readFileToString(file, "UTF-8");
            jsonObject = JSONObject.parseObject(input);
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


    public void buildJsonObj(){

        JSONObject jsonObject = new JSONObject();
        JSONObject subJsonObject = new JSONObject();
        subJsonObject.put("test4","12121");
        subJsonObject.put("test5",new JSONObject());
        jsonObject.put("test","123");
        jsonObject.put("test2","234");
        jsonObject.put("test3",subJsonObject);
        System.out.println(jsonObject.toString());

    }

}
