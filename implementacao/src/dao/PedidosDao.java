/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import model.Clientes;
import model.Pedidos;

/**
 *
 * @author ANDRADE
 */
public class PedidosDao {

	private Connection conn;

	public PedidosDao() {
		this.conn = new ConnectionFactory().getConnection();
	}

	// Cadastrar Pedido
	public void cadastrarPedido(Pedidos obj) {
		try {

			String sql = "insert into tb_pedido_estoque (cliente_id, data_venda,total_venda,observacoes) values (?,?,?,?)";
			// Conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, obj.getCliente().getId());
			stmt.setString(2, obj.getData_venda());
			stmt.setDouble(3, obj.getTotal_venda());
			stmt.setString(4, obj.getObs());

			stmt.execute();
			stmt.close();

			JOptionPane.showMessageDialog(null, "Venda registrada!");

		} catch (SQLException erro) {

			JOptionPane.showMessageDialog(null, "Erro : " + erro);
		}
	}

	// Retorna a ultimo pedido
	public int retornaUltimoPedido() {
		try {
			int idpedido = 0;

			String sql = "select max(id) id from tb_pedido_estoque";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Pedidos p = new Pedidos();

				p.setId(rs.getInt("id"));
				idpedido = p.getId();
			}

			return idpedido;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Metodo que filtra Pedidos por Datas
	public List<Pedidos> listarPedidosPorPeriodo(LocalDate data_inicio, LocalDate data_fim) {
		try {

			// Criar a lista
			List<Pedidos> lista = new ArrayList<>();

			// Criar o sql , organizar e executar.
			String sql = "select v.id ,  date_format(v.data_venda,'%d/%m/%Y') as data_formatada , c.nome, v.total_venda, v.observacoes  from tb_pedido_estoque as v  "
					+ " inner join tb_clientes as c on(v.cliente_id = c.id) where v.data_venda BETWEEN ? AND ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, data_inicio.toString());
			stmt.setString(2, data_fim.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pedidos obj = new Pedidos();
				Clientes c = new Clientes();

				obj.setId(rs.getInt("v.id"));
				obj.setData_venda(rs.getString("data_formatada"));
				c.setNome(rs.getString("c.nome"));
				obj.setTotal_venda(rs.getDouble("v.total_venda"));
				obj.setObs(rs.getString("v.observacoes"));

				obj.setCliente(c);

				lista.add(obj);
			}

			return lista;

		} catch (SQLException erro) {

			JOptionPane.showMessageDialog(null, "Erro :" + erro);
			return null;
		}

	}

	// Metodo que calcula total da pedidos por data
	public double retornatotalpordata(LocalDate data_venda) {
		try {

			double totalvenda = 0;

			String sql = "select sum(total_venda) as total from tb_pedido_estoque where data_venda = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, data_venda.toString());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				totalvenda = rs.getDouble("total");
			}

			return totalvenda;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
