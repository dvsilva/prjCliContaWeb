package web;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ClienteService;
import service.ContaService;
import dto.ClienteDTO;
import dto.ContaDTO;
import entity.EstadoCivil;
import entity.Sexo;
import entity.TipoConta;
import exception.DaoException;
import exception.ServiceException;

/**
 * Servlet implementation class Redimensionar
 */
public class Redimensionar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ContaService contaService = new ContaService();
	private static ClienteService clienteService = new ClienteService(contaService);
	private int codContas = 4;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Redimensionar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)	
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacao = request.getParameter("operacao");
		
		String cpf = request.getParameter("cpf");
		cpf = cpf.replace(".", "").trim();
		cpf = cpf.replace("-", "").trim();
		
		if (operacao.equals("Salvar")) {
			
			Sexo sexo = null;
			
			if (request.getParameter("sexo").equalsIgnoreCase("Masculino")) {
				sexo = Sexo.MASCULINO;
			} else {
				sexo = Sexo.FEMININO;
			}

			EstadoCivil estadoCivil = null;
			if (request.getParameter("estadoCivil").equalsIgnoreCase("Solteiro")) {
				estadoCivil = EstadoCivil.SOLTEIRO;
			} else {
				if (request.getParameter("estadoCivil").equalsIgnoreCase("Casado")) {
					estadoCivil = EstadoCivil.CASADO;
				} else {
					if (request.getParameter("estadoCivil").equalsIgnoreCase("Divorciado")) {
						estadoCivil = EstadoCivil.DIVORCIADO;
					} else {
						estadoCivil = EstadoCivil.OUTROS;
					}
				}
			}
			
			ClienteDTO clid = null;
			try {
				clid = clienteService.buscarCliente(Long.parseLong(cpf));
			}
			 catch (DaoException e1) {

					ClienteDTO clienteDTO = new ClienteDTO(Long.parseLong(cpf), request.getParameter("nome"),
							request.getParameter("sobrenome"), sexo, estadoCivil,
							 request.getParameter("cep"), request.getParameter("endereco"));
					
					try {
							clienteService.cadastrarCliente(clienteDTO);
						} catch (ServiceException | DaoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
			
			if(clid != null){
				clid.setNome(request.getParameter("nome"));
				clid.setSobrenome(request.getParameter("sobrenome"));
				clid.setEstadoCivil(estadoCivil);
				clid.setSexo(sexo);
				clid.setEndereco(request.getParameter("endereco"));
				clid.setCep(request.getParameter("cep"));
				
				try {
					
					clienteService.alterarCliente(clid);
					clienteService.adicionarContaCliente(clid.getCpf());
					
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("./principal.jsp");
			dispatcher.forward(request, response);
		}
	
	
		if (operacao.equals("Voltar")) {	
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("./principal.jsp");
			dispatcher.forward(request, response);
		}
	
		if (operacao.equals("Vincular Conta poupança")) {
						
			String agencia = new String("AgPilares");
			int codigo = new Random().nextInt(10) + codContas++;	
			
			criarConta(codigo, agencia, 0L, TipoConta.POUPANCA,Long.parseLong(cpf));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("./principal.jsp");
			dispatcher.forward(request, response);
			
		}
		if (operacao.equals("Vincular Conta corrente")) {
			
			String agencia = new String("AgPilares");
			int codigo = new Random().nextInt(10) + codContas++;	
			
			criarConta(codigo, agencia, 0L, TipoConta.CORRENTE,Long.parseLong(cpf));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("./principal.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private static void criarConta(int codigo, String agencia, float saldo,
			TipoConta tipo, long cpf) {
		ContaDTO contaDTO = new ContaDTO(codigo, agencia, saldo, tipo, cpf);
		try {
			contaService.cadastrarConta(contaDTO);
		} catch (ServiceException e) {
			//System.out.println(e.getTipo());
		} catch (DaoException e) {
			//System.out.println(e.getMessage());
		}
	
		Collection<ClienteDTO> clientesCadastrados = null;
		try {
			clientesCadastrados = (Collection<ClienteDTO>) clienteService.buscarclientesDTO();
		} catch (DaoException e) {
			//e.printStackTrace();
		}
		for (ClienteDTO clienteDTO : clientesCadastrados) {
			if (contaDTO.getClienteCpf() == clienteDTO.getCpf())
				clienteDTO.adicionarContaDTO(codigo);
		}
		clienteService.adicionarContaCliente(cpf);
	}


}
