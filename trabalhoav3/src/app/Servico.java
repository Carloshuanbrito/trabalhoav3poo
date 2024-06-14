package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Servico {
	private int codigo;
	private String descricao;
	private Double valor;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
    public String toString() {
        return "Servico{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
	
	 public boolean cadastrar(Servico servico) {
	        try (FileWriter fw = new FileWriter("servicos.txt", true);
	             BufferedWriter bw = new BufferedWriter(fw)) {
	            bw.write(servico.getCodigo() + "," + servico.getDescricao() + "," + servico.getValor());
	            bw.newLine();
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean editar(Servico servico) {
	        List<Servico> servicos = listar();
	        try (FileWriter fw = new FileWriter("servicos.txt");
	             BufferedWriter bw = new BufferedWriter(fw)) {
	            for (Servico s : servicos) {
	                if (s.getCodigo() == servico.getCodigo()) {
	                    bw.write(servico.getCodigo() + "," + servico.getDescricao() + "," + servico.getValor());
	                } else {
	                    bw.write(s.getCodigo() + "," + s.getDescricao() + "," + s.getValor());
	                }
	                bw.newLine();
	            }
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public Servico consultar(Servico servico) {
	        List<Servico> servicos = listar();
	        for (Servico s : servicos) {
	            if (s.getCodigo() == servico.getCodigo()) {
	                return s;
	            }
	        }
	        return null;
	    }

	    public ArrayList<Servico> listar() {
	        ArrayList<Servico> servicos = new ArrayList<>();
	        try (FileReader fr = new FileReader("servicos.txt");
	             BufferedReader br = new BufferedReader(fr)) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                Servico servico = new Servico();
	                servico.setCodigo(Integer.parseInt(data[0]));
	                servico.setDescricao(data[1]);
	                servico.setValor(Double.parseDouble(data[2]));
	                servicos.add(servico);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return servicos;
	    }
	
}
