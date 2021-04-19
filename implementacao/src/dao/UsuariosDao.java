package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import model.Usuarios;
import model.WebServiceCep;
import view.Frmlogin;
import view.Frmmenu;

/**
 *
 * @author ANDRADE
 */
public class UsuariosDao {

	private Connection conn;

	public static void main(String[] args) {

	}

	public UsuariosDao() {
		this.conn = new ConnectionFactory().getConnection();
	}

	// Metodo cadastrarUsuario
	public void cadastrarUsuario(Usuarios obj) {
		try {
			// Criar o comando sql
			String sql = "insert into tb_usuarios (nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,uf) "
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// Conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());

			stmt.setString(5, obj.getSenha());
			stmt.setString(6, obj.getCargo());
			stmt.setString(7, obj.getNivel_acesso());

			stmt.setString(8, obj.getTelefone());
			stmt.setString(9, obj.getCelular());
			stmt.setString(10, obj.getCep());
			stmt.setString(11, obj.getEndereco());
			stmt.setInt(12, obj.getNumero());
			stmt.setString(13, obj.getComplemento());
			stmt.setString(14, obj.getBairro());
			stmt.setString(15, obj.getCidade());
			stmt.setString(16, obj.getUf());

			// Executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}
	}

	// Metodo Listar Todos Usuário
	public List<Usuarios> listarUsuarios() {
		try {
			// Criar a lista
			List<Usuarios> lista = new ArrayList<>();

			// Criar o sql , organizar e executar.
			String sql = "select * from tb_usuarios";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuarios obj = new Usuarios();

				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));

				obj.setSenha(rs.getString("senha"));
				obj.setCargo(rs.getString("cargo"));
				obj.setNivel_acesso(rs.getString("nivel_acesso"));

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

	// Metodo buscarusuarioPorNome
	public List<Usuarios> buscaUsuarioPorNome(String nome) {
		try {

			// Criar um array lista
			List<Usuarios> lista = new ArrayList<>();

			// Criar o sql , organizar e executar.
			String sql = "select * from tb_usuarios where nome like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuarios obj = new Usuarios();

				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));

				obj.setSenha(rs.getString("senha"));
				obj.setCargo(rs.getString("cargo"));
				obj.setNivel_acesso(rs.getString("nivel_acesso"));

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

	// Metodo Excluir Usuários
	public void excluirUsuario(Usuarios obj) {
		try {

			// Criar o comando sql
			String sql = "delete from tb_usuarios  where id = ?";

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

	// Metodo Atualizar Usuários
	public void alterarUsuario(Usuarios obj) {
		try {

			// Criar o comando sql
			String sql = "update tb_usuarios  set  nome=?, rg=?, cpf=?, email=?, senha=?, cargo=?, nivel_acesso =?, telefone=?, celular=?, cep=?, "
					+ "endereco=?, numero=?,complemento=?,bairro=?,cidade=?, uf=?  where id =?";

			// Conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());

			stmt.setString(5, obj.getSenha());
			stmt.setString(6, obj.getCargo());
			stmt.setString(7, obj.getNivel_acesso());

			stmt.setString(8, obj.getTelefone());
			stmt.setString(9, obj.getCelular());
			stmt.setString(10, obj.getCep());
			stmt.setString(11, obj.getEndereco());
			stmt.setInt(12, obj.getNumero());
			stmt.setString(13, obj.getComplemento());
			stmt.setString(14, obj.getBairro());
			stmt.setString(15, obj.getCidade());
			stmt.setString(16, obj.getUf());

			stmt.setInt(17, obj.getId());

			// Executar o comando sql
			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro: " + erro);

		}
	}

	// Busca Cep
	public Usuarios buscaCep(String cep) {

		WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

		Usuarios obj = new Usuarios();

		if (webServiceCep.wasSuccessful()) {
			obj.setEndereco(webServiceCep.getLogradouroFull());
			obj.setCidade(webServiceCep.getCidade());
			obj.setBairro(webServiceCep.getBairro());
			obj.setUf(webServiceCep.getUf());

			return obj;
		} else {
			JOptionPane.showMessageDialog(null, "Digite o cep corretamente: " + webServiceCep.getResultText());
			return obj;
		}

	}

	// Metodo Login
	public void efetuaLogin(String email, String senha) {
		try {
			// Comando - SQL
			String sql = "select * from tb_usuarios where email = ? and senha = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, senha);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				// Caso o usuario seja do tipo admin
				if (rs.getString("nivel_acesso").equals("Administrador")) {

					JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
					Frmmenu tela = new Frmmenu();
					// tela.usuariologado = rs.getString("nome");

					tela.setVisible(true);
				}

				// Caso o usuario seja do tipo limitado
				else if (rs.getString("nivel_acesso").equals("Usuário")) {

					JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
					Frmmenu tela = new Frmmenu();
					// tela.usuariologado = rs.getString("nome");

					// Esse comando esabilitar os menus
					tela.menu_posicao_dia.setEnabled(false);
					// esse comando a baixo oculta os menus
					tela.menu_relatorio_vendas.setVisible(false);

					tela.setVisible(true);

				}
			} else {
				// Dados incorretos
				JOptionPane.showMessageDialog(null, "Dados incorretos!");
				new Frmlogin().setVisible(true);
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro : " + erro);
		}

	}

}
