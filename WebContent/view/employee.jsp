<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>员工列表</title>
		<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/css/bootstrap.min.css">
		<script type="text/javascript" src="${basePath}js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${basePath}js/date.js"></script>
		<link type="text/css" href="${basePath}icheck/skins/all.css?v=1.0.2" rel="stylesheet">
		<script src="${basePath}icheck/icheck.min.js?v=1.0.2"></script>
		<link type="text/css" href="${basePath}bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
		<script src="${basePath}bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
		<script src="${basePath}bootstrap/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<style type="text/css">
			#searchFF .form-control{width: 120px!important;}
			#searchFF .form-group{margin-bottom: 10px!important;}
			#dg tr:hover{cursor: pointer;}
		/*
		.in{width: 300px;height: 24px; border:1px solid #95b8e7;border-radius:3px;}
		.searchIn{width: 120px;height: 24px; border:1px solid #95b8e7;border-radius:3px;}
		#ff table{width: 500px;margin-left: 50px;}
		#ff tr{height: 40px;}*/
		</style>
		<script type="text/javascript">
		$(function(){
			loadData();
			//搜索按钮点击事件
			$('#btn-search').click(function(){
				$("#pageNo").val("1");
				loadData();
			});
			//查看全部员工点击事件
			$('#btn-searchAll').click(function(){
				$('#searchFF')[0].reset();
				$('#pageNo').val('1');
				$('#pageSize').val('10');
				loadData();
			});
			//初始化icheck插件
			$("input[name2=gender],input[name2=searchGender]").iCheck({
			    //checkboxClass: 'icheckbox_square',
			    radioClass: 'iradio_flat-blue',
			    increaseArea: '20%' // optional
			});
			//初始化入职日期选择框
			$('#searchFF .form_date').datetimepicker({
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
			$('#tr_entryDate .form_date').datetimepicker({
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0,
				pickerPosition: 'top-right'//显示在右上角  默认右下角
		    });
			//全选和取消全选点击事件
			$('#checkAll').on("ifChecked",function(){
				$('input[name=empids]').iCheck('toggle');//.iCheck('check');
			}).on("ifUnchecked",function(){
				$('input[name=empids]').iCheck('toggle');//.iCheck('uncheck');
			});
			//tbody里面的tr添加点击事件 点击某一行的任意位置都触发前面多选框的选中和取消
			$("#dg").on("click", "tr", function(){
				$(this).find('input[name=empids]').iCheck('toggle');
			});
			//新增按钮点击事件，打开表单窗口
			$("#btn-toAdd").click(function(){
				$('#tr_entryDate').hide();
				$('#ff')[0].reset();		//清除表单内的所有已填数据
				$('#window').modal({backdrop:'static'}).modal('show');  	// open a window 
				$('input[name2=gender]').iCheck({radioClass: 'iradio_flat-blue'});
			});
			//编辑按钮点击事件  打开表单窗口并且回填数据
			$('#btn-toEdit').click(function(){
				var empidArray = $('input[name=empids]:checked');
				if(empidArray.length == 0){
					alert("请选择要修改的员工信息！");
					return;
				}
				if(empidArray.length > 1){
					alert("请选中一行进行修改！");
					return;
				}
				var empid = empidArray.eq(0).val();
				//发送请求查询员工表的数据
				$.getJSON("${basePath}employee/loadEmployeeById.do?empid="+empid,function(data){
					$('#window').modal({backdrop:'static'}).modal('show');
					$('#tr_entryDate').show();
					//表单数据回填
					$('#empid').val(data.empid);
					$('#empName').val(data.empName);
					$('#age').val(data.age);
					$('#phone').val(data.phone);
					$('#email').val(data.email);
					$('#deptid').val(data.deptid);
					if(data.gender == 1)
						$('input[name2=gender]').eq(0).iCheck("check");
					else
						$('input[name2=gender]').eq(1).iCheck("check");
					$('#entryDate,#entryDateShow').val(dateToString(data.entryDate, false));
				});
			});
			//异步加载部门列表填入部门搜索框和新增、编辑员工的部门下拉列表中
			$.getJSON("${basePath}employee/loadAllDept.do",function(data){
				for(var i=0;i<data.length;i++){
					$("#searchDeptid").append("<option value='"+data[i].deptid+"'>"+data[i].deptName+"</option>");
					$("#deptid").append("<option value='"+data[i].deptid+"'>"+data[i].deptName+"</option>");
				}
			});
			//添加员工点击事件
			$("#btn-add").click(function(){
				var param = $('#ff').serialize();//返回字符串
				$.post("${basePath}employee/saveOrUpdateEmployee.do",param,function(data){
					if(data.result=="addok"){
						alert('员工添加成功！'); 
						$('#window').modal('hide');  // close a window 
						$('#pageNo').val('1');
						loadData();
					}else if(data.result=="editok"){
						alert('员工修改成功！'); 
						$('#window').modal('hide');  // close a window 
						loadData();
					}
				},"json");
			});
			
			/*
			//删除按钮点击事件 支持删除多行数据
			$('#btn-delete').click(function(){
				var selected = $('#dg').datagrid("getSelections");
				if(selected.length == 0){
					$.messager.alert("错误","请选择要删除的员工信息！","error");
					return;
				}
				var ids = new Array();
				for(var i=0;i<selected.length;i++){
					ids.push(selected[i].empid);
				}
				$.post("${basePath}employee!deleteEmployee.action",{ids:ids.join(",")},function(data){
					if(data.result == "deleteOK"){
						$.messager.alert("消息","删除员工成功！","info", function(){
							loadData();
						});
					}
				},"json");
				
			});
			*/
		});
		
		/**
		 * 加载数据
		 */
		function loadData(){
			$.post("${basePath}employee/employeeList.do",$('#searchFF').serialize(),function(data){
				if(data.error){
					alert(data.error);
					return;
				}
				$("#dg").empty();
				for(var i=0;i<data.rows.length;i++){
					$("#dg").append('<tr>'+
							'<td><input type="checkbox" name="empids" value="'+data.rows[i].empid+'" /></td>'+
							'<td>'+data.rows[i].empid+'</td>'+
							'<td>'+data.rows[i].empName+'</td>'+
							'<td>'+data.rows[i].age+'</td>'+
							'<td>'+(data.rows[i].gender==1?'男':'女')+'</td>'+
							'<td>'+data.rows[i].phone+'</td>'+
							'<td>'+data.rows[i].email+'</td>'+
							'<td>'+data.rows[i].deptName+'</td>'+
							'<td>'+dateToString(data.rows[i].entryDate, false)+'</td>'+
							'</tr>');
				}
				$("#pageCount").val(data.pageCount);
				//初始化icheck插件
				$("input[name=empids],#checkAll").iCheck({
				    checkboxClass: 'icheckbox_flat-blue',
				    //radioClass: 'iradio_flat-blue',
				    increaseArea: '20%' // optional
				});
				
				if(data.pageNo == 1){
					$("#pagination>li:first").addClass("disabled");
					$("#pagination>li:first").find("a").attr("href", "javascript:void(0);");
				}else{
					$("#pagination>li:first").removeClass("disabled");
					$("#pagination>li:first").find("a").attr("href", "javascript:changePage(-1);");
				}
				if(data.pageNo == data.pageCount){
					$("#pagination>li:last").addClass("disabled");
					$("#pagination>li:last").find("a").attr("href", "javascript:void(0);");
				}else{
					$("#pagination>li:last").removeClass("disabled");
					$("#pagination>li:last").find("a").attr("href", "javascript:changePage(-2);");
				}
				$('#pagination>li').each(function(index){
					var len = $('#pagination>li').length;
					if(index >= 1 && index <= len-2){
						if($(this).find("a").text() > data.pageCount){
							$(this).addClass("disabled");
							$(this).find("a").attr("href", "javascript:void(0);");
						}else{
							$(this).removeClass("disabled");
							$(this).find("a").attr("href", "javascript:changePage("+index+");");
						}
					}
				});
			},"json");
		}
		
		/**
		 * 翻页
		 */
		function changePage(to){
			if(to > 0){
				$("#pageNo").val(to);
			}else if(to == -1){
				//上一页
				$("#pageNo").val($("#pageNo").val()-1);
			}else if(to == -2){
				//下一页
				var next = parseInt($("#pageNo").val())+1;
				$("#pageNo").val(next);
			}
			loadData();
		}
		</script>
	</head>
	<body>
		<input type="hidden" id="pageCount" />
		<table class="table table-striped table-hover table-condensed">
			<caption>
				<form id="searchFF" class="form-inline">
					<input type="hidden" name="pageNo" id="pageNo" value="1" />
					<input type="hidden" name="pageSize" id="pageSize" value="10" />
					<div class="form-group">
					    <input type="text" class="form-control" name="searchEmpName" id="searchEmpName" placeholder="按姓名搜索" />
  					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="searchPhone" id="searchPhone" placeholder="按电话搜索" />
					</div>
					<div class="form-group">
		   				<input type="text" name="searchAge1" id="searchAge1" placeholder="按年龄范围搜索" class="form-control"/>至
		   				<input type="text" name="searchAge2" id="searchAge2" placeholder="按年龄范围搜索" class="form-control"/>
	   				</div>
	   				<div class="form-group">
						<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="searchEntryDate1">
		                    <input class="form-control" size="16" type="text" value="" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>
						<input type="hidden" name="searchEntryDate1" id="searchEntryDate1" value="" />
						至
						<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="searchEntryDate2">
		                    <input class="form-control" size="16" type="text" value="" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>
						<input type="hidden" name="searchEntryDate2" id="searchEntryDate2" value="" />
					</div>
					<div class="form-group">
						<select id="searchDeptid" name="searchDeptid" class="form-control"></select>
					</div>
					<div class="form-group">
						<input type="radio" name2="searchGender" name="searchGender" value="1" /> 男
						<input type="radio" name2="searchGender" name="searchGender" value="2" /> 女
					</div>
				</form>
				<!-- data-toggle="modal" href="#window" data-backdrop="static" -->
				<a id="btn-toAdd" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>&nbsp;新增</a>  
				<a id="btn-delete" href="#" class="btn btn-success"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</a>  
				<a id="btn-toEdit" href="#" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span>&nbsp;编辑</a>  
				<a id="btn-search" href="#" class="btn btn-warning"><span class="glyphicon glyphicon-search"></span>&nbsp;搜索</a>  
				<a id="btn-searchAll" href="#" class="btn btn-danger"><span class="glyphicon glyphicon-th"></span>&nbsp;查看全部</a>  
			</caption>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll" /></th>
					<th>编号</th>
					<th>姓名</th>
					<th>年龄</th>
					<th>性别</th>
					<th>电话</th>
					<th>邮箱</th>
					<th>部门</th>
					<th>入职日期</th>
				</tr>
			</thead>
			<tbody id="dg"></tbody>
		</table>
		<nav aria-label="Page navigation" class="text-center" style="margin-top: -30px;">
			<ul class="pagination" id="pagination">
				<li id=""><a href="javascript:changePage(-1);"><span aria-hidden="true">&laquo;</span></a></li>
				<li><a href="javascript:changePage(1);">1</a></li>
				<li><a href="javascript:changePage(2);">2</a></li>
				<li><a href="javascript:changePage(3);">3</a></li>
				<li><a href="javascript:changePage(4);">4</a></li>
				<li><a href="javascript:changePage(5);">5</a></li>
				<li id=""><a href="javascript:changePage(-2);"><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
		</nav>
		<!-- 新增/编辑窗口 -->
		<div class="modal fade" id="window">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-user"></span> 新增/编辑员工信息</h4>
					</div>
					<div class="modal-body">
						<form id="ff">
							<!-- 当此输入框内empid有值时，表示修改员工，没有值时表示新增员工 -->
							<input type="hidden" name="empid" id="empid" />
							<div class="form-group">
								<input type="text" class="form-control" name="empName" id="empName" placeholder="员工姓名" />
							</div>
							<div class="form-group">
								<input type="text" id="age" name="age" class="form-control" placeholder="年龄" />
							</div>
							<div class="form-group">
								<input type="text" id="phone" name="phone" class="form-control" placeholder="电话" />
							</div>
							<div class="form-group">
								<input type="text" id="email" name="email" class="form-control" placeholder="邮箱" />
							</div>
							<div class="form-group">
								<select id="deptid" name="deptid" class="form-control"></select>
							</div>
							<div class="form-group" id="tr_entryDate" style="display: none;">
								<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="entryDate">
				                    <input class="form-control" size="16" type="text" id="entryDateShow" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
				                </div>
								<input type="hidden" name="entryDate" id="entryDate" value="" />
							</div>
							<div class="form-group">
								<input type="radio" name2="gender" name="gender" value="1" /> 男
								<input type="radio" name2="gender" name="gender" value="2" /> 女
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="btn-add">确认</button>
					</div>
				</div>
			</div>
		</div>

	<!-- <div id="win" class="easyui-window" title="新增/编辑员工" style="width:600px;height:400px;top:50px;padding: 20px;"   
		        data-options="iconCls:'icon-add2',modal:true,collapsible:false,minimizable:false,maximizable:false">   
		    <form id="ff">
		    	当此输入框内empid有值时，表示修改员工，没有值时表示新增员工
		    	<input type="hidden" name="ev.empid" id="empid" />
		    	<table>
		    		<tr>
		    			<td><label>姓名：</label></td>
		    			<td><input type="text" id="empName" name="ev.empName" class="easyui-validatebox in" data-options="required:true,validType:'chineseName[2,5]'" /></td>
		    		</tr>
		    		<tr>
		    			<td><label>年龄：</label></td>
		    			<td><input type="text" id="age" name="ev.age" class="easyui-validatebox in" data-options="validType:'age'" /></td>
		    		</tr>
		    		<tr>
		    			<td><label>手机：</label></td>
		    			<td><input type="text" id="phone" name="ev.phone" class="easyui-validatebox in" data-options="required:true,validType:'mobile'" /></td>
		    		</tr>
		    		<tr>
		    			<td><label>邮箱：</label></td>
		    			<td><input type="text" id="email" name="ev.email" class="easyui-validatebox in" data-options="validType:'email'" /></td>
		    		</tr>
		    		<tr>
		    			<td><label>部门：</label></td>
		    			<td>
		    				<select id="deptid" name="ev.deptid" class="in"></select>
		    			</td>
		    		</tr>
		    		<tr id="tr_entryDate" style="display: none;">
		    			<td><label>入职：</label></td>
		    			<td>
		    				<input type="text" name="ev.entryDate" id="entryDate" class="easyui-datebox in" style="height:30px;" data-options="editable:false" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td><label>性别：</label></td>
		    			<td>
		    				<input type="radio" name="ev.gender" value="1" />男
		    				<input type="radio" name="ev.gender" value="2" />女
		    			</td>
		    		</tr>
		    		<tr>
		    			<td colspan="2" style="text-align:center;">
		    				<a id="btn-add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add2'">确认</a>  
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		</div>  --> 
	</body>
</html>