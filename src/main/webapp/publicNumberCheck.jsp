<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公众号审批列表</title>
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
		$('.c1').combobox({ 
		    url:'userPublicNumber/queryUid',    
		    valueField:'id',    
		    textField:'name', 
		    onLoadSuccess:function(data){
		    	$('.c2').combobox({ 
				    url:'userPublicNumber/queryPublicId',    
				    valueField:'id',    
				    textField:'publicId', 
					onLoadSuccess:function(data){
						$("#dg").datagrid({
							idField:"id",
							url:"publicNumberCheck/queryByPage",
							queryParams: form2Json("searchform"),//查询参数
							columns:[[
								{field:"",checkbox:true},
								{field:"id",title:"编号",width:100,align:"center"},
								{field:"pid",title:"公众号编号",width:100,align:"center"},
								{field:"u_id",title:"申请人编号",width:100,align:"center"},
								{field:"applyTime",title:"提交时间",width:100,align:"center"},
								{field:"advise",title:"审核意见",width:100,align:"center"},
								{field:"state",title:"性质",width:100,align:"center",formatter: function(value,row,index){
									if (value==1){
										return "待审核";
									}else if(value==2){
										return "通过";
									}else if(value==3){
										return "不通过";
									}
								}
								},
								{field:"checkedTime",title:"审批时间",width:100,align:"center"}
							]],
							fitColumns:true,
							toolbar: [{
								text:"审核",
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
    				}
		    	});
			}
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
					url:"publicNumberCheck/deleteMore",
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
	function openFormDialog(uid,pid){
		$("#dd").dialog({
			buttons:[{
				text:'通过',
				iconCls:"icon-save",
				handler:function(){
					saveOK(uid,pid);
					$("#dg").datagrid("clearSelections");
				}
			},{
				text:'不通过',
				iconCls:"icon-cancel",
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
		url = "publicNumberCheck/add";
		//打开弹出框
		openFormDialog();
		//设置弹出框标题
		$("#dd").dialog("setTitle","新增公众号审批信息");
	}
	//审核记录
	function update(){
		//重置表单内容
		$("#ff").form("reset");
		var array = $("#dg").datagrid("getSelections");
		if(array.length==0){
			$.messager.alert("提示","请选择要审核的记录","info");
			return;
		}else if(array.length>1){
			$.messager.alert("提示","每次只能审核一条记录","info");
			return;
		}
      	//修改记录的请求地址
		url = "publicNumberCheck/update?id="+array[0].id;
		//表单填充内容
        $("#ff").form("load",array[0]);
        uids = array[0].u_id;
        pids = array[0].pid;
        
      	//打开弹出框
		openFormDialog(uids,pids);
		//设置弹出框标题
		$("#dd").dialog("setTitle","修改公众号审批信息");
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
	
	
	function saveOK(uid,pid){
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
			},
		});
		$.ajax({
			url:"userPublicNumber/savePublicNumber",
			type:"post",
			data:"uid="+uid+"&pid="+pid,
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
			url:"DownServlet?filename=公众号审批信息.xls",
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
			公众号编号：<input class="c2" type="text" name="pid" class="easyui-textbox" >
			申请人编号：<input class="c1" type="text" name="u_id" class="easyui-textbox">
			审核是否通过：<select name="state"  class="easyui-combobox">
		            <option value="-1">--请选择--</option>
		            <option value="1">待审核</option>
		            <option value="2">通过</option>
		            <option value="3">不通过</option>
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
					<td><input class="c2" type="text" name="pid" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">申请人编号：</td>
					<td><input class="c1" type="text" name="u_id" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">提交时间：</td>
					<td><input type="text" name="applyTime" class="easyui-datebox" data-options="required:true,editable:false"></td>
				</tr>
				<tr>
					<td class="right">审核意见：</td>
					<td><input type="text" name="advise" class="easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="right">审核是否通过：</td>
					<td>
						<select name="state"  class="easyui-combobox">
				            <option value="-1">--请选择--</option>
				            <option value="1">待审核</option>
				            <option value="2">通过</option>
				            <option value="3">不通过</option>
		        		</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>