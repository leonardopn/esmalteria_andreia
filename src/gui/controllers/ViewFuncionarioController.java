package gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.utils.AlertsUtils;
import gui.utils.NotificacoesUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.collections.Colecao;
import model.collections.entities.Funcionario;
import model.daos.DaoFuncionario;

public class ViewFuncionarioController implements Initializable {

	private ObservableList<Funcionario> obFuncionario;
	private boolean parada;

	@FXML
	private Button btAtualizar;

	@FXML
	private ProgressIndicator piStatus;

	@FXML
	private Label labelStatus;

	@FXML
	private Button btCriaFuncionario;

	@FXML
	private Button btExcluiFuncionario;

	@FXML
	private TextField txtCpfFuncionario;

	@FXML
	private TextField txtNomeFuncionario;

	@FXML
	private TextField txtTelefoneFuncionario;

	@FXML
	private TableView<Funcionario> tvFuncionario = new TableView<>();

	@FXML
	private TableColumn<Funcionario, String> colunaNome;

	@FXML
	private TableColumn<Funcionario, String> colunaCpf;

	@FXML
	private TableColumn<Funcionario, String> colunaTelefone;

	@FXML
	private TableColumn<Funcionario, CheckBox> colunaSelect;

	@FXML
	public void atualizaFuncionario() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/gui/views/ViewAtualizaFuncionario.fxml"));
			Scene scene = new Scene(parent);
			ViewController.getStageFuncionario().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void carregaFuncionario() {//colocar task futuramente
		obFuncionario = FXCollections.observableArrayList(Colecao.funcionarios);
		tvFuncionario.setItems(obFuncionario);
		tvFuncionario.refresh();
	}

	@FXML
	public void onBtCriaFuncionarioAction() {
		parada = true;
		Task<Void> tarefa = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				while (parada) {
					Thread.sleep(0);
				}
				piStatus.setVisible(false);
				labelStatus.setVisible(false);
				return null;
			}
		};

		try {
			if (AlertsUtils.showAlertGenerico("Confirmação de inclusão", "Deseja mesmo incluir um funcionário?", null)) {
				if (txtCpfFuncionario.getText().isEmpty() || txtNomeFuncionario.getText().isEmpty()) {
					AlertsUtils.showAlert("Aviso", "Falta informações", "Coloco no mínimo: CPF e Nome",
							AlertType.INFORMATION);
				} else {
					piStatus.setVisible(true);
					labelStatus.setVisible(true);
					labelStatus.setText("Criando funcionário");
					Task<Void> acaoCriaFuncionario = new Task<Void>() {
						@Override
						protected Void call() throws Exception {
							Platform.runLater(() -> {
								Thread t = new Thread(tarefa);
								t.start();
							});
							if (!DaoFuncionario.salvarFuncionario(txtCpfFuncionario, txtTelefoneFuncionario,
									txtNomeFuncionario)) {
								DaoFuncionario.carregaFuncionario();
								DaoFuncionario.carregaAgendaFuncionario(ViewController.getDpDataTemp());
								carregaFuncionario();
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										obFuncionario = FXCollections.observableArrayList(Colecao.funcionarios);
										ViewController.getTvAgendaTemp().setItems(obFuncionario);
										ViewController.getTvFuncionarioTemp().setItems(obFuncionario);
										txtCpfFuncionario.setText("");
										txtNomeFuncionario.setText("");
										txtTelefoneFuncionario.setText("");
										NotificacoesUtils.mostraNotificacoes("Concluído!", "Funcionário criado com sucesso!");
									}
								});
							} else {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										AlertsUtils.showAlert("Aviso", "Funcionário já adicionado",
												"Já existe funcionário com esse cpf"
														+ " no programa ou o funcionário não foi excluído no banco de dados\n\n"
														+ "Peça ao ADMINISTRADOR para excluir o "
														+ "registro desse funcionário no BANCO ou então cpf diferente para ocorrer a diferenciação.",
												AlertType.INFORMATION);
									}
								});
							}
							parada = false;
							return null;
						}
					};

					Platform.runLater(() -> {
						ViewController.getStageCaixa().hide();
						ViewController.getStageCliente().hide();
						ViewController.getStagePagamento().hide();
						Thread t = new Thread(acaoCriaFuncionario);
						t.start();
					});
				}
			} else {
				AlertsUtils.showAlert("Cancelado", "Você cancelou a operação", "Funcionário não incluído",
						AlertType.INFORMATION);
				txtCpfFuncionario.setText("");
				txtNomeFuncionario.setText("");
				txtTelefoneFuncionario.setText("");
			}
		} catch (NumberFormatException e) {
			AlertsUtils.showAlert("Error", "Parse error", e.getMessage(), AlertType.ERROR);
		}
	}

	public void excluirFuncionario() {
		if (AlertsUtils.showAlertExclusao()) {
			parada = true;
			Task<Void> tarefa = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					while (parada) {
						Thread.sleep(0);
					}
					piStatus.setVisible(false);
					labelStatus.setVisible(false);
					return null;
				}
			};

			Task<Void> acaoExcluirFuncionario = new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					Platform.runLater(() -> {
						piStatus.setVisible(true);
						labelStatus.setVisible(true);
						labelStatus.setText("Excluíndo Funcionário");
						Thread t = new Thread(tarefa);
						t.start();
					});

					for (Funcionario fun : obFuncionario) {
						if (fun.getSelect().isSelected()) {
							DaoFuncionario.excluirFuncionario(fun);
						}
					}
					DaoFuncionario.carregaFuncionario();
					DaoFuncionario.carregaAgendaFuncionario(ViewController.getDpDataTemp());
					carregaFuncionario();

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							obFuncionario = FXCollections.observableArrayList(Colecao.funcionarios);
							ViewController.getTvAgendaTemp().setItems(obFuncionario);
							ViewController.getTvFuncionarioTemp().setItems(obFuncionario);
							NotificacoesUtils.mostraNotificacoes("Concluído!", "Funcionário excluído com sucesso!");
						}
					});
					parada = false;
					return null;
				}
			};

			Platform.runLater(() -> {
				ViewController.getStageCaixa().hide();
				ViewController.getStageCliente().hide();
				ViewController.getStagePagamento().hide();
				Thread t = new Thread(acaoExcluirFuncionario);
				t.start();
			});

		} else {
			AlertsUtils.showAlert("Cancelado", "Você cancelou a operação", "Funcionário não excluído",
					AlertType.INFORMATION);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		colunaSelect.setCellValueFactory(new PropertyValueFactory<>("select"));
		carregaFuncionario();
	}
}
