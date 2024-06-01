package app;

import java.util.ArrayList;

public class Item {
	 private int codigo;
	    private String descricao;
	    private double valor;

	    
	    public int getCodigo() {
	        return codigo;
	    }

	    public void setCodigo(int codigo) {
	        this.codigo = codigo;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    public double getValor() {
	        return valor;
	    }

	    public void setValor(double valor) {
	        this.valor = valor;
	    }

	    
	    public boolean cadastrar(Item item) {	    
	        return true;
	    }

	    public boolean editar(Item item) {	    
	        return true;
	    }

	    public Item consultar(Item item) {	    
	        return item;
	    }

	    public ArrayList<Item> listar() {	  
	        return new ArrayList<Item>();
	    }
}
