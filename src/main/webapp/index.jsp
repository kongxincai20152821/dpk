<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单列表</title>
<script type="text/javascript">
	
	$(function(){
		 	$("#nav a").click(function(){
			var title = $(this).text();
			var url = $(this).attr("url");
			if(title){
				if($('#tt').tabs('exists',title)){//选项页存在
					$('#tt').tabs('select',title);//选中
				}else{
					$('#tt').tabs('add',{    
					    title:title,    
					    content:"<iframe src='"+url+"' style='width: 100%;height: 100%' frameborder='0'></iframe>",
					    closable:true 
					});
				}
			}
		});
		 
		$('#tt').tabs({  
		    onSelect:function(title){    
		    	var tab = $('#tt').tabs('getSelected');  // 获取选择的面板
		    	var url = $(this).attr("url");
				$('#tt').tabs('update', {
					tab: tab,
					options: {
						href: url
					}
				});   
		    }
		}); 
		 	
		 	
		/*  $("#nav").tree({
			 url:"menu/queryAll",
			 onClick: function(node){// 在用户点击的时候提示
			 	var title = node.text;
				var url = node.attributes.url;
				if(title&&url!=''){
					if($('#tt').tabs('exists',title)){//选项页存在
						$('#tt').tabs('select',title);//选中
					}else{
						$('#tt').tabs('add',{    
						    title:title,    
						    content:"<iframe src='"+url+"' style='width: 100%;height: 100%' frameborder='0'></iframe>",
						    closable:true 
						});
					}
				}
			 }
		 }); */
	});	
</script>

</head>
<body>
	<div id="cc" class="easyui-layout" fit="true">   
	    <div data-options="region:'north',split:false,border:false" style="height:100px;">
	    	
	    </div>   
	    <div data-options="region:'south',border:false,split:false" style="height:100px;">
	    	
	    </div>   
	    <div data-options="region:'west',title:'导航菜单',iconCls:'icon-save',split:false" style="width:150px;">
	    	<ul class="easyui-tree" fit="true" id="nav">
	          	<a href="#" url="user.jsp"><h2>用户管理</h2></a><br/>
	          	<a href="#" url="publicNumber.jsp"><h2>公众号管理</h2></a><br/>
	          	<a href="#" url="userPublicNumber.jsp"><h2>公众号跟用户绑定管理</h2></a><br/>
	          	<a href="#" url="publicNumberCheck.jsp"><h2>公众号审批管理</h2></a><br/>
	          	<a href="#" url="user.jsp"><h2>内容模板管理</h2></a><br/>
	          	<a href="#" url="user.jsp"><h2>公众号模板绑定管理</h2></a><br/>
	          	<a href="#" url="user.jsp"><h2>信息内容管理</h2></a><br/>
	          	<a href="#" url="user.jsp"><h2>信息内容审核管理</h2></a><br/>
	          	<a href="#" url="user.jsp"><h2>公众号粉丝管理</h2></a><br/>
          	</ul>
	    </div>   
	    <div data-options="region:'center'">
	    	<div id="tt" class="easyui-tabs" fit="true" border="false">   
			    <div title="欢迎页" style="text-align:center;font-size: 20px;">   
			         	欢迎使用XXX系统后台管理界面！ 
			    </div>
			</div> 
	    </div>   
	</div>
</body>
</html>