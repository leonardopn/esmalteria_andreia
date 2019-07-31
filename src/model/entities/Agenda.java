package model.entities;

import java.util.ArrayList;

public class Agenda {
	
	
	private String funcionario;
	private ArrayList<Agendamento> agendamentos;
	private String Data;
	private String h12;
	private String h12_3;
	private String h13;
	private String h13_3;
	private String h14;
	private String h14_3;
	private String h15;
	private String h15_3;
	private String h16;
	private String h16_3;
	private String h17;
	private String h17_3;
	private String h18;
	
	public Agenda(String funcionario) {
		this.agendamentos = new ArrayList<>();
		this.funcionario = funcionario;
		this.h12 = "Livre";
		this.h12_3 = "Livre";
		this.h13 = "Livre";
		this.h13_3 = "Livre";
		this.h14 = "Livre";
		this.h14_3 = "Livre";
		this.h15 = "Livre";
		this.h15_3 = "Livre";
		this.h16 = "Livre";
		this.h16_3 = "Livre";
		this.h17 = "Livre";
		this.h17_3 = "Livre";
		this.h18 = "Livre";
	}
	
	public void retornaHorario(String horario, Agenda agenda, String cliente) {
		if(horario.equals("12:00")) {
			agenda.setH12(cliente);
		}
		if(horario.equals("12:30")) {
			agenda.setH12_3(cliente);
		}
		if(horario.equals("13:00")) {
			agenda.setH13(cliente);
		}
		if(horario.equals("13:30")) {
			agenda.setH13_3(cliente);
		}
		if(horario.equals("14:00")) {
			agenda.setH14(cliente);
		}
		if(horario.equals("14:30")) {
			agenda.setH14_3(cliente);
		}
		if(horario.equals("15:00")) {
			agenda.setH15(cliente);
		}
		if(horario.equals("15:30")) {
			agenda.setH15_3(cliente);
		}
		if(horario.equals("16:00")) {
			agenda.setH16(cliente);
		}
		if(horario.equals("16:30")) {
			agenda.setH16_3(cliente);
		}
		if(horario.equals("17:00")) {
			agenda.setH17(cliente);
		}
		if(horario.equals("17:30")) {
			agenda.setH17_3(cliente);
		}
		if(horario.equals("18:00")) {
			agenda.setH18(cliente);
		}
	}
	
	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getH12() {
		return h12;
	}
	public void setH12(String h12) {
		this.h12 = h12;
	}
	public String getH12_3() {
		return h12_3;
	}
	public void setH12_3(String h12_3) {
		this.h12_3 = h12_3;
	}
	public String getH13() {
		return h13;
	}
	public void setH13(String h13) {
		this.h13 = h13;
	}
	public String getH13_3() {
		return h13_3;
	}
	public void setH13_3(String h13_3) {
		this.h13_3 = h13_3;
	}
	public String getH14() {
		return h14;
	}
	public void setH14(String h14) {
		this.h14 = h14;
	}
	public String getH14_3() {
		return h14_3;
	}
	public void setH14_3(String h14_3) {
		this.h14_3 = h14_3;
	}
	public String getH15() {
		return h15;
	}
	public void setH15(String h15) {
		this.h15 = h15;
	}
	public String getH15_3() {
		return h15_3;
	}
	public void setH15_3(String h15_3) {
		this.h15_3 = h15_3;
	}
	public String getH16() {
		return h16;
	}
	public void setH16(String h16) {
		this.h16 = h16;
	}
	public String getH16_3() {
		return h16_3;
	}
	public void setH16_3(String h16_3) {
		this.h16_3 = h16_3;
	}
	public String getH17() {
		return h17;
	}
	public void setH17(String h17) {
		this.h17 = h17;
	}
	public String getH17_3() {
		return h17_3;
	}
	public void setH17_3(String h17_3) {
		this.h17_3 = h17_3;
	}
	public String getH18() {
		return h18;
	}
	public void setH18(String h18) {
		this.h18 = h18;
	}

	public ArrayList<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(ArrayList<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	@Override
	public String toString() {
		return "\n\nfuncionario=" + funcionario + ", h12=" + h12 + ", h12_3=" + h12_3 + ", h13=" + h13 + ", h13_3="
				+ h13_3 + ", h14=" + h14 + ", h14_3=" + h14_3 + ", h15=" + h15 + ", h15_3=" + h15_3 + ", h16=" + h16
				+ ", h16_3=" + h16_3 + ", h17=" + h17 + ", h17_3=" + h17_3 + ", h18=" + h18+"\n\n";
	}
}