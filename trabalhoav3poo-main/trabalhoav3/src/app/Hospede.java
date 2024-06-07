package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Hospede extends Pessoa {
	private String enderecoCompleto;
	
	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}
	
	public boolean cadastrar(Hospede hospede) {
        try (FileWriter fw = new FileWriter("hospedes.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(hospede.getCpf() + "," + hospede.getNome() + "," + hospede.getEmail() + "," + hospede.getEnderecoCompleto());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Hospede hospede) {
        List<Hospede> hospedes = listar();
        try (FileWriter fw = new FileWriter("hospedes.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (Hospede h : hospedes) {
                if (h.getCpf().equals(hospede.getCpf())) {
                    bw.write(hospede.getCpf() + "," + hospede.getNome() + "," + hospede.getEmail() + "," + hospede.getEnderecoCompleto());
                } else {
                    bw.write(h.getCpf() + "," + h.getNome() + "," + h.getEmail() + "," + h.getEnderecoCompleto());
                }
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Hospede consultar(Hospede hospede) {
        List<Hospede> hospedes = listar();
        for (Hospede h : hospedes) {
            if (h.getCpf().equals(hospede.getCpf())) {
                return h;
            }
        }
        return null;
    }

    public ArrayList<Hospede> listar() {
        ArrayList<Hospede> hospedes = new ArrayList<>();
        try (FileReader fr = new FileReader("hospedes.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Hospede hospede = new Hospede();
                hospede.setCpf(data[0]);
                hospede.setNome(data[1]);
                hospede.setEmail(data[2]);
                hospede.setEnderecoCompleto(data[3]);
                hospedes.add(hospede);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hospedes;
    }
	
}
