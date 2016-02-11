<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" type="text/css" href="css/principal.css" media="screen">

<title>Banco Pilares - Principal</title>

<script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="js/jquery.maskedinput-1.1.4.pack.js"/></script>
<script type="text/javascript">$(document).ready(function(){	$("#cpf").mask("999.999.999-99");});</script>
<link rel="shortcut icon" href="imagens/Icone PILARES.jpg" >

</head>

<body class="body">

	<form method="post" action="cadastro.jsp" class="ok">
		CPF: <input type="text" name="cpf" id= "cpf"/><br /> <br /> 
		<input type="submit" value="OK" class="botoes" />
	</form>
	<div class="div"> 
	<table border="1" class="tabela">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Sobrenome</th>
				<th>Sexo</th>
				<th>CPF</th>
				<th>Estado Civil</th>
				<th>Endereço</th>
				<th>Conta</th>
				<th>Saldo</th>
			<tr>

				<%@ page import="java.util.*, service.*, dto.*"%>
				<%
					ContaService contaService = new ContaService();
					ClienteService clienteService = new ClienteService(contaService);
					
					Collection<ClienteDTO> clientes = (Collection<ClienteDTO>) clienteService.buscarclientesDTO();

					for (ClienteDTO cliente : clientes) {
						
						if (cliente.getCodContas().size() <= 0){
						%>
						
				<tr>
				<td><%=cliente.getNome()%></td>
				<td><%=cliente.getSobrenome()%></td>
				<td><%=cliente.getSexo()%></td>
				<td><%=cliente.getCpf()%></td>
				<td><%=cliente.getEstadoCivil()%></td>
				<td><%=cliente.getEndereco()  + " | " + cliente.getCep() %></td>
				<td></td><td></td>
			<tr>
				<%
						}
						else {
						for (int conta : cliente.getCodContas()) {

							ContaDTO cont = contaService.buscarConta(conta);
				%>
			
			<tr>
				<td><%=cliente.getNome()%></td>
				<td><%=cliente.getSobrenome()%></td>
				<td><%=cliente.getSexo()%></td>
				<td><%=cliente.getCpf()%></td>
				<td><%=cliente.getEstadoCivil()%></td>
				<td><%=cliente.getEndereco() + " | " + cliente.getCep()%></td>
				<td><%=cont.getCodigo()%></td>
				<td><%=cont.getSaldo()%></td>

			<tr>
				<%
						}
					}
					}
				%>
			
	</table>
	</div>

	<form method="post" action="Principal.do" class="listarCadastros">
		<input type="submit" value="Listar cadastros" class="botoes"/>
	</form>
	
	<form method="post" action="transacoes.jsp" class="transacoes">
		<input type="submit" value="Transações" class="botoes"/>
	</form>

</body>
</html>