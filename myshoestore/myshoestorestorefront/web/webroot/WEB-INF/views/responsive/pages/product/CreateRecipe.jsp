<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.lang.*" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<script type="text/javascript">
function validate(){
    x=document.myForm
    input=x.myInput.value
    if (input.length>5){
        alert("The field cannot contain more than 5 characters!")
        return false
    }else {
        return true
    }
}
</script>
<template:page pageTitle="${pageTitle}">
<c:url value = "/create-recipe"/>
<h1>Recipe Creation Form</h1>
<form:form action="create-recipe" method="post" modelAttribute="recipeForm">
    <formElement:formInputBox idKey="recipe.code" labelKey="Recipe code" path="code" inputCSS="text" mandatory="true"/>
    <formElement:formInputBox idKey="recipe.title" labelKey="Recipe Title" path="title" inputCSS="text" mandatory="true"/>
    <formElement:formInputBox idKey="recipe.description" labelKey="Recipe Description" path="description" inputCSS="text" mandatory="true"/>
    <div class="row">
        <div class="col-sm-6 col-sm-push-6">
            <div class="accountActions">
                <button type="submit" class="btn btn-primary btn-block" onsubmit="return validate()">
                    <spring:theme code="Create" text="Create"/>
                </button>
            </div>
        </div>
        <div class="col-sm-6 col-sm-pull-6">
            <div class="accountActions">
                <a href="https://localhost:9002/myShoeStore/en/recipe/recipeDetails"
            	    <button type="button" method="get" class="btn btn-default btn-block backToHome">
                        <spring:theme code="Cancel" text="Cancel"/>
                    </button>
                </a>
            </div>
        </div>
    </div>
</form:form>
</template:page>