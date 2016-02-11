package dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import entity.Cliente;
import entity.Conta;
import entity.EstadoCivil;
import entity.Sexo;
import entity.TipoConta;
import exception.DaoException;

/**
 * 
 * @author Danyllo
 * Banco de dados em memória
 */
public class BancoDAO {

	private Map<Long, Cliente> clientes;
	private Map<Integer, Conta> contas;
	private static BancoDAO instance;

	private BancoDAO() {
		clientes = new HashMap<Long, Cliente>();
		contas = new HashMap<Integer, Conta>();

		//criamos um Cliente aqui para exercitar a operação de busca
		//criamos um Cliente aqui para exercitar a operação de busca
				Cliente cliente1 = new Cliente(14508011705L, "Danyllo",  "Valente da Silva", Sexo.MASCULINO, EstadoCivil.SOLTEIRO, "24525-223","Rua Danelson, 22");
				Conta conta1 = new Conta(1, "agencia", 12, TipoConta.CORRENTE, null);
				Cliente cliente2 = new Cliente( 10515029777L, "Felipe", "Santos Vieira", Sexo.MASCULINO, EstadoCivil.SOLTEIRO, "24525-224", "Rua Felinelson, 22");
				Conta conta2 = new Conta(2, "agencia", 23, TipoConta.CORRENTE, null);
				Cliente cliente3 = new Cliente(12345678910L, "Hugo", "Silva do Carmo", Sexo.MASCULINO, EstadoCivil.CASADO, "24525-225", "Rua Hugonelson, 22");
				Conta conta3 = new Conta(3, "agencia", 34, TipoConta.CORRENTE, null);
				Conta conta4 = new Conta(4, "agencia", 45, TipoConta.POUPANCA, null);
				cliente1.adicionarConta(conta1);
				cliente2.adicionarConta(conta2);
				cliente3.adicionarConta(conta3);
				cliente1.adicionarConta(conta4);
				conta1.setCliente(cliente1);
				conta2.setCliente(cliente2);
				conta3.setCliente(cliente3);
				conta4.setCliente(cliente1);
				try {
					this.addCliente(cliente1);
					this.addConta(conta1);
					this.addConta(conta4);
					this.addCliente(cliente2);
					this.addConta(conta2);
					this.addCliente(cliente3);
					this.addConta(conta3);
				} catch (DaoException e) {
					e.printStackTrace();
				}
	}

	public static BancoDAO getInstance() {
		if (instance == null) {
			instance = new BancoDAO();
		}
		return instance;
	}

	public void addCliente(Cliente cli) throws DaoException {
		clientes.put(cli.getCpf(), cli);
		getCliente(cli.getCpf());
	}

	public Cliente updateCliente(Cliente clienteNovo) throws DaoException {
		clientes.put(clienteNovo.getCpf(), clienteNovo);
		return clienteNovo;
	}

	public Cliente getCliente(long cpfCliente) throws DaoException {
		if (clientes.get(cpfCliente) == null) {
			throw new DaoException("");
		}
		Cliente cliente = clientes.get(cpfCliente);
		return cliente;

	}

	//Não utilizando ainda
	public Collection<Cliente> getClientes() {
		return clientes.values();
	}

	public void removeCliente(long cpfCliente) throws DaoException {
		if (clientes.get(cpfCliente) == null) {
			throw new DaoException("");
		}
		clientes.remove(cpfCliente);
	}
	
	
	public void addConta(Conta con) throws DaoException {
		contas.put(con.getCodigo(), con);
		getConta(con.getCodigo());
		//raizDePersistencia.add(con);
	}

	public Conta updateConta(Conta ContaNovo) throws DaoException {
		contas.put(ContaNovo.getCodigo(), ContaNovo);
		return ContaNovo;
	}

	public Conta getConta(int codConta) throws DaoException {
		if (contas.get(codConta) == null) {
			throw new DaoException("");
		}
		Conta conta = contas.get(codConta);
		return conta;

	}

	//Não utilizando ainda
	public Collection<Conta> getContas() {
		return contas.values();
	}

	public void removeConta(int codConta) throws DaoException {
		if (contas.get(codConta) == null) {
			throw new DaoException("");
		}
		contas.remove(codConta);
	}
	
	public void gravarCliente(Map<Long, Cliente> clientes){
		this.clientes = clientes;
	}

	public Map<Long, Cliente> recuperaCliente() {
		return clientes;
	}

	public void salvarClientes() {
		gravarCliente(clientes);
	}
	
	public void recuperarClientes() {
		recuperaCliente();
	}
	
	public void gravarConta (Map<Integer, Conta> contas) {
		this.contas = contas;
	}
	
	public Map<Integer, Conta> recuperaConta() {
		return contas;
	}
	
	public void salvarContas() {
		gravarConta(contas);
	}
	
	public void recuperarContas() {
		recuperaConta();
	}
}