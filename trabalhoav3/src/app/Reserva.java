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

public class Reserva {
	private int codigo;
    private Hospede hospede;
    private Quarto quarto;
    private Funcionario funcionarioReserva;
    private Funcionario funcionarioFechamento;
    private Date dataEntradaReserva;
    private Date dataSaidaReserva;
    private Date dataCheckin;
    private Date dataCheckout;
    private double valorReserva;
    private double valorPago;

	  public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Funcionario getFuncionarioReserva() {
        return funcionarioReserva;
    }

    public void setFuncionarioReserva(Funcionario funcionarioReserva) {
        this.funcionarioReserva = funcionarioReserva;
    }

    public Funcionario getFuncionarioFechamento() {
        return funcionarioFechamento;
    }

    public void setFuncionarioFechamento(Funcionario funcionarioFechamento) {
        this.funcionarioFechamento = funcionarioFechamento;
    }

    public Date getDataEntradaReserva() {
        return dataEntradaReserva;
    }

    public void setDataEntradaReserva(Date dataEntradaReserva) {
        this.dataEntradaReserva = dataEntradaReserva;
    }

    public Date getDataSaidaReserva() {
        return dataSaidaReserva;
    }

    public void setDataSaidaReserva(Date dataSaidaReserva) {
        this.dataSaidaReserva = dataSaidaReserva;
    }

    public Date getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(Date dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public Date getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(Date dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public double getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(double valorReserva) {
        this.valorReserva = valorReserva;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
	    
	    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Reserva{" +
                "codigo=" + codigo +
                ", hospede=" + hospede.getCpf() +
                ", quarto=" + quarto.getCodigo() +
                ", funcionarioReserva=" + funcionarioReserva.getCpf() +
                ", funcionarioFechamento=" + funcionarioFechamento.getCpf() +
                ", dataEntradaReserva=" + sdf.format(dataEntradaReserva) +
                ", dataSaidaReserva=" + sdf.format(dataSaidaReserva) +
                ", dataCheckin=" + sdf.format(dataCheckin) +
                ", dataCheckout=" + sdf.format(dataCheckout) +
                ", valorReserva=" + valorReserva +
                ", valorPago=" + valorPago +
                '}';
    }

    public boolean cadastrar(Reserva reserva) {
        try (FileWriter fw = new FileWriter("reservas.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataEntradaReservaStr = sdf.format(reserva.getDataEntradaReserva());
            String dataSaidaReservaStr = sdf.format(reserva.getDataSaidaReserva());
            String dataCheckinStr = sdf.format(reserva.getDataCheckin());
            String dataCheckoutStr = sdf.format(reserva.getDataCheckout());
            bw.write(reserva.getCodigo() + "," +
                     reserva.getHospede().getCpf() + "," +
                     reserva.getQuarto().getCodigo() + "," +
                     reserva.getFuncionarioReserva().getCpf() + "," +
                     reserva.getFuncionarioFechamento().getCpf() + "," +
                     dataEntradaReservaStr + "," +
                     dataSaidaReservaStr + "," +
                     dataCheckinStr + "," +
                     dataCheckoutStr + "," +
                     reserva.getValorReserva() + "," +
                     reserva.getValorPago());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Reserva reserva) {
        List<Reserva> reservas = listar();
        try (FileWriter fw = new FileWriter("reservas.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Reserva r : reservas) {
                if (r.getCodigo() == reserva.getCodigo()) {
                    String dataEntradaReservaStr = sdf.format(reserva.getDataEntradaReserva());
                    String dataSaidaReservaStr = sdf.format(reserva.getDataSaidaReserva());
                    String dataCheckinStr = sdf.format(reserva.getDataCheckin());
                    String dataCheckoutStr = sdf.format(reserva.getDataCheckout());
                    bw.write(reserva.getCodigo() + "," +
                             reserva.getHospede().getCpf() + "," +
                             reserva.getQuarto().getCodigo() + "," +
                             reserva.getFuncionarioReserva().getCpf() + "," +
                             reserva.getFuncionarioFechamento().getCpf() + "," +
                             dataEntradaReservaStr + "," +
                             dataSaidaReservaStr + "," +
                             dataCheckinStr + "," +
                             dataCheckoutStr + "," +
                             reserva.getValorReserva() + "," +
                             reserva.getValorPago());
                } else {
                    String dataEntradaReservaStr = sdf.format(r.getDataEntradaReserva());
                    String dataSaidaReservaStr = sdf.format(r.getDataSaidaReserva());
                    String dataCheckinStr = sdf.format(r.getDataCheckin());
                    String dataCheckoutStr = sdf.format(r.getDataCheckout());
                    bw.write(r.getCodigo() + "," +
                             r.getHospede().getCpf() + "," +
                             r.getQuarto().getCodigo() + "," +
                             r.getFuncionarioReserva().getCpf() + "," +
                             r.getFuncionarioFechamento().getCpf() + "," +
                             dataEntradaReservaStr + "," +
                             dataSaidaReservaStr + "," +
                             dataCheckinStr + "," +
                             dataCheckoutStr + "," +
                             r.getValorReserva() + "," +
                             r.getValorPago());
                }
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Reserva consultar(Reserva reserva) {
        List<Reserva> reservas = listar();
        for (Reserva r : reservas) {
            if (r.getCodigo() == reserva.getCodigo()) {
                return r;
            }
        }
        return null;
    }

    public ArrayList<Reserva> listar() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (FileReader fr = new FileReader("reservas.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 11) {
                    try {
                        Reserva reserva = new Reserva();
                        reserva.setCodigo(Integer.parseInt(data[0]));

                        Hospede hospede = new Hospede();
                        hospede.setCpf(data[1]);
                        reserva.setHospede(hospede);

                        Quarto quarto = new Quarto();
                        quarto.setCodigo(Integer.parseInt(data[2]));
                        reserva.setQuarto(quarto);

                        Funcionario funcionarioReserva = new Funcionario();
                        funcionarioReserva.setCpf(data[3]);
                        reserva.setFuncionarioReserva(funcionarioReserva);

                        Funcionario funcionarioFechamento = new Funcionario();
                        funcionarioFechamento.setCpf(data[4]);
                        reserva.setFuncionarioFechamento(funcionarioFechamento);

                        reserva.setDataEntradaReserva(sdf.parse(data[5]));
                        reserva.setDataSaidaReserva(sdf.parse(data[6]));
                        reserva.setDataCheckin(sdf.parse(data[7]));
                        reserva.setDataCheckout(sdf.parse(data[8]));

                        reserva.setValorReserva(Double.parseDouble(data[9]));
                        reserva.setValorPago(Double.parseDouble(data[10]));

                        reservas.add(reserva);
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
        return reservas;
    }

    public void pagarReserva(int codigo) {
        List<Reserva> reservas = listar();
        for (Reserva r : reservas) {
            if (r.getCodigo() == codigo) {
                r.setValorPago(r.getValorReserva());
                editar(r);
                break;
            }
        }
    }

}
