package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {
	 private int codigo;
	 private Hospede hospede;
	 private Date dataCheckin;
	 private Quarto quarto;
	 private Funcionario funcionarioReserva;
	 private Funcionario funcionarioFechamento;
	 private Date dataEntradaReserva;
	 private Date dataSaidaReserva;
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


	    public boolean editar(Reserva reserva) {
	        List<Reserva> reservas = listar();
	        try (FileWriter fw = new FileWriter("reservas.txt");
	             BufferedWriter bw = new BufferedWriter(fw)) {
	            for (Reserva r : reservas) {
	                if (r.getCodigo() == reserva.getCodigo()) {
	                    bw.write(reserva.getCodigo() + "," + reserva.getHospede().getCpf() + "," + reserva.getQuarto().getCodigo() + "," +
	                             reserva.getFuncionarioReserva().getCpf() + "," + reserva.getDataEntradaReserva().getTime() + "," +
	                             reserva.getDataSaidaReserva().getTime() + "," + reserva.getDataCheckin().getTime() + "," +
	                             reserva.getDataCheckout().getTime() + "," + reserva.getValorReserva() + "," + reserva.getValorPago());
	                } else {
	                    bw.write(r.getCodigo() + "," + r.getHospede().getCpf() + "," + r.getQuarto().getCodigo() + "," +
	                             r.getFuncionarioReserva().getCpf() + "," + r.getDataEntradaReserva().getTime() + "," +
	                             r.getDataSaidaReserva().getTime() + "," + r.getDataCheckin().getTime() + "," +
	                             r.getDataCheckout().getTime() + "," + r.getValorReserva() + "," + r.getValorPago());
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
	        try (FileReader fr = new FileReader("reservas.txt");
	             BufferedReader br = new BufferedReader(fr)) {
	            
	        	String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                Reserva reserva = new Reserva();
	                reserva.setCodigo(Integer.parseInt(data[0]));
	                Hospede hospede = new Hospede();
	                hospede.setCpf(data[1]);
	                reserva.setHospede(hospede);
	                Quarto quarto = new Quarto();
	                quarto.setCodigo(Integer.parseInt(data[2]));
	                reserva.setQuarto(quarto);
	                Funcionario funcionario = new Funcionario();
	                funcionario.setCpf(data[3]);
	                reserva.setFuncionarioReserva(funcionario);
	                reserva.setDataEntradaReserva(new Date(Long.parseLong(data[4])));
	                reserva.setDataSaidaReserva(new Date(Long.parseLong(data[5])));
	                reserva.setDataCheckin(new Date(Long.parseLong(data[6])));
	                reserva.setDataCheckout(new Date(Long.parseLong(data[7])));
	                reserva.setValorReserva(Double.parseDouble(data[8]));
	                reserva.setValorPago(Double.parseDouble(data[9]));
	                reservas.add(reserva);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return reservas;
	    }

	    public void pagarReserva(int codigo) {
	    }
}
