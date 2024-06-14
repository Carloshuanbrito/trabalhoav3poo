package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public boolean cadastrar(Consumo consumo) {
        try (FileWriter fw = new FileWriter("consumos.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataConsumoStr = sdf.format(consumo.getDataConsumo());

            bw.write(consumo.getDescricao() + "," + consumo.getQuantidadeSolicitada() + "," + dataConsumoStr);
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

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Consumo c : consumos) {
                if (c.getDescricao().equals(consumo.getDescricao())) {
                    String dataConsumoStr = sdf.format(consumo.getDataConsumo());
                    bw.write(consumo.getDescricao() + "," + consumo.getQuantidadeSolicitada() + "," + dataConsumoStr);
                } else {
                    String dataConsumoStr = sdf.format(c.getDataConsumo());
                    bw.write(c.getDescricao() + "," + c.getQuantidadeSolicitada() + "," + dataConsumoStr);
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
            if (c.getDescricao().equals(consumo.getDescricao())) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Consumo> listar() {
        ArrayList<Consumo> consumos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (FileReader fr = new FileReader("consumos.txt");
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    try {
                        Consumo consumo = new Consumo();
                        consumo.setDescricao(data[0]);
                        consumo.setQuantidadeSolicitada(Integer.parseInt(data[1]));
                        consumo.setDataConsumo(sdf.parse(data[2]));
                        consumos.add(consumo);
                    } catch (NumberFormatException | ParseException e) {
                        System.err.println("Erro ao converter dados: " + line);
                    }
                } else {
                    System.err.println("Formato de linha inválido: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return consumos;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Consumo [descricao=" + descricao + ", quantidadeSolicitada=" + quantidadeSolicitada + ", dataConsumo=" + sdf.format(dataConsumo) + "]";
    }

}
