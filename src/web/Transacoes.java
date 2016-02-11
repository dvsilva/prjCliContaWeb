package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ContaService;
import dto.ContaDTO;
import exception.DaoException;
import exception.ServiceException;

/**
 * Servlet implementation class Transacoes
 */
public class Transacoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ContaService contaService = new ContaService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transacoes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operacao = request.getParameter("operacao");
		
		if (operacao.equals("Efetuar transação")) {
			
			int numContaDebito = Integer.parseInt(request.getParameter("contad"));
			int numContaCredito = Integer.parseInt(request.getParameter("contac"));
			float valor = Float.parseFloat(request.getParameter("valor"));
			
			try {
				ContaDTO debito = contaService.buscarConta(numContaDebito);
				debito.setSaldo(debito.getSaldo() - valor);
				contaService.alterarConta(debito);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				ContaDTO credito = contaService.buscarConta(numContaCredito);
				credito.setSaldo(credito.getSaldo() + valor);
				contaService.alterarConta(credito);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("./principal.jsp");  
			dispatcher.forward(request,response);  
			
		}
		
		if (operacao.equals("Depositar")) {
			
			int numConta = Integer.parseInt(request.getParameter("conta"));
			float valor = Float.parseFloat(request.getParameter("valor"));
			
			try {
				ContaDTO conta = contaService.buscarConta(numConta);
				conta.setSaldo(conta.getSaldo() + valor);
				contaService.alterarConta(conta);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("./principal.jsp");  
			dispatcher.forward(request,response);  
		}
		
	}
	
}
