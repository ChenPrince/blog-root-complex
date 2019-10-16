package com.zerodg.zdtylin.utils;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import java.io.IOException;



/**
 * @program: eroot
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-08-12-09:01
 **/
public class Test{
    public static void main(String[] args) {

        //            String jsonPath1="C:/Users/ztz-prince/Desktop/userRoles.json";
//            String jsonPath2="C:/Users/ztz-prince/Desktop/dataSecurities.json";
//            UserRolesJoinDataSecurities userRolesJoinDataSecurities=new UserRolesJoinDataSecurities(jsonPath1,jsonPath2);
//            userRolesJoinDataSecurities.execute();
        JsonTest jsonTest=new JsonTest("C:/Users/ztz-prince/Desktop/test.json");
//            jsonTest.getJsonData();
        jsonTest.buildJsonObj();
    }


}
