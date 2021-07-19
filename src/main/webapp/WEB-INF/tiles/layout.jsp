<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, Bootstrap">
    <meta name="generator" content="Hugo 0.83.1">
    
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	
    <title><tiles:insertAttribute name="title"/></title>

    <link rel="canonical" href="https://getbootstrap.kr/docs/5.0/examples/carousel/">

    
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" integrity="sha256-MSaMXoOj1lKN/BhWEgjiX0XxaLN9I8XwaATfpoDzT+8=" crossorigin="anonymous">


<!-- Favicons -->
<link rel="icon" href="<c:url value="/resources/favicon.ico"/>">

<!-- FontAwesome -->
<link href="<c:url value="/resources/fontAwesome/css/all.css"/>" rel="stylesheet"> <!--load all styles -->

<!--  AngularJS -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"> </script>

<script src="<c:url value="/resources/js/bootstrap.bundle.min.js"/>"></script>
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/carousel.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    
  </head>
  <body>

	<tiles:insertAttribute name="menu"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>
	
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="<c:url value="/resources/js/bootstrap.bundle.min.js"/>" integrity="sha256-wCw27iblW6EBiJKKa8q0H0T9+t418CA5fMbq8JkcTlc=" crossorigin="anonymous"></script>
  
  </body>
</html>
