<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>



<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<title>Employee Attendance</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
</head>

<body>
<div id="header">
<img class="inline-block" alt="" src="/images/logodiligent.png" height="100px">
<span class="header">Employee Attendance</span>
<div>
<%@ include file="menu.jsp"%>
</div>
</div>

