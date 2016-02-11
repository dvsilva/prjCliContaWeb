<%@page import="entity.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" type="text/css" href="css/cadastro.css"
	media="screen">
	
<title>Banco Pilares - Cadastrar Cliente</title>

<link rel="shortcut icon" href="imagens/Icone PILARES.jpg" >

<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="js/jquery.maskedinput-1.1.4.pack.js"/></script>
<script type="text/javascript">$(document).ready(function(){	$("#cpf").mask("999.999.999-99");});</script>
<script type="text/javascript">
function toogle_disabled( bool )
{
	var input = document.getElementsByTagName('input');
	var textarea = document.getElementsByTagName('textarea');
	var select = document.getElementsByTagName('select');
	for( var i=0; i<=(input.length-1); i++ )
	{
		if( input[i].type!='submit' ) 
			input[i].disabled = bool;
	}
	for( var i=0; i<=(textarea.length-1); i++ )
	{
		textareat[i].disabled = bool;
	}
	for( var i=0; i<=(select.length-1); i++ )
	{
		select[i].disabled = bool;
	}
}
</script>

</head>

		<%@ page import="java.util.*, service.*, dto.*, exception.*"%>
		<%
		ClienteDTO cliente = new ClienteDTO();
		
		try{
		
		ContaService contaService = new ContaService();
		ClienteService clienteService = new ClienteService(contaService);

		String cpf = request.getParameter("cpf");
		cpf = cpf.replace(".", "").trim();
		cpf = cpf.replace("-", "").trim();
		long cpfl = Long.parseLong(cpf);

		try {
			
			cliente = clienteService.buscarCliente(cpfl);
			%>
			<body class="body" OnLoad = "toogle_visible(false)">
			
			<form method="post" action="Redimensionar.do" class="campos">
	
			<%-- <% long cpf = Long.parseLong(request.getParameter("cpf"));%> 
			value =<%out.print(cpf);%> --%>
		
			CPF: <input type="text" name="cpf" id= "cpf" value=<%=cliente.getCpf()%> /><br />  <br />  
			Nome: <input type="text" name="nome" value= "<%=cliente.getNome()%>" /><br /><br />    
			Sobrenome: <input type="text" name="sobrenome" value= "<%=cliente.getSobrenome()%>" /><br /> <br />  
			 
			Sexo: <select name="sexo"> 
			 <% Sexo[] sexos = Sexo.values();
			 for (int i =0; i < sexos.length; i++){
					if (cliente.getSexo().toString().equalsIgnoreCase(sexos[i].name())){
					%>
					<option SELECTED><%=sexos[i].name()%></option>
					<%
				}
				else{
			%> 
			<option><%=sexos[i].name()%></option>  
			<%}}%>
   	 	</select><br /><br />   
   	 	
		Estado Civil: <select name="estadoCivil"> 
        <% 
        EstadoCivil[] estados = EstadoCivil.values();
		for (int i =0; i < estados.length; i++){
			if (cliente.getEstadoCivil().toString().equalsIgnoreCase(estados[i].name())){
				%>
				<option SELECTED><%=estados[i].name()%></option>
				<%
			}
			else{
		%> 
		<option><%=estados[i].name()%></option>  
		<%}}%>
   	 	</select><br /><br />  
   	 	
		Cep: <input type="text" value= "<%=cliente.getCep()%>" name="cep" /><br /><br />  
		Endereço: <input type="text" value= "<%=cliente.getEndereco()%>" name="endereco" /><br /><br />
		<br /><br /> <br /><br /> <br /><br /> <br />
		
		<input type="submit" name="operacao" value="Voltar" class="botoes"/>
		<input type="submit" name="operacao" value="Salvar" class="botoes"/>

		<input type="submit" name="operacao" value="Vincular Conta poupança" class="botoes"/>
		<input type="submit" name="operacao" value="Vincular Conta corrente" class="botoes"/>
				
		</form>
			<%
		}
		catch (DaoException d){
			%>
			<body class="body">
			
			<form method="post" action="Redimensionar.do" class="campos">
				
				CPF: <input type="text" name="cpf" value="${param.cpf}"/><br />  <br />  
				Nome: <input type="text" name="nome" /><br /><br />    
				Sobrenome: <input type="text" name="sobrenome" /><br /> <br />  
				 
				Sexo: <select name="sexo"> 
				 <% Sexo[] sexos = Sexo.values();
				 for (int i =0; i < sexos.length; i++){
						
							%>
					<option><%=sexos[i].name()%></option>  
					<%}%>
		   	 	</select><br /><br />   
		   	 	
				Estado Civil: <select name="estadoCivil"> 
		        <% 
		        EstadoCivil[] estados = EstadoCivil.values();
				for (int i =0; i < estados.length; i++){
						%>
				<option><%=estados[i].name()%></option>  
				<%}%>
		   	 	</select><br /><br />  
		   	 	
				Cep: <input type="text" name="cep" /><br /><br />  
				Endereço: <input type="text" name="endereco" /><br /><br />
				<br /><br /> <br /><br /> <br /><br /> <br />
				
				<input type="submit" name="operacao" value="Voltar" class="botoes"/>
				<input type="submit" name="operacao" value="Salvar" class="botoes"/>
			</form>
			<% 
		}
		}
		catch (NumberFormatException n) {
			%>
			<body class="body">
			
			<form method="post" action="Redimensionar.do" class="campos">
				
				CPF: <input type="text" name="cpf" value="${param.cpf}"/><br />  <br />  
				Nome: <input type="text" name="nome" /><br /><br />    
				Sobrenome: <input type="text" name="sobrenome" /><br /> <br />  
				 
				Sexo: <select name="sexo"> 
				 <% Sexo[] sexos = Sexo.values();
				 for (int i =0; i < sexos.length; i++){
						
							%>
					<option><%=sexos[i].name()%></option>  
					<%}%>
		   	 	</select><br /><br />   
		   	 	
				Estado Civil: <select name="estadoCivil"> 
		        <% 
		        EstadoCivil[] estados = EstadoCivil.values();
				for (int i =0; i < estados.length; i++){
						%>
				<option><%=estados[i].name()%></option>  
				<%}%>
		   	 	</select><br /><br />  
		   	 	
				Cep: <input type="text" name="cep" /><br /><br />  
				Endereço: <input type="text" name="endereco" /><br /><br />
				<br /><br /> <br /><br /> <br /><br /> <br />
				
				<input type="submit" name="operacao" value="Voltar" class="botoes"/>
				<input type="submit" name="operacao" value="Salvar" class="botoes"/>
				
			</form>
			<%
		}
		finally{}
		
		%>
	

	</body>
</html>