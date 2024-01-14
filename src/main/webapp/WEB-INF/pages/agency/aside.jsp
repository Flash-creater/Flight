<%--
   旅行社的操作功能：
       1.查看旅客信息
       2.购票
       3.基本信息查看
       4.账户管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/img2.png"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>${user.agencyName}</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/agencyController/main "><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>旅客信息</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>


			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/agencyController/findAllTraveller?id=${user.id}"> <i
							class="fa fa-circle-o"></i> 旅客列表
					</a></li>

				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
				<span>信息管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/agencyController/agency-info">
						<i class="fa fa-circle-o"></i> 基本信息
					</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>航班信息</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/agencyController/findAllFlight">
							<i class="fa fa-circle-o"></i> 航班列表
					</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
				<span>订单管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/agencyController/findFOrder?aid=${user.id}">
						<i class="fa fa-circle-o"></i> 航班订单列表
					</a></li>
				</ul></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>