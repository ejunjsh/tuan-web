<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    <!-- Fixed navbar -->
   

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">


<div class="container">



  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="<%=request.getContextPath()%>/">Tuan++</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li <c:if test="${curPage!='today'}">class="active"</c:if>><a href="<%=request.getContextPath()%>/">首页</a></li>
      <li <c:if test="${curPage=='today'}">class="active"</c:if>><a href="<%=request.getContextPath()%>/today">今日</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        <c:if test="${curCity!=null}">
        ${curCity} 
        </c:if>
        <c:if test="${curCity==null}">
        城市 
        </c:if>
        <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li class="dropdown-header">热门城市</li>
          <c:forEach items="${hotCity}" var="city">
          <li><a href="<%=request.getContextPath()%>/selectCity/${city.name}">${city.name}</a></li>
          </c:forEach>
          <li class="divider"></li>
          <li><a data-toggle="modal" href="<%=request.getContextPath()%>/ajax/getAllCity" data-target="#cityModal">更多城市</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        <c:if test="${curTag==null}">
        标签 
        </c:if>
        <c:if test="${curTag!=null}">
        ${curTag} 
        </c:if>
        <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li class="dropdown-header">热门标签</li>
          <c:forEach items="${hotTag}" var="tag">
          <li><a href="<%=request.getContextPath()%>/${tag.name}">${tag.name}</a></li>
          </c:forEach>
          <li class="divider"></li>
          <li><a data-toggle="modal" href="<%=request.getContextPath()%>/ajax/getAllTag" data-target="#tagModal">更多标签</a></li>
        </ul>
      </li>
    </ul>
    <form action="<%=request.getContextPath()%>/${curTag}" method="get" class="navbar-form navbar-right" role="search">
    <div class="form-group">
      <input type="text" name="key" class="form-control" placeholder="搜索">
      </div>
        <button class="btn btn-primary" type="submit">
		<span class="glyphicon glyphicon-search"></span>
		</button>
    </form>
  </div><!-- /.navbar-collapse -->
</div>
</nav>