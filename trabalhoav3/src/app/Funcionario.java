package app;

import java.util.ArrayList;

public class Funcionario extends Pessoa {
	private String setor;

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	 public boolean cadastrar(Funcionario funcionario) {
	        return true;
	    }

	    public boolean editar(Funcionario funcionario) {
	        return true;
	    }

	    public Funcionario consultar(Funcionario funcionario) {
	        return funcionario;
	    }

	    public ArrayList<Funcionario> listar() {
	        return new ArrayList<Funcionario>();
	    }
	
}
