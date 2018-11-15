<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.6.6/themes/default/easyui.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.6.6/themes/icon.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.6.6/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery-easyui-1.6.6/locale/easyui-lang-zh_CN.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
 <!--声明css代码域-->
<style type="text/css">
    table{
        margin: auto;
        margin-top: 20px;
    }
    tr{
        height: 40px;
        text-align: center;
    }
</style>
<!--声明js代码域-->
<script type="text/javascript">
    /*校验登录信息*/
        $(function(){

            //登录校验
            $("#btnCon").click(function(){
                //校验用户信息
                if($(":text").val()==""){
                    //使用EasyUI的信息框进行提示
                    $.messager.alert('登录提示',"用户名不能为空！","warning");
                }else if($(":password").val()==""){
                    //使用EasyUI的信息框进行提示
                    $.messager.alert('登录提示',"密码不能为空！","warning");
                }else{
                    $("form").submit();
                }
            })
            //重置
            $("#btnCan").click(function(){
                $("form").get(0).reset();
            })
            
            
        })
</script>
<script type="text/javascript">
	$(function(){
		var value=getQueryString("error");
		var value2=getQueryString("userName");
		var value3=getQueryString("password");
		if(value==2){
			$("#error").html("*验证码错误！");
			$("#userName").val(value2);
			$("#password").val(value3);
	  	}
		if(value==1){
			$("#error").html("*用户名或者密码错误！");
			$("#userName").val(value2);
			$("#password").val(value3);
	  	}
	});
</script>
</head>
<body>
	<div id="panel_login" style="margin: auto;width: 500px;margin-top: 100px;">
            <!--创建登录面板-->
            <div id="login" class="easyui-panel" title="登录" data-options="iconCls:'icon-mlogin',minimizable:true,maximizable:true
                ,collapsible:true,closable:true" style="width: 500px;height: 240px;">
                <form action="user/login.do" method="post">
                    <table>
                        <tr>
                            <td>用户名:</td>
                            <td>
                                <input type="text" id="userName" name="userName" value="${user.userName }"/>
                            </td>
                        </tr>
                        <tr>
                            <td>密&nbsp;&nbsp;&nbsp;码:</td>
                            <td>
                                <input type="password" id="password" name="password" value="${user.password }" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <font id="error" color="red">${error }</font>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <a id="btnCon" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'">登录</a>&nbsp; &nbsp;&nbsp;
                                <a id="btnCan" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">重置</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
</body>
</html>