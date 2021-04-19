package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import model.Fornecedores;

/**
 *
 * @author ANDRADE
 */
public class FornecedoresDao {

	private Connection conn;

	public FornecedoresDao() {
		this.conn = new ConnectionFactory().getConnection();
	}

	// Metodo cadastrarFornecedor
	public void cadastrarFornecedores(Fornecedores obj) {
		try {

			// Criar o comando sql
			String sql = "insert into tb_fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,uf) "
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?)";

			// Conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCnpj());
			stmt.setString(3, obj.getEmail());
			stmt.setString(4, obj.getTelefone());
			stmt.setString(5, obj.getCelular());
			stmt.setString(6, obj.getCep());
			stmt.setString(7, obj.getEndereco());
			stmt.setInt(8, obj.getNumero());
			stmt.setString(9, obj.getComplemento());
			stmt.setString(10, obj.getBairro());
			stmt.setString(11, obj.getCidade());
			stmt.setString(12, obj.getUf());

			// Executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}
	}

	// Metodo AlterarFornecedores
	public void alterarFornecedor(Fornecedores obj) {
		try {

			// Criar o comando sql
			String sql = "update tb_fornecedores set  nome=?, cnpj=?, email=?, telefone=?, celular=?, cep=?, "
					+ "endereco=?, numero=?,complemento=?,bairro=?,cidade=?, uf=?  where id =?";

			// Conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCnpj());
			stmt.setString(3, obj.getEmail());
			stmt.setString(4, obj.getTelefone());
			stmt.setString(5, obj.getCelular());
			stmt.setString(6, obj.getCep());
			stmt.setString(7, obj.getEndereco());
			stmt.setInt(8, obj.getNumero());
			stmt.setString(9, obj.getComplemento());
			stmt.setString(10, obj.getBairro());
			stmt.setString(11, obj.getCidade());
			stmt.setString(12, obj.getUf());

			stmt.setInt(13, obj.getId());

			// Executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}
	}

	// Metodo ExcluirFornecedor
	public void excluirFornecedor(Fornecedores obj) {
		try {

			// Criar o comando sql
			String sql = "delete from tb_fornecedores where id = ?";

			// Conectar o banco de dados e organizar o comando sql
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

	// Metodo Listar Todos Fornecedores
	public List<Fornecedores> listaFornecedor() {
		try {

			// Criar a um array lista
			List<Fornecedores> lista = new ArrayList<>();

			// Criar o sql , organizar e executar.
			String sql = "select * from tb_fornecedores";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet row = stmt.executeQuery();

			while (row.next()) {
				// Instancia o objeto Fornecedores
				Fornecedores obj = new Fornecedores();

				obj.setId(row.getInt("id"));
				obj.setNome(row.getString("nome"));
				obj.setCnpj(row.getString("cnpj"));
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

	// Metodo buscarfornecedoresePorNome
	public List<Fornecedores> buscaFornecedorPorNome(String nome) {
		try {

			// Criar um array lista
			List<Fornecedores> lista = new ArrayList<>();

			// Criar o sql , organizar e executar.
			String sql = "select * from tb_fornecedores where nome like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Fornecedores obj = new Fornecedores();

				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setCnpj(rs.getString("cnpj"));
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

}
