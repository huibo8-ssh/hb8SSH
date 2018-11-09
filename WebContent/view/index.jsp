<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>BootStrap的欢迎界面</title>
		<link rel="stylesheet" type="text/css" href="${basePath}bootstrap/css/bootstrap.min.css">
		<script type="text/javascript" src="${basePath}js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}bootstrap/js/bootstrap.min.js"></script>
		<style type="text/css">
			#north{height: 60px;line-height: 60px;padding-left: 80px;padding-right: 80px;color: #FFFFFF;
			background-image: linear-gradient(to right, red, orange);}
			#north span{font-size: 30px;}
			#west{height: 568px;border-right: 1px dashed #f7f5de;background-color: #f7f5de;}
			#center{height: 568px;/*linear-gradient(to right, #f5f2c9,#f7f5de)*/}
			#accordion{padding-top: 15px;}
			#tablist .close{position: relative;top:-42px;left:-5px;color: red;}
		</style>
		<script type="text/javascript">
		function addTab(name,url,mid) {
			$('#tablist>li').removeClass("active");
			$('#tab-content>div').removeClass("active");
			//判断该选项卡是否已存在
			if($('#tabpanel'+mid).length > 0){
				$("#tab-content>div").each(function(index){
					if("tabpanel"+mid == $(this).attr("id")){
						//选中目标选项卡
						$(this).addClass("active");
						$('#tablist>li').eq(index).addClass("active");
						//刷新目标选项卡中内容
						$(this).find("iframe").attr("src", url);
					}
				});
			}else{
				$('#tablist').append('<li role="presentation" class="active">'+
										'<a href="#tabpanel'+mid+'" aria-controls="home" role="tab" data-toggle="tab">'+name+'</a>'+
										'<span class="close" title="关闭选项卡">&times;</span>'+
									'</li>');
				$('#tab-content').append('<div role="tabpanel" class="tab-pane active" id="tabpanel'+mid+'">'+
											'<iframe src="'+url+'" width="100%" height="500px" frameborder="no" scrolling="auto"></iframe>'+
										'</div>');
			}
		}
		$(function(){
			$("#tablist").on("click", ".close",function(){
				$(this).parent().remove();
				var divid = $(this).prev("a").attr("href");
				$(divid).remove();
				//选中最后一个选项卡
				$('#tablist>li:last').addClass("active");
				$('#tab-content>div:last').addClass("active");
			});
		});
		</script>
	</head>
	<body class="container-fluid">
		<div class="row" id="north">
			<div class="col-md-8">
				<span>欢迎使用XXXXXX个人管理系统</span>
			</div>
			<div class="col-md-4 text-right">
				<img class="img-circle" src="${basePath}${currentUser.headPicture}" onclick="addTab('设置头像','${basePath}view/setHeadImage.jsp','0');" style="height:50px;width:50px;cursor:pointer;" title="点击头像可设置头像" />
				<font>${currentUser.realName}</font>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row" >
			<div class="col-md-2" id="west">
				<div class="panel-group" id="accordion">
					<c:forEach items="${menus}" var="m2" varStatus="num">
						<c:if test="${m2.level==2}">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" href="#collapse${num.index+1}" data-parent="#accordion">${m2.menuName}</a>
									</h4>
								</div>
								<div id="collapse${num.index+1}" class="panel-collapse collapse">
									<div class="panel-body">
										<div class="list-group">
											<c:forEach items="${menus}" var="m3">
												<c:if test="${m3.level==3 && m3.parentid==m2.mid}">
													<a href="javascript:addTab('${m3.menuName}','${basePath}${m3.url}','${m3.mid}');" class="list-group-item">${m3.menuName}</a>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="col-md-10" id="center">
				<div>
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" id="tablist">
						<li role="presentation" class="active">
							<a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a>
						</li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content" id="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">欢迎你</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>