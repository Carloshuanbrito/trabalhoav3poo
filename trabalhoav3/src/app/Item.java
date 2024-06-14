package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	    @Override
	    public String toString() {
	        return "Item{" +
	                "codigo=" + codigo +
	                ", descricao='" + descricao + '\'' +
	                ", valor=" + valor +
	                '}';
	    }
	    
	    public boolean cadastrar(Item item) {
	        try (FileWriter fw = new FileWriter("itens.txt", true);
	             BufferedWriter bw = new BufferedWriter(fw)) {
	            bw.write(item.getCodigo() + "," + item.getDescricao() + "," + item.getValor());
	            bw.newLine();
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean editar(Item item) {
	        List<Item> itens = listar();
	        try (FileWriter fw = new FileWriter("itens.txt");
	             BufferedWriter bw = new BufferedWriter(fw)) {
	            for (Item i : itens) {
	                if (i.getCodigo() == item.getCodigo()) {
	                    bw.write(item.getCodigo() + "," + item.getDescricao() + "," + item.getValor());
	                } else {
	                    bw.write(i.getCodigo() + "," + i.getDescricao() + "," + i.getValor());
	                }
	                bw.newLine();
	            }
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public Item consultar(Item item) {
	        List<Item> itens = listar();
	        for (Item i : itens) {
	            if (i.getCodigo() == item.getCodigo()) {
	                return i;
	            }
	        }
	        return null;
	    }

	    public ArrayList<Item> listar() {
	        ArrayList<Item> itens = new ArrayList<>();
	        try (FileReader fr = new FileReader("itens.txt");
	             BufferedReader br = new BufferedReader(fr)) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                Item item = new Item();
	                item.setCodigo(Integer.parseInt(data[0]));
	                item.setDescricao(data[1]);
	                item.setValor(Double.parseDouble(data[2]));
	                itens.add(item);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return itens;
	    }
}
