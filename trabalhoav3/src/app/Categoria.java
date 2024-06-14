package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Categoria {
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
	
	@Override
    public String toString() {
        return "Categoria{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
	
	public boolean cadastrar(Categoria categoria) {
        try (FileWriter fw = new FileWriter("categorias.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(categoria.getCodigo() + "," + categoria.getDescricao() + "," + categoria.getValor());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Categoria categoria) {
        List<Categoria> categorias = listar();
        try (FileWriter fw = new FileWriter("categorias.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (Categoria c : categorias) {
                if (c.getCodigo() == categoria.getCodigo()) {
                    bw.write(categoria.getCodigo() + "," + categoria.getDescricao() + "," + categoria.getValor());
                } else {
                    bw.write(c.getCodigo() + "," + c.getDescricao() + "," + c.getValor());
                }
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Categoria consultar(Categoria categoria) {
        List<Categoria> categorias = listar();
        for (Categoria c : categorias) {
            if (c.getCodigo() == categoria.getCodigo()) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Categoria> listar() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        try (FileReader fr = new FileReader("categorias.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Categoria categoria = new Categoria();
                categoria.setCodigo(Integer.parseInt(data[0]));
                categoria.setDescricao(data[1]);
                categoria.setValor(Double.parseDouble(data[2]));
                categorias.add(categoria);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categorias;
    }

}
	