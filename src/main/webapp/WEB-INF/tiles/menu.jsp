<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Carousel</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="<c:url value="/"/>">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/products"/>">Products</a>
          </li>
          <c:if test="${pageContext.request.userPrincipal.name != null}">
          	<c:if test="${pageContext.request.userPrincipal.name == 'admin'}">
          		<li class="nav-item">
            		<a class="nav-link" href="<c:url value="/admin"/>">Admin</a>
          		</li>
          	</c:if>

          	<c:if test="${pageContext.request.userPrincipal.name != 'admin'}">
          		<li class="nav-item">
            		<a class="nav-link" href="<c:url value="/cart"/>">Cart</a>
          		</li>
          	</c:if>
          	
          	<li class="nav-item">
				<a class="nav-link" href="javascript:document.getElementById('logout').submit()">Logout</a>
			</li>
					
			<form id="logout" action="<c:url value="/logout" />"method="post">
				<input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
			</form>
          	<%--
          	<li class="nav-item">
          		<a href="javascropt:document.getElementById('logout').submit()" lang="en">Logout</a>
          	</li>
          	<form id="logout" action="<c:url value="/logout"/>" method="post">
          		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          	</form>
          	 --%>
          	
          </c:if>
          
          <c:if test="${pageContext.request.userPrincipal.name == null}">
          	<li class="nav-item">
          		<a class="nav-link" href="<c:url value="/login"/>">Login</a>
          	</li>
          	<li class="nav-item">
          		<a class="nav-link" href="<c:url value="/register"/>">Register</a>
          	</li>
          </c:if>
        </ul>
        <form class="d-flex">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
      </div>
    </div>
  </nav>
</header>