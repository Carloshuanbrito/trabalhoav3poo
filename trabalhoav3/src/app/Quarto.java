package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	 @Override
	    public String toString() {
	        return "Quarto{" +
	                "codigo=" + codigo +
	                ", categoria=" + categoria.getCodigo() +
	                ", status='" + status + '\'' +
	                '}';
	    }

    public boolean cadastrar(Quarto quarto) {
        try (FileWriter fw = new FileWriter("quartos.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(quarto.getCodigo() + "," + quarto.getCategoria().getCodigo() + "," + quarto.getStatus());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Quarto quarto) {
        List<Quarto> quartos = listar();
        try (FileWriter fw = new FileWriter("quartos.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (Quarto q : quartos) {
                if (q.getCodigo() == quarto.getCodigo()) {
                    bw.write(quarto.getCodigo() + "," + quarto.getCategoria().getCodigo() + "," + quarto.getStatus());
                } else {
                    bw.write(q.getCodigo() + "," + q.getCategoria().getCodigo() + "," + q.getStatus());
                }
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Quarto consultar(Quarto quarto) {
        List<Quarto> quartos = listar();
        for (Quarto q : quartos) {
            if (q.getCodigo() == quarto.getCodigo()) {
                return q;
            }
        }
        return null;
    }

    public ArrayList<Quarto> listar() {
        ArrayList<Quarto> quartos = new ArrayList<>();
        try (FileReader fr = new FileReader("quartos.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Quarto quarto = new Quarto();
                quarto.setCodigo(Integer.parseInt(data[0]));
                Categoria categoria = new Categoria();
                categoria.setCodigo(Integer.parseInt(data[1]));
                quarto.setCategoria(categoria);
                quarto.setStatus(data[2]);
                quartos.add(quarto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quartos;
    }

}
