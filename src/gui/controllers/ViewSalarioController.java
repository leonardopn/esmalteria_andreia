package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Notificacoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Funcionario;
import model.services.Atualizar;
import model.services.Cadastro;
import model.services.Carregar;

public class ViewSalarioController implements Initializable{

		ObservableList<Funcionario> obFuncionario;
	
	 	@FXML
	    private TextField tfSalario;

	    @FXML
	    private TextField tfFuncionario;
	    
	    @FXML
	    private TextField tfId;

	    @FXML
	    private Button btPagar;

	    @FXML
	    private TableView<Funcionario> tvFuncionario;

	    @FXML
	    private TableColumn<Funcionario, String> colunaFuncionario;

	    @FXML
	    private TableColumn<Funcionario, Double> colunaSalario;

	    @FXML
	    void onBtPagarAction(ActionEvent event) {
	    	if(tfSalario.getText().equals("0.0")) {
	    		Notificacoes.mostraNotificacao("Valor vazio", "Esse funcion�rio n�o tem o qu� receber");
	    	}
	    	else {
	    		Atualizar.atualizarSalario(tfFuncionario.getText(), - Double.parseDouble(tfSalario.getText()));
		    	Carregar.carregaFuncionario();
		    	tfFuncionario.clear();
				tfId.clear();
				tfSalario.clear();
		    	populaTabela();
		    	Notificacoes.mostraNotificacao("Notifica��o", "Pagamento efetuado!!");
	    	}
	    }
	    
	    public void populaTabela() {
	    	obFuncionario = FXCollections.observableArrayList(Cadastro.funcionarios);
	        tvFuncionario.setItems(obFuncionario);
	        tvFuncionario.refresh();
	    }
	    
	    @FXML
		public void selecionaFuncionario() {
			Funcionario fun = tvFuncionario.getSelectionModel().getSelectedItem();
			tfFuncionario.setText(fun.getNome());
			tfId.setText(String.valueOf(fun.getId()));
			tfSalario.setText(String.valueOf(fun.getSalario()));
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			colunaFuncionario.setCellValueFactory(new PropertyValueFactory<>("nome"));
	        colunaSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
	        populaTabela();
			
		}
	
}