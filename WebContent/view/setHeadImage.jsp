<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYpE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="IE=11.0000" http-equiv="X-Ua-Compatible">
		<meta name="GENERaTOR" content="MSHTML 11.00.9600.17496">
		<title>设置头像</title> 
		<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/css/bootstrap.min.css">
		<script type="text/javascript" src="${basePath}js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}bootstrap/js/bootstrap.min.js"></script>
		<style type="text/css">
			.panel{
				width: 400px;
				margin: auto;
				height: 130px;
				margin-top: 100px;
			}
		</style>
		<script type="text/javascript">
		$(function(){
			$('#btn-choiseFile,#headImageShow').click(function(){
				$("#headImage").click();
			});
			$('#headImage').change(function(){
				$('#headImageShow').val($(this).val());
			});
		});
		</script>
	</head>
	<body>
		<div class="panel panel-primary">
		  	<div class="panel-body">
		    	<form action="${basePath}setHeadImage.do" method="post" enctype="multipart/form-data">
		    		<div class="form-group">
		    			<div class="input-group">
			    			<input type="text" id="headImageShow" class="form-control" placeholder="支持GIF、JPG、PNG、JPEG格式图片" readonly />
						  	<span class="input-group-btn">
        						<button class="btn btn-success" id="btn-choiseFile" type="button">
        							<span class="glyphicon glyphicon-send"></span>&nbsp;上传
        						</button>
      						</span>
						</div>
		    			<input type="file" name="headImage" id="headImage" style="display: none;" />
		    		</div>
		    		<div class="form-group text-center">
		    			<button class="btn btn-danger" id="btn-upload" type="submit">
    						<span class="glyphicon glyphicon-send"></span>&nbsp;确认上传
    					</button>
		    		</div>
		    	</form>
		  	</div>
		</div>
	</body>
</HTML>