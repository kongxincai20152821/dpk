<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公众号列表</title>
<script type="text/javascript">

	//将表单数据转为json
	function form2Json(id) {
	 
	    var arr = $("#" + id).serializeArray();
	    var jsonStr = "";
	
	   	jsonStr += '{';
	    for (var i = 0; i < arr.length; i++) {
	        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",'
	    }
	    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
	    jsonStr += '}'
	
	    var json = JSON.parse(jsonStr);
	    return json;
	}
	
	var url = "";
	$(function(){
		
		$("#dg").datagrid({
			idField:"id",
			url:"publicNumber/queryByPage",
			queryParams: form2Json("searchform"),//查询参数
			columns:[[
				{field:"",checkbox:true},
				{field:"id",title:"编号",width:100,align:"center"},
				{field:"publicId",title:"公众号账号",width:100,align:"center"},
				{field:"publicName",title:"昵称",width:100,align:"center"},
				{field:"publicType",title:"性质",width:100,align:"center",formatter: function(value,row,index){
					if (value==1){
						return "订阅号";
					}else if(value==2){
						return "服务号";
					}else if(value==3){
						return "企业号";
					}
				}
				},
				{field:"name",title:"注册人名",width:100,align:"center"},
				{field:"phone",title:"注册联系方式",width:100,align:"center"},
				{field:"address",title:"注册联系地址",width:100,align:"center"},
				{field:"logo",title:"logo",width:100,align:"center"},
				{field:"regDate",title:"注册日期",width:100,align:"center"},
				{field:"createTime",title:"创建时间",width:100,align:"center"}
			]],
			fitColumns:true,
			toolbar: [{
				text:"新增",
				iconCls: 'icon-add',
				handler: function(){
					add();
				}
			},'-',{
				text:"修改",
				iconCls: 'icon-edit',
				handler: function(){
					update();
				}
			},'-',{
				text:"删除",
				iconCls: 'icon-remove',
				handler: function(){
					remove();
				}
			}],
			pagination:true,
			pageSize:30
		});
		
		//点击搜索
		$("#search_btn").click(function() {
            $('#dg').datagrid({ 
            	//在请求远程数据的时候发送额外的参数。
            	queryParams: form2Json("searchform")
            });   
        });
		
		//点击导入
		$("#ud_btn").click(function() {
          	var filename = $("#file").filebox("getText");
            if(filename==""){
            	$.messager.alert("提示","请选择文件","info");
            }else{
            	ud();
            }
        });
		
		
		//点击导出
		$("#download_btn").click(function() {
			download();
        });
		
		
	});
	//删除记录
	function remove(){
		var array = $("#dg").datagrid("getSelections");
		if(array.length==0){
			$.messager.alert("提示","请选择要删除的记录","info");
			return;
		}
		$.messager.confirm("提示","你确定要删除这"+array.length+"条记录吗？",function(r){
			if(r){
				var ids = "";
				for(var i=0;i<array.length;i++){
					ids += array[i].id+",";
				}
				ids = ids.substring(0,ids.length-1);
				$.ajax({
					url:"publicNumber/deleteMore",
					type:"post",
					data:"ids="+ids,
					dataType:"json",
					success:function(result){
						if(result.state==0){
							//刷新页面
							$("#dg").datagrid("reload");
							$.messager.alert("提示",result.msg,"info");
						}else{
							$.messager.alert("提示",result.msg,"error");
						}
						//清除之前选择的所有行
						$("#dg").datagrid("clearSelections");
					}
				});
			}
		});
	}
	//打开弹出框
	function openFormDialog(){
		$("#dd").dialog({
			buttons:[{
				text:'保存',
				iconCls:"icon-save",
				handler:function(){
					save();
					$("#dg").datagrid("clearSelections");
				}
			},{
				text:'关闭',
				iconCls:"icon-cancel",
				handler:function(){
					$("#dd").dialog("close");
				}
			}]
		});
		//打开对话框
		$("#dd").dialog("open");
	}
	//新增记录
	function add(){
		//重置表单内容
        $("#ff").form("reset");
		//新增记录的请求地址
		url = "publicNumber/add";
		//打开弹出框
		openFormDialog();
		//设置弹出框标题
		$("#dd").dialog("setTitle","新增公众号信息");
	}
	//修改记录
	function update(){
		//重置表单内容
		$("#ff").form("reset");
		var array = $("#dg").datagrid("getSelections");
		if(array.length==0){
			$.messager.alert("提示","请选择要修改的记录","info");
			return;
		}else if(array.length>1){
			$.messager.alert("提示","每次只能修改一条记录","info");
			return;
		}
      	//修改记录的请求地址
		url = "publicNumber/update?id="+array[0].id;
		//表单填充内容
        $("#ff").form("load",array[0]);
      	//打开弹出框
		openFormDialog();
		//设置弹出框标题
		$("#dd").dialog("setTitle","修改公众号信息");
	}
	//保存或者更新数据
	function save(){
		$("#ff").form("submit",{
			url:url,
			onSubmit:function(){
				//调用validate方法校验表单中所有的字段有效性，只有所有的字段有效才返回true
				return $(this).form("validate");
			},
			success:function(result){
				//接收服务器返回的json格式字符串数据转换成json对象
			 	var data = eval('(' + result + ')');   
		        if (data.state==0){    
		           	$.messager.alert("提示消息",data.msg,"info"); 
		           	//刷新列表
		           	$("#dg").datagrid("reload");
		        } else{
		        	$.messager.alert("提示消息",data.msg,"error");
		        }
		        //关闭弹出框
		        $("#dd").dialog("close");
			}
		});
	}
	
	//导入
	function ud(){
		$("#searchform").form("submit",{
			url:"Upload111",
			onSubmit:function(){
				//调用validate方法校验表单中所有的字段有效性，只有所有的字段有效才返回true
				return $(this).form("validate");
			},
			success:function(result){
				$.messager.alert("提示","导入成功","info");
            	$("#dg").datagrid("reload");
			}
		});
	}
	
	//导出
	function download(){
		$("#searchform").form("submit",{
			url:"DownServlet?filename=公众号信息.xls",
			success:function(result){
				$.messager.alert("提示","导出成功","info");
            	$("#dg").datagrid("reload");
			}
		});
	}
	
	
</script>
<style type="text/css">
	
	.right{
		text-align: right;
	}
</style>
</head>
<body>
	<div style="height: 10%">
		<form name="searchform" method="post" id ="searchform" enctype="multipart/form-data">
			公众号账号：<input type="text" name="publicId" class="easyui-textbox" >
			昵称：<input type="text" name="publicName" class="easyui-textbox">
			注册人名：<input type="text" name="name" class="easyui-textbox">
			性质：<select name="publicType"  class="easyui-combobox">
		            <option value="-1">--请选择--</option>
		            <option value="1">订阅号</option>
		            <option value="2">服务号</option>
		            <option value="3">企业号</option>
		        </select>
		        <a id="search_btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
	  			
	  			<input id="file" class="easyui-filebox" name="file" data-options="buttonText:'选择文件'">
				<a id="ud_btn" href="#" class="easyui-linkbutton" >导入</a>
				<a id="download_btn" href="#" class="easyui-linkbutton">导出</a>
	  	</form>
	  	
	  	
  	</div>
  	<div style="height: 90%">
		<table id="dg" fit="true"></table>
	</div>
	<div id="dd" class="easyui-dialog" style="width: 400px;text-align: center;padding: 10px;" closed="true">
		<form id="ff" method="post">
			<table style="margin: 0 auto;">
				<tr>
					<td class="right">公众号账号：</td>
					<td><input type="text" name="publicId" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">昵称：</td>
					<td><input type="text" name="publicName" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">性质：</td>
					<td>
						<select name="publicType"  class="easyui-combobox">
				            <option value="-1">--请选择--</option>
				            <option value="1">订阅号</option>
				            <option value="2">服务号</option>
				            <option value="3">企业号</option>
		        		</select>
					</td>
				</tr>
				<tr>
					<td class="right">注册人名：</td>
					<td><input type="text" name="name" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">联系方式：</td>
					<td><input type="text" name="phone" class="easyui-numberbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">联系地址：</td>
					<td><input type="text" name="address" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">logo：</td>
					<td><input type="text" name="logo" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">注册日期：</td>
					<td><input type="text" name="regDate" class="easyui-datebox" data-options="required:true,editable:false"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>