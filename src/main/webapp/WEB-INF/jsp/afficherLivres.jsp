<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://displaytag.sf.net"  prefix="d"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/maven-theme.css" />
<title>Titre à définir</title>
</head>
<body>
<center>
<jsp:include page="./header.jsp" />
<hr />
<jsp:include page="./menu.jsp" />
<hr />

<!--  contenu spécifique .... !  -->
<h3>Liste des Livres</h3>

<d:table name="livres" id="livre">
<d:column title="N°">
	<a href="livre-editer?id=${livre.id }" >${livre.id }</a>
</d:column>
<d:column title="Titre" property="titre"/>
<d:column title="Auteur" property="auteur"/>
<d:column title="Date" property="parution"/>
</d:table>

<form action="livre-creer.action">
<table width="500" >
<tr><td align="right"><input type="submit"  value="Créer un nouveau livre"/></td></tr>
</table>
<input type="hidden"  name="id" value="0"/>
</form>



</center>
</body>
</html>