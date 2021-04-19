/*
 * dao carrinho
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import model.ItensPedidos;
import model.Produtos;

/**
 *
 * @author ANDRADE
 */
public class ItensPedidosDao {

	private Connection conn;

	public ItensPedidosDao() {
		this.conn = new ConnectionFactory().getConnection();
	}

	// Metodo que cadastra Itens
	public void cadastraItem(ItensPedidos obj) {

		try {

			String sql = "insert into tb_itens_pedido (pedido_id, produto_id,qtd,subtotal) values (?,?,?,?)";

			// Conectar o banco de dados e organizar o comando sql
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, obj.getVenda().getId());
			stmt.setInt(2, obj.getProduto().getId());
			;
			stmt.setInt(3, obj.getQtd());
			stmt.setDouble(4, obj.getSubtotal());

			stmt.execute();
			stmt.close();
		} catch (SQLException erro) {

			JOptionPane.showMessageDialog(null, "Erroaaaa : " + erro);
		}
	}

	// Metodo que lista Itens do pedido por id
	public List<ItensPedidos> listarItensPorPedidos(int venda_id) {
		try {

			// Criar a lista
			List<ItensPedidos> lista = new ArrayList<>();

			// Criar o sql , organizar e executar.
			String sql = "select i.id, p.descricao, i.qtd, p.preco, i.subtotal from tb_itens_pedido as i "
					+ " inner join tb_produtos as p on(i.produto_id = p.id) where i.pedido_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, venda_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ItensPedidos item = new ItensPedidos();
				Produtos prod = new Produtos();

				item.setId(rs.getInt("i.id"));
				prod.setDescricao(rs.getString("p.descricao"));
				item.setQtd(rs.getInt("i.qtd"));
				prod.setPreco(rs.getDouble("p.preco"));
				item.setSubtotal(rs.getDouble("i.subtotal"));

				item.setProduto(prod);

				lista.add(item);
			}

			return lista;

		} catch (SQLException erro) {

			JOptionPane.showMessageDialog(null, "Erro :" + erro);
			return null;
		}
	}

}
