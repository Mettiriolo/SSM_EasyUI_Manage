<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>

	<!-- 引入JQuery -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.6.6/jquery.min.js"></script>
	<!-- 引入EasyUI -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
	<!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.6.6/locale/easyui-lang-zh_CN.js"></script>
	<!-- 引入EasyUI的样式文件-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.6.6/themes/default/easyui.css" type="text/css"/>
	<!-- 引入EasyUI的图标样式文件-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui-1.6.6/themes/icon.css" type="text/css"/>
	<script type="text/javascript">
	
	function newUser(){
		$('#dlg').dialog('open').dialog('setTitle','New User');
		$('#fm').form('clear');
		url = 'addUser.do';
	}
	
	function saveUser(){
		$('#fm').form('submit',{
			url: url,
			onSubmit: function(){
				return $(this).form('validate');
			},
			success: function(result){
				var result = eval('('+result+')');
				if (result.errorMsg){
					$.messager.show({
						title: 'Error',
						msg: result.errorMsg
					});
				} else {
					$('#dlg').dialog('close');		// close the dialog
					$('#dg').datagrid('reload');	// reload the user data
				}
			}
		});
	}
	
	 function deleteUser(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$.messager.confirm('提示信息','您确定要删除这条信息吗?',function(r){
				if (r){
					$.post('deleteUser.do',{userId:row.userId},function(result){
							$('#dg').datagrid('reload');	// reload the user data
					},'json');
				}
			});
		}else{
			$.messager.confirm("提示信息","请选择要删除的用户！","warning");
		}
	} 
	 function recoveryUser(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$.messager.confirm('提示信息','您确定要恢复这条信息吗?',function(r){
				if (r){
					$.post('recoveryUser.do',{userId:row.userId},function(result){
							$('#dg').datagrid('reload');	// reload the user data
					},'json');
				}
			});
		}else{
			$.messager.confirm("提示信息","请选择要恢复的用户！","warning");
		}
	} 
	
	function editUser(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$('#fm').form('load',row);  
			$('#dlg').dialog('open').dialog('setTitle','Edit User');
			url = 'updateUser.do';
		}else{
			$.messager.confirm("提示信息","请选择要修改的用户！","warning");
		}
	}
	//下拉框
	 $(function () {
	       $('#cc').combobox({
	           url: "findRole.do",
	           valueField: 'roleId',
	           textField: 'roleName',
	           editable: true,
	           //返回要显示的过滤数据
	           loadFilter: function (data) {
	               var o = [{ 'roleId': '0', 'roleName': '--请选择--'}];
	               return o.concat(data);
	           },
	           //当远程数据加载成功时触发 
	           onLoadSuccess: function (data) {
	               var data = $('#cc').combobox('getData');
	               if (data.length > 0) {
	                   $("#cc").combobox('select', data[0].roleId);
	               }
	           },
	           onSelect: function (record) {
	             //获取values
	              alert($("#cc").combobox("getValues"));
	             // 获取text
	              alert($("#cc").combobox("getText"));
	           }
	
	       });
	   }); 
	 
	</script>
	
<body>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser()">删除</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-permission" plain="true" onclick="">权限管理</a>
</div>
<table id="dg" title="用户信息" class="easyui-datagrid" style="width:98.8%;"
		url="findAllUser.do"
        toolbar="#toolbar"
        rownumbers="true" fitColumns="true"  collapsible="true" pagination="true">
    <thead>
        <tr>
        	<th field="ck" width="50" checkbox="true"></th>
            <th field="userId" width="40">编号</th>
            <th field="userName" width="40">姓名</th>
            <th field="password" width="50">密码</th>
            <th field="userType" width="40">用户类型</th>
            <th field="roleId" width="40">部门</th>
            <th field="userDescription" width="40" >描述</th>
        </tr>
    </thead>
</table>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
        closed="true" buttons="#dlg-buttons">
    <div class="ftitle">用户信息编辑框</div>
    <form id="fm" method="post">
    	<table>
    		<tr>
    			<td><label></label></td>
    			<td><input name="userId" class="easyui-validatebox" type="hidden"></td>
    		</tr>
    		
    		<tr>
    			<td><label>姓名:</label></td>
    			<td><input name="userName" class="easyui-validatebox" ></td>
    		</tr>
    		
    		<tr>
    			<td><label>密码:</label></td>
    			<td><input name="userPwd" class="easyui-validatebox" ></td>
    		</tr>
    		<tr>
    			<td><label>类型:</label></td>
    			<td><input name="remark" class="easyui-validatebox" ></td>
    		</tr>
    		<tr>
    			<td><label>角色:</label></td>
				<td><input class="easyui-combobox" id="cc" name="role"  
				   url="findRole.do" valueField="roleId" textField="roleName" multiple="false">
				</td>
			</tr>
    		<tr>
    			<td><label>描述:</label></td>
    			<td><input name="del" class="easyui-validatebox" ></td>
    		</tr>
    	</table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>
</body>
</html>