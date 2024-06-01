package app;

import java.util.ArrayList;

public class Hospede extends Pessoa {
	private String enderecoCompleto;
	
	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}
	
	public boolean cadastrar(Hospede hospede) {
        return true;
    }

    public boolean editar(Hospede hospede) {
        return true;
    }

    public Hospede consultar(Hospede hospede) {
        return hospede;
    }

    public ArrayList<Hospede> listar() {
        return new ArrayList<Hospede>();
    }
	
}
