package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import model.Clientes;
import model.WebServiceCep;

/**
 *
 * @author ANDRADE
 */
public class ClientesDao {

	private Connection conn;

	public ClientesDao() {
		this.conn = new ConnectionFactory().getConnection();
	}

	// Metodo cadastrarCliente
	public void cadastrarCliente(Clientes obj) {
		try {

			// Criar o comando sql
			String sql = "insert into tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,uf) "
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// Conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getTelefone());
			stmt.setString(6, obj.getCelular());
			stmt.setString(7, obj.getCep());
			stmt.setString(8, obj.getEndereco());
			stmt.setInt(9, obj.getNumero());
			stmt.setString(10, obj.getComplemento());
			stmt.setString(11, obj.getBairro());
			stmt.setString(12, obj.getCidade());
			stmt.setString(13, obj.getUf());

			// Executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}
	}

	// Metodo AlterarCliente
	public void alterarCliente(Clientes obj) {
		try {

			// Criar o comando sql
			String sql = "update tb_clientes set  nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?, cep=?, "
					+ "endereco=?, numero=?,complemento=?,bairro=?,cidade=?, uf=?  where id =?";

			// Conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getTelefone());
			stmt.setString(6, obj.getCelular());
			stmt.setString(7, obj.getCep());
			stmt.setString(8, obj.getEndereco());
			stmt.setInt(9, obj.getNumero());
			stmt.setString(10, obj.getComplemento());
			stmt.setString(11, obj.getBairro());
			stmt.setString(12, obj.getCidade());
			stmt.setString(13, obj.getUf());

			stmt.setInt(14, obj.getId());

			// Executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}
	}

	// Metodo ExcluirCliente
	public void excluirCliente(Clientes obj) {
		try {

			// Criar o comando sql
			String sql = "delete from tb_clientes where id = ?";

			// 2 passo - conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obj.getId());

			// Executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);
		}
	}

	// Metodo Listar Todos Clientes
	public List<Clientes> listarClientes() {
		try {

			// Criar a um array lista
			List<Clientes> lista = new ArrayList<>();

			// Criar o sql , organizar e executar.
			String sql = "select * from tb_clientes";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet row = stmt.executeQuery();

			while (row.next()) {
				// Instancia o objeto cliente
				Clientes obj = new Clientes();

				obj.setId(row.getInt("id"));
				obj.setNome(row.getString("nome"));
				obj.setRg(row.getString("rg"));
				obj.setCpf(row.getString("cpf"));
				obj.setEmail(row.getString("email"));
				obj.setTelefone(row.getString("telefone"));
				obj.setCelular(row.getString("celular"));
				obj.setCep(row.getString("cep"));
				obj.setEndereco(row.getString("endereco"));
				obj.setNumero(row.getInt("numero"));
				obj.setComplemento(row.getString("complemento"));
				obj.setBairro(row.getString("bairro"));
				obj.setCidade(row.getString("cidade"));
				obj.setUf(row.getString("uf"));

				// Armazena os valores na lista
				lista.add(obj);
			}

			return lista;

		} catch (SQLException erro) {

			JOptionPane.showMessageDialog(null, "Erro :" + erro);
			return null;
		}

	}

	// Metodo buscarclientePorNome
	public List<Clientes> buscaClientePorNome(String nome) {
		try {

			// Criar um array lista
			List<Clientes> lista = new ArrayList<>();

			// Criar o sql , organizar e executar.
			String sql = "select * from tb_clientes where nome like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Clientes obj = new Clientes();

				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone"));
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setUf(rs.getString("uf"));

				lista.add(obj);
			}
			return lista;

		} catch (SQLException erro) {

			JOptionPane.showMessageDialog(null, "Erro :" + erro);
			return null;
		}
	}

	// metodo buscaProduto por Codigo
	public Clientes buscaPorCFPClientes(String cpf) {
		try {
			// Criar o sql , organizar e executar.

			String sql = "select * from tb_clientes where cpf =  ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cpf);

			ResultSet rs = stmt.executeQuery();
			Clientes obj = new Clientes();

			if (rs.next()) {

				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone"));
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setUf(rs.getString("uf"));

			} else {
				JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
			}

			return obj;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
			return null;
		}
	}

	// Busca Cep
	public Clientes buscaCep(String cep) {

		WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

		Clientes obj = new Clientes();

		if (webServiceCep.wasSuccessful()) {
			obj.setEndereco(webServiceCep.getLogradouroFull());
			obj.setCidade(webServiceCep.getCidade());
			obj.setBairro(webServiceCep.getBairro());
			obj.setUf(webServiceCep.getUf());

			return obj;
		} else {
			JOptionPane.showMessageDialog(null, "Digite o cep corretamente: " + webServiceCep.getResulCode());
			JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
			return null;
		}

	}

}
