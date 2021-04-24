
public class ItensPedido {
	int id;
	Produto produto;
	double qtdItem;
	String status;
	double valUnitario;
	double valTotal;
	PedidoEstoque pedido;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public double getQtdItem() {
		return qtdItem;
	}
	public void setQtdItem(double qtdItem) {
		this.qtdItem = qtdItem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getValUnitario() {
		return valUnitario;
	}
	public void setValUnitario(double valUnitario) {
		this.valUnitario = valUnitario;
	}
	public double getValTotal() {
		return valTotal;
	}
	public void setValTotal(double valTotal) {
		this.valTotal = valTotal;
	}
	public PedidoEstoque getPedido() {
		return pedido;
	}
	public void setPedido(PedidoEstoque pedido) {
		this.pedido = pedido;
	}
	
	public double calculaTotal(double qtd, double valor) {
		return this.valTotal = qtd * valor;
	}
	
}
