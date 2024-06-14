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

public class ConsumoServico {
	private Servico servico;
	private Reserva reserva;
	private int quantidadeSolicitada;
	private Date dataServico;

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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

	public Date getDataServico() {
		return dataServico;
	}

	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
	}

	@Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "ConsumoServico{" +
                "servico=" + servico.getCodigo() +
                ", reserva=" + reserva.getCodigo() +
                ", quantidadeSolicitada=" + quantidadeSolicitada +
                ", dataServico=" + sdf.format(dataServico) +
                '}';
    }

    public boolean cadastrar(ConsumoServico consumoServico) {
        try (FileWriter fw = new FileWriter("consumoservicos.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataServicoStr = sdf.format(consumoServico.getDataServico());
            bw.write(consumoServico.getServico().getCodigo() + "," +
                     consumoServico.getReserva().getCodigo() + "," +
                     consumoServico.getQuantidadeSolicitada() + "," +
                     dataServicoStr);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(ConsumoServico consumoServico) {
        List<ConsumoServico> consumoServicos = listar();
        try (FileWriter fw = new FileWriter("consumoservicos.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (ConsumoServico cs : consumoServicos) {
                if (cs.getServico().getCodigo() == consumoServico.getServico().getCodigo()
                        && cs.getReserva().getCodigo() == consumoServico.getReserva().getCodigo()) {
                    String dataServicoStr = sdf.format(consumoServico.getDataServico());
                    bw.write(consumoServico.getServico().getCodigo() + "," +
                             consumoServico.getReserva().getCodigo() + "," +
                             consumoServico.getQuantidadeSolicitada() + "," +
                             dataServicoStr);
                } else {
                    String dataServicoStr = sdf.format(cs.getDataServico());
                    bw.write(cs.getServico().getCodigo() + "," +
                             cs.getReserva().getCodigo() + "," +
                             cs.getQuantidadeSolicitada() + "," +
                             dataServicoStr);
                }
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ConsumoServico consultar(ConsumoServico consumoServico) {
        List<ConsumoServico> consumoServicos = listar();
        for (ConsumoServico cs : consumoServicos) {
            if (cs.getServico().getCodigo() == consumoServico.getServico().getCodigo()
                    && cs.getReserva().getCodigo() == consumoServico.getReserva().getCodigo()) {
                return cs;
            }
        }
        return null;
    }

    public ArrayList<ConsumoServico> listar() {
        ArrayList<ConsumoServico> consumoServicos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (FileReader fr = new FileReader("consumoservicos.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    try {
                        ConsumoServico consumoServico = new ConsumoServico();
                        Servico servico = new Servico();
                        servico.setCodigo(Integer.parseInt(data[0]));
                        consumoServico.setServico(servico);

                        Reserva reserva = new Reserva();
                        reserva.setCodigo(Integer.parseInt(data[1]));
                        consumoServico.setReserva(reserva);

                        consumoServico.setQuantidadeSolicitada(Integer.parseInt(data[2]));
                        consumoServico.setDataServico(sdf.parse(data[3]));

                        consumoServicos.add(consumoServico);
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
        return consumoServicos;
    }

}
