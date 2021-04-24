
public class Estoque {
	int id;
	int qntd;
	double valor;
	Filial filiais[];
	Produto produto;
	
	//aray filial para guardar estoque por filial
	
	void cadProduto(Filial filial) {
		for (int i = 0; i < filiais.length; i++){
			if (filiais[i] == null) {
				filiais[i] = filial;
				break;
			}
		}
	}
	
	void inEstoque() {
		
	}
	
	void outEstoque() {
		
	}
}
