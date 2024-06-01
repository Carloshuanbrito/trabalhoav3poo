package app;

import java.util.ArrayList;

public class Quarto {
	private int codigo;
    private Categoria categoria;
    private String status;
    private double valor;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public boolean cadastrar(Quarto quarto) {

        return true;
    }

    public boolean editar(Quarto quarto) {
        return true;
    }

    public Quarto consultar(Quarto quarto) {
        return quarto;
    }

    public ArrayList<Quarto> listar() {
        return new ArrayList<Quarto>();
    }

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
