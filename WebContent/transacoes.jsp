<%@page import="entity.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" type="text/css" href="css/transacoes.css"
	media="screen">
	
<title>Banco Pilares - Depósito e transações</title>
<link rel="shortcut icon" href="imagens/Icone PILARES.jpg" >
</head>

<body class="body">
			
	<form method="post" action="Transacoes.do" class="deposito">		
		Valor: R$ <input type="text" name="valor" id= "valor"/>
		Conta a creditar: <input type="text" name="conta" id= "conta"/><br /> <br /> 
		<input type="submit" name="operacao" value="Depositar" class="botoes" />
	</form>
	
	<form method="post" action="Transacoes.do" class="transacao">	
		Conta a creditar: <input type="text" name="contac" id= "contac"/>
		Conta a debitar: <input type="text" name="contad" id= "contad"/><br /> <br /> 
		Valor: R$ <input type="text" name="valor" id= "valor"/><br /> <br /> 
		<input type="submit" name="operacao" value="Efetuar transação" class="botoes"/>
	</form>
	
	<form method="post" action="Principal.do" class="voltar">
		<input type="submit" name="operacao" value="Voltar" class="botoes"/>
	</form>

</body>

</html>