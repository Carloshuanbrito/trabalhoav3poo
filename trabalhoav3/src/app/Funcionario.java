package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {
	private String setor;

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	 public boolean cadastrar(Funcionario funcionario) {
	        try (FileWriter fw = new FileWriter("funcionarios.txt", true);
	             BufferedWriter bw = new BufferedWriter(fw)) {
	            bw.write(funcionario.getCpf() + "," + funcionario.getNome() + "," + funcionario.getEmail() + "," + funcionario.getSetor());
	            bw.newLine();
	            bw.close();
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean editar(Funcionario funcionario) {
	        List<Funcionario> funcionarios = listar();
	        try (FileWriter fw = new FileWriter("funcionarios.txt");
	             BufferedWriter bw = new BufferedWriter(fw)) {
	            for (Funcionario f : funcionarios) {
	                if (f.getCpf().equals(funcionario.getCpf())) {
	                    bw.write(funcionario.getCpf() + "," + funcionario.getNome() + "," + funcionario.getEmail() + "," + funcionario.getSetor());
	                } else {
	                    bw.write(f.getCpf() + "," + f.getNome() + "," + f.getEmail() + "," + f.getSetor());
	                }
	                bw.newLine();
	            }
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public Funcionario consultar(Funcionario funcionario) {
	        List<Funcionario> funcionarios = listar();
	        for (Funcionario f : funcionarios) {
	            if (f.getCpf().equals(funcionario.getCpf())) {
	                return f;
	            }
	        }
	        return null;
	    }

	    public ArrayList<Funcionario> listar() {
	    	 ArrayList<Funcionario> funcionarios = new ArrayList<>();
	         try (FileReader fr = new FileReader("funcionarios.txt");
	              BufferedReader br = new BufferedReader(fr)) {
	             String line;
	             while ((line = br.readLine()) != null) {
	                 String[] data = line.split(",");
	                 Funcionario funcionario = new Funcionario();
	                 funcionario.setCpf(data[0]);
	                 funcionario.setNome(data[1]);
	                 funcionario.setEmail(data[2]);
	                 funcionario.setSetor(data[3]);
	                 funcionarios.add(funcionario);
	             }
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	         return funcionarios;
	     }
	    }
	
