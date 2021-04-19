package model;

/**
 *
 * @author ANDRADE
 */
public class ItensPedidos {

	// atributos
	private int id;
	private Pedidos venda;
	private Produtos produto;
	private int qtd;
	private double subtotal;

	// getters e setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pedidos getVenda() {
		return venda;
	}

	public void setVenda(Pedidos venda) {
		this.venda = venda;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

}
