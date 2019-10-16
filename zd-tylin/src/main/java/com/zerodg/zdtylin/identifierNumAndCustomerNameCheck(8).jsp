<%@page language="java" contentType="textml; charset=UTF-8"%>
<%@ page import="weaver.general.Util"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<jsp:useBean id="log" class="weaver.general.BaseBean" scope="page" />
<jsp:useBean id="RecordSet" class="weaver.conn.RecordSet" scope="page" />
<%

    if(!"".equals(customerNameInput)){
        request = customerNameInput;
    }else{
        result="ERR_NULL";
    }


	try{
        String identifierNumberInput = Util.null2String(request.getParameter("identifierNumber"));
        String customerNameInput = Util.null2String(request.getParameter("Customernameinput"));
        String result="S";

        //已完成中
        //输入客户名不为空
		if("".equals(customerNameInput)){
            String sql="select LE_Identifier from uf_crmInfo where OrganizationName='"+customerNameInput+"'";
            RecordSet.executeSql(sql);
            if(RecordSet.getCounts()>0){
                //返回的编号不为空
                if(!"".equals(RecordSet.getString(1))){

                    if(RecordSet.getString(1).equals(identifierNumberInput)){
                        result="ERR:客户已存在";
                    }else{
                        result="DATA_ERR:纳税人名称"+customerNameInput+"已存在;"+"纳税人名称为"+rs.getString(1);
                    }
                }
                else{
                    //如果纳税人名存在，但是纳税编号不存在，就当作不存在
                }
            }
        }else{
            result="ERR_NULL";
        }

        out.print(result +"123");
	}catch(Exception e){
		out.print("123456"+e.getMessage());
	}
%>
