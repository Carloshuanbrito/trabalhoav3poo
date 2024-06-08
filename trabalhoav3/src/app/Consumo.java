package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Consumo {
	private Item item;
	private Reserva reserva;
	private int quantidadeSolicitada;
	private Date dataConsumo;
	private String descricao;
	
	
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public int getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(int quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public Date getDataConsumo() {
		return dataConsumo;
	}

	public void setDataConsumo(Date dataConsumo) {
		this.dataConsumo = dataConsumo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
    public String toString() {
        return "Consumo{" +
                "item=" + item.getCodigo() +
                ", reserva=" + reserva.getCodigo() +
                ", quantidadeSolicitada=" + quantidadeSolicitada +
                ", dataConsumo=" + dataConsumo +
                '}';
    }
	
	public boolean cadastrar(Consumo consumo) {
        try (FileWriter fw = new FileWriter("consumos.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(consumo.getItem().getCodigo() + "," + consumo.getReserva().getCodigo() + "," + consumo.getQuantidadeSolicitada() + "," + consumo.getDataConsumo());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Consumo consumo) {
        List<Consumo> consumos = listar();
        try (FileWriter fw = new FileWriter("consumos.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (Consumo c : consumos) {
                if (c.getItem().getCodigo() == consumo.getItem().getCodigo() && c.getReserva().getCodigo() == consumo.getReserva().getCodigo()) {
                    bw.write(consumo.getItem().getCodigo() + "," + consumo.getReserva().getCodigo() + "," + consumo.getQuantidadeSolicitada() + "," + consumo.getDataConsumo());
                } else {
                    bw.write(c.getItem().getCodigo() + "," + c.getReserva().getCodigo() + "," + c.getQuantidadeSolicitada() + "," + c.getDataConsumo());
                }
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Consumo consultar(Consumo consumo) {
        List<Consumo> consumos = listar();
        for (Consumo c : consumos) {
            if (c.getItem().getCodigo() == consumo.getItem().getCodigo() && c.getReserva().getCodigo() == consumo.getReserva().getCodigo()) {
                return c;
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
	public ArrayList<Consumo> listar() {
        ArrayList<Consumo> consumos = new ArrayList<>();
        try (FileReader fr = new FileReader("consumos.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Consumo consumo = new Consumo();
                Item item = new Item();
                item.setCodigo(Integer.parseInt(data[0]));
                consumo.setItem(item);
                Reserva reserva = new Reserva();
                reserva.setCodigo(Integer.parseInt(data[1]));
                consumo.setReserva(reserva);
                consumo.setQuantidadeSolicitada(Integer.parseInt(data[2]));
                consumo.setDataConsumo(new Date(data[3]));
                consumos.add(consumo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return consumos;
    }
	
}


