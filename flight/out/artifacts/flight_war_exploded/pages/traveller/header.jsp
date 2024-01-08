<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" errorPage="404.jsp"%>

<!-- 页面头部 -->
<header class="main-header">
	<!-- Logo -->
	<a href="all-admin-index.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>旅客</b></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>旅客</b>管理</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="${pageContext.request.contextPath}/img/img2.png"
						class="user-image" alt="User Image"> <span class="hidden-xs">
					${user.userName}
					</span>

				</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header"><img
							src="${pageContext.request.contextPath}/img/img2.png"
							class="img-circle" alt="User Image"></li>

						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="${pageContext.request.contextPath}/pages/traveller/password.jsp" class="btn btn-default btn-flat">修改密码</a>
							</div>
							<div class="pull-right">
								<a href="${pageContext.request.contextPath}/traveller/logout"
									class="btn btn-default btn-flat">注销</a>
							</div>
						</li>
					</ul></li>

			</ul>
		</div>
	</nav>
</header>
<!-- 页面头部 /-->