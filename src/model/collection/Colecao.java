package model.collection;

import java.util.ArrayList;
import java.util.TreeSet;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.collection.entities.Agendamento;
import model.collection.entities.Categoria;
import model.collection.entities.Cliente;
import model.collection.entities.Funcionario;
import model.collection.entities.Operacao;
import model.collection.entities.Pacote;
import model.collection.entities.PacoteAssociado;
import model.collection.entities.Servico;

public class Colecao {
	static public TreeSet<Cliente> clientes = new TreeSet<>();
	static public TreeSet<Funcionario> funcionarios = new TreeSet<>();
	static public TreeSet<Pacote> pacotes = new TreeSet<>();
	static public ArrayList<Agendamento> agendamentos = new ArrayList<>();
	static public ArrayList<PacoteAssociado> pacoteAssociados = new ArrayList<>();
	static public ArrayList<Servico> servicos = new ArrayList<>();
	static public ArrayList<Categoria> categorias = new ArrayList<>();
	static public ArrayList<Operacao> operacoes = new ArrayList<>();

	public static boolean verificaFuncionario(Funcionario fun) {
		if(Colecao.funcionarios.contains(fun)) {
			Alerts.showAlert("Alerta", "Funcionário já inscrito", "Funcionário não foi adicionado, pois ID já está em uso", AlertType.INFORMATION);
			return false;
		}
		return true;
	}
}
