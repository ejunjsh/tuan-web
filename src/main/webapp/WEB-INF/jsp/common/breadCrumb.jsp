<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<ol class="breadcrumb">
  <li><a href="<%=request.getContextPath()%>/">首页</a></li>
  <c:if test="${curTag==null&&curSearchKey==null}">
  <li class="active">${curCity}</li>
  </c:if>
  <c:if test="${curTag!=null||curSearchKey!=null}">
  <li><a href="<%=request.getContextPath()%>/">${curCity}</a></li>
  </c:if>
  <c:if test="${curTag!=null&&curSearchKey==null}">
  <li class="active">${curTag}</li>
  </c:if>
  <c:if test="${curTag!=null&&curSearchKey!=null}">
  <li><a href="<%=request.getContextPath()%>/${curTag}">${curTag}</a></li>
  </c:if>
  <c:if test="${curSearchKey!=null}">
  <li class="active">${curSearchKey}</li>
  </c:if>
</ol>