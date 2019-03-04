<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
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
			url:"user/queryByPage",
			queryParams: form2Json("searchform"),//查询参数
			columns:[[
				{field:"",checkbox:true},
				{field:"id",title:"编号",width:100,align:"center"},
				{field:"username",title:"账号",width:100,align:"center"},
				{field:"password",title:"密码",width:100,align:"center"},
				{field:"name",title:"姓名",width:100,align:"center"},
				{field:"sex",title:"性别",width:100,align:"center",formatter: function(value,row,index){
					if (value==1){
						return "男";
					} else {
						return "女";
					}
				}
				},
				{field:"phone",title:"联系方式",width:100,align:"center"},
				{field:"address",title:"联系地址",width:100,align:"center"},
				{field:"email",title:"邮箱",width:100,align:"center"},
				{field:"photo",title:"用户头像",width:100,align:"center"},
				{field:"role",title:"所属角色",width:100,align:"center",formatter: function(value,row,index){
					if (value==1){
						return "管理员";
					}else if(value==2){
						return "公众号运营员";
					}else if(value==3){
						return "营业员";
					}}
				},	
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
			},'-',{
					text:"绑定公众号",
					iconCls: 'icon-add',
					handler: function(){
						//查出公众号所有信息
						setPublicNumber();
				}
			}],
			pagination:true,
			pageSize:30,
			onLoadSuccess:function(data){
				$("#pNumgrid").datagrid({
					idField:"id",
					url:"publicNumber/queryAll",
					columns:[[
						{field:"",checkbox:true},
						{field:"id",title:"编号",width:100,align:"center"},
						{field:"publicId",title:"公众号账号",width:100,align:"center"},
						{field:"publicName",title:"公众号昵称",width:100,align:"center"}
					]],
					fitColumns:true,
					scrollbarSize:0
				});
			}
		});
		
		 //分配公众号
		function setPublicNumber(){
			//清除之前选择的所有行
			$("#pNumgrid").datagrid("clearSelections");
			//判断是否选中用户
			var array = $("#dg").datagrid("getSelections");
			if(array.length==0){
				$.messager.alert("提示","请选择用户","info");
				return;
			}
			//用户的编号Id
			var uids = "";
			for(var i=0;i<array.length;i++){
				uids += array[i].id+",";
			}
			uids = uids.substring(0,uids.length-1);
			//获取用户已有公众号	
			$.ajax({
				url:"user/queryExistPid",
				type:"post",
				data:"uids="+uids,
				dataType:"json",
				success:function(result){
					$("#pNum").dialog({
						title:"用户列表",
						buttons:[{
							text:'分配公众号',
							iconCls:"icon-save",
							handler:function(){
								savepublicNumber(uids);
							}
						},{
							text:'关闭',
							iconCls:"icon-cancel",
							handler:function(){
								$("#pNum").dialog("close");
							}
						}]
					});
					for(var i=0;i<result.length;i++){
						//根据后台返回的角色id，设置列表框默认选中状态
						$("#pNumgrid").datagrid("selectRecord",result[i].id);
					}
					//打开对话框
					$("#pNum").dialog("open");
				}
			});
			
		}
		//信息提交到公众号审核,审核是否可以绑定
		function savepublicNumber(uids){
			var array = $("#pNumgrid").datagrid("getSelections");
			if(array.length==0){
				$.messager.alert("提示","请选择公众号","info");
				return;
			}
			//公众号的编号Id
			var pids = "";
			for(var i=0;i<array.length;i++){
				pids += array[i].id+",";
			}
			pids = pids.substring(0,pids.length-1);
			$.ajax({
				url:"publicNumberCheck/savePublicNumberCheck",
				type:"post",
				data:"uids="+uids+"&pids="+pids,
				dataType:"json",
				success:function(result){
					if(result.state==0){
						$.messager.alert("提示",result.msg,"info");
					}else{
						$.messager.alert("提示",result.msg,"error");
					}
					//清除之前选择的所有行
					$("#pNumgrid").datagrid("clearSelections");
					//关闭对话框
					$("#pNum").dialog("close");
				}
			});
		} 		
		
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
            	uploadImg();
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
					url:"user/deleteMore",
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
		url = "user/add";
		//打开弹出框
		openFormDialog();
		//设置弹出框标题
		$("#dd").dialog("setTitle","新增用户信息");
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
		url = "user/update?id="+array[0].id;
		//表单填充内容
        $("#ff").form("load",array[0]);
      	//打开弹出框
		openFormDialog();
		//设置弹出框标题
		$("#dd").dialog("setTitle","修改用户信息");
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
	function uploadImg(){
		$("#searchform").form("submit",{
			url:"user/uploadFile",
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
			url:"user/download?fileName=用户信息.xls",
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
			账号：<input type="text" name="username" class="easyui-textbox" >
			姓名：<input type="text" name="name" class="easyui-textbox">
			性别：<select name="sex" id="qsex"  class="easyui-combobox">
		            <option value="-1">--请选择--</option>
		            <option value="1">男</option>
		            <option value="0">女</option>
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
					<td class="right">账号：</td>
					<td><input type="text" name="username" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">密码：</td>
					<td><input type="password" name="password" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">姓名：</td>
					<td><input type="text" name="name" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">性别：</td>
					<td>
						<input type="radio" name="sex" value="1" checked="checked">男
						<input type="radio" name="sex" value="0">女
					</td>
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
					<td class="right">邮箱：</td>
					<td><input type="text" name="email" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">用户头像：</td>
					<td><input type="text" name="photo" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">所属角色：</td>
					<td>
						<select name="role"  class="easyui-combobox">
		            		<option value="-1">--请选择--</option>
		           			<option value="1">管理员</option>
		            		<option value="2">公众号运营员</option>
		            		<option value="3">营业员</option>
		       			</select>
		        	</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 绑定公众号 -->
	<div id="pNum" class="easyui-dialog" style="width: 400px;text-align: center;" closed="true">
		<table id="pNumgrid"></table>
	</div>
</body>
</html>