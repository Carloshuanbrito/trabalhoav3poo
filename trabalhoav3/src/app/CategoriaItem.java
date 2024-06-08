package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaItem {
	private Item item;
	private Categoria categoria;
	private int quantidade;
	
	
	public Item getItem() {
		return item;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	 @Override
	    public String toString() {
	        return "CategoriaItem{" +
	                "item=" + item.getCodigo() +
	                ", categoria=" + categoria.getCodigo() +
	                ", quantidade=" + quantidade +
	                '}';
	    }
	
	public boolean cadastrar(CategoriaItem categoriaItem) {
        try (FileWriter fw = new FileWriter("categoriaitens.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(categoriaItem.getItem().getCodigo() + "," + categoriaItem.getCategoria().getCodigo() + "," + categoriaItem.getQuantidade());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(CategoriaItem categoriaItem) {
        List<CategoriaItem> categoriaItens = listar();
        try (FileWriter fw = new FileWriter("categoriaitens.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (CategoriaItem ci : categoriaItens) {
                if (ci.getItem().getCodigo() == categoriaItem.getItem().getCodigo() && ci.getCategoria().getCodigo() == categoriaItem.getCategoria().getCodigo()) {
                    bw.write(categoriaItem.getItem().getCodigo() + "," + categoriaItem.getCategoria().getCodigo() + "," + categoriaItem.getQuantidade());
                } else {
                    bw.write(ci.getItem().getCodigo() + "," + ci.getCategoria().getCodigo() + "," + ci.getQuantidade());
                }
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public CategoriaItem consultar(CategoriaItem categoriaItem) {
        List<CategoriaItem> categoriaItens = listar();
        for (CategoriaItem ci : categoriaItens) {
            if (ci.getItem().getCodigo() == categoriaItem.getItem().getCodigo() && ci.getCategoria().getCodigo() == categoriaItem.getCategoria().getCodigo()) {
                return ci;
            }
        }
        return null;
    }

    public ArrayList<CategoriaItem> listar() {
        ArrayList<CategoriaItem> categoriaItens = new ArrayList<>();
        try (FileReader fr = new FileReader("categoriaitens.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                CategoriaItem categoriaItem = new CategoriaItem();
                Item item = new Item();
                item.setCodigo(Integer.parseInt(data[0]));
                categoriaItem.setItem(item);
                Categoria categoria = new Categoria();
                categoria.setCodigo(Integer.parseInt(data[1]));
                categoriaItem.setCategoria(categoria);
                categoriaItem.setQuantidade(Integer.parseInt(data[2]));
                categoriaItens.add(categoriaItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoriaItens;
    }
	public void setNome(String novoNome) {
		// TODO Auto-generated method stub
		
	}
	public Object getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setItem(Item item2) {
		// TODO Auto-generated method stub
		
	}

	public void setItem(String item2) {
		// TODO Auto-generated method stub
		
	}
	
}
