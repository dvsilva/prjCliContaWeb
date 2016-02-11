package dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import entity.Cliente;
import entity.Conta;
import exception.DaoException;

/**
 * 
 * @author Danyllo
 * Banco de dados f�sico e real
 */
public class BancoDAO2 {
	
	private static Map<Long, Cliente> clientes;
	private static Map<Integer, Conta> contas;
	private static BancoDAO2 instance;
	private static Buscar buscar;
	private static Cadastrar cadastrar;
	private static Alterar alterar;
	private static Excluir excluir;
	private static String urlDeConexao = "jdbc:mysql://localhost:3306/bancopilares";
	private static String usuario = "root";
	private static String senha = "123";

	public BancoDAO2() {
		clientes = new HashMap<Long, Cliente>();
		contas = new HashMap<Integer, Conta>();
		
		cadastrar = new Cadastrar(urlDeConexao, usuario, senha);
		alterar = new Alterar(urlDeConexao, usuario, senha);
		excluir = new Excluir(urlDeConexao, usuario, senha);
		buscar = new Buscar(urlDeConexao, usuario, senha);
		
	}

	public static BancoDAO2 getInstance() {
		if (instance == null) {
			instance = new BancoDAO2();
		}
		return instance;
	}

	public void addCliente(Cliente cli) throws DaoException {
		cadastrar.addCliente(cli);
		getCliente(cli.getCpf());
	}

	public Cliente updateCliente(Cliente clienteNovo) throws DaoException {
		alterar.updateCliente(clienteNovo);
		return clienteNovo;
	}

	public Cliente getCliente(long cpfCliente) throws DaoException {
		if (buscar.getCliente(cpfCliente) == null) {
			throw new DaoException("");
		}
		Cliente cliente = buscar.getCliente(cpfCliente);
		return cliente;
	}

	//N�o utilizando ainda
	public Collection<Cliente> getClientes() {
		return clientes.values();
	}

	public void removeCliente(long cpfCliente) throws DaoException {
		if (buscar.getCliente(cpfCliente) == null) {
			throw new DaoException("");
		}
		excluir.removeCliente(cpfCliente);
	}
	
	
	public void addConta(Conta con) throws DaoException {
		cadastrar.addConta(con);
		getConta(con.getCodigo());
	}

	public Conta updateConta(Conta ContaNovo) throws DaoException {
		alterar.updateConta(ContaNovo);
		return ContaNovo;
	}

	public Conta getConta(int codConta) throws DaoException {
		if (buscar.getConta(codConta) == null) {
			throw new DaoException("");
		}
		Conta conta = buscar.getConta(codConta);
		return conta;

	}

	//N�o utilizando ainda
	public Collection<Conta> getContas() {
		return contas.values();
	}

	public void removeConta(int codConta) throws DaoException {
		if (buscar.getConta(codConta) == null) {
			throw new DaoException("");
		}
		excluir.removeConta(codConta);
	}

	private static Map<Long, Cliente> recuperaClientes() {
		clientes = buscar.getClientes();
		return clientes;
	}
	
	public void recuperarClientes() {
		recuperaClientes();
	}
	
	
	public static Map<Integer, Conta> recuperaConta() {
		contas = buscar.getContas();
		return contas;
	}
	
	public void recuperarContas() {
		recuperaConta();
	}
}