<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	
	function newRole(){
		$('#dlg').dialog('open').dialog('setTitle','New Role');
		$('#fm').form('clear');
		url = 'addRole.do';
	}
	
	function saveRole(){
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
	

	
	 function deleteRole(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$.messager.confirm('提示信息','您确定要删除这条信息吗?',function(r){
				if (r){
					$.post('deleteRole.do',{roleId:row.roleId},function(result){
							$('#dg').datagrid('reload');	// reload the user data
					},'json');
				}
			});
		}else{
			$.messager.confirm("提示信息","请选择要删除的用户！","warning");
		}
	} 
	 function recoveryRole(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$.messager.confirm('提示信息','您确定要恢复这条信息吗?',function(r){
				if (r){
					$.post('recoveryRole.do',{roleId:row.roleId},function(result){
							$('#dg').datagrid('reload');	// reload the user data
					},'json');
				}
			});
		}else{
			$.messager.confirm("提示信息","请选择要恢复的用户！","warning");
		}
	} 
	
	function editRole(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			 $('#fm').form('load',row);  
			$('#dlg').dialog('ope n').dialog('setTitle','Edit Role');
			url = 'updateRole.do';
		}else{
			$.messager.confirm("提示信息","请选择要修改的用户！","warning");
		}
	}
	
	//权限配置
	function addPower(){
		$('#dlgp').dialog('open').dialog('setTitle','Edit Power');
	}
		
	</script>
	
<body>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRole()">新建</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRole()">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRole()">删除</a>
</div>
<table id="dg" title="用户信息" class="easyui-datagrid" style="width:98.8%;"
		url="findAllAuth.do"
        toolbar="#toolbar"
        rownumbers="true" fitColumns="true"  collapsible="true" pagination="true">
    <thead>
        <tr>
        	<th field="ck" width="50" checkbox="true"></th>
            <th field="authId" width="40">编号</th>
            <th field="authName" width="40">名称</th>
            <th field="authPath" width="40">路径</th>
            <th field="parentId" width="40">父节点</th>
            <th field="authDescription" width="40">描述</th>
            <th field="state" width="40">状态</th>
            <th field="iconCls" width="40">图标</th>
        </tr>
    </thead>
</table>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
        closed="true" buttons="#dlg-buttons">
    <div class="ftitle">角色信息编辑框</div>
    <form id="fm" method="post">
    	<table>
    		<tr>
    			<td><label></label></td>
    			<td><input name="roleId" class="easyui-validatebox" type="hidden"></td>
    		</tr>
    		
    		<tr>
    			<td><label>姓名:</label></td>
    			<td><input name="roleName" class="easyui-validatebox" ></td>
    		</tr>
    		
    		<tr>
    			<td><label>备注:</label></td>
    			<td><input name="remark" class="easyui-validatebox" ></td>
    		</tr>
    		<tr>
    			<td><label>标记:</label></td>
    			<td><input name="del" class="easyui-validatebox" ></td>
    		</tr>
    		
    	</table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>

<!-- 权限管理框 -->
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
        closed="true" buttons="#dlg-buttons">
    <div class="ftitle">角色信息编辑框</div>
    <form id="fm" method="post">
    	<table>
    		<tr>
    			<td><label></label></td>
    			<td><input name="roleId" class="easyui-validatebox" type="hidden"></td>
    		</tr>
    		
    		<tr>
    			<td><label>姓名:</label></td>
    			<td><input name="roleName" class="easyui-validatebox" ></td>
    		</tr>
    		
    		<tr>
    			<td><label>备注:</label></td>
    			<td><input name="remark" class="easyui-validatebox" ></td>
    		</tr>
    		<tr>
    			<td><label>标记:</label></td>
    			<td><input name="del" class="easyui-validatebox" ></td>
    		</tr>
    		
    	</table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>
</div>
</body>
</html>