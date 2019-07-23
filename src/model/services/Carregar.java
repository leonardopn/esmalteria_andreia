package model.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import db.DB;
import model.entities.Agenda;
import model.entities.Caixa;
import model.entities.Cliente;
import model.entities.Funcionario;
import model.entities.Transacao;
import model.exceptions.DbException;

public class Carregar {
	static Statement st = null;
	static ResultSet rs = null;
	static ResultSet rs2 = null;
	
	public static void carregaCliente() {
		try {	
			 st = DB.getConnection().createStatement();
			 rs = st.executeQuery("select * from cliente");
			 while(rs.next()) {
				 Cliente cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"));
				 Cadastro.clientes.add(cliente);
			 }
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeConnection();
			DB.fechaResultSet(rs);
			DB.fechaStatement(st);
		}
	}
	
	public static void carregaCaixa() {
		String linha = "";
		String caminho = System.getProperty("user.home")+File.separatorChar+"Documents"+File.separatorChar+"teste"+ File.separatorChar+"caixa.csv";
		if (IdentificadorSO.sistema() == "linux"){
				caminho = System.getProperty("user.home")+File.separatorChar+"Documentos"+File.separatorChar+"teste"+ File.separatorChar+"caixa.csv";
		}
		try(BufferedReader brCaixa = new BufferedReader(new FileReader(caminho));) {
			while((linha = brCaixa.readLine()) != null) {	
				String[] linhaCaixa = linha.split(";");				
				Caixa.setStatus(Boolean.parseBoolean(linhaCaixa[0]));
			}
			brCaixa.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void carregaFuncionario() {
		try {
			 st = DB.getConnection().createStatement();
			 rs = st.executeQuery("select * from funcionario");
			 while(rs.next()) {
				 Funcionario fun = new Funcionario(rs.getString("nome"), rs.getInt("id"),  rs.getDouble("salario"));
				 Cadastro.funcionarios.add(fun);
			 }
			 rs2 = st.executeQuery("select * from agenda");
			 for(Fun)
			 Agenda agen = new Agenda(fun, rs2.getInt("12"), rs2.getInt("12_3"), rs2.getInt("13"), rs2.getInt("13_3"), rs2.getInt("14"),
					 rs2.getInt("14_3"), rs2.getInt("15"), rs2.getInt("15_3"), rs2.getInt("16"), rs2.getInt("16_3"), rs2.getInt("17"),
					 rs2.getInt("17_3"), rs2.getInt("18"));
			 Cadastro.agendas.add(agen);
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeConnection();
			DB.fechaResultSet(rs);
			DB.fechaStatement(st);
		}
	}
	
	public static void carregaTransacaoExpecifica(LocalDate data) {
		Caixa.caixaTemp.clear();
		DateTimeFormatter localDateFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			 st = DB.getConnection().createStatement();
			 rs = st.executeQuery("SELECT * FROM caixa "
					 +"WHERE data = "
					 +"'"+localDateFormatada.format(data)+"'"
					 );
			 
			 while(rs.next()) {
				 Transacao tran = new Transacao(rs.getInt("id"), rs.getDouble("valor"), rs.getString("cliente"), rs.getString("atendente"),  rs.getString("forma_pagamento"), rs.getString("data"));
				 Caixa.caixaTemp.add(tran);
			 }
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeConnection();
			DB.fechaResultSet(rs);
			DB.fechaStatement(st);
		}
	}
	
	public static void carregaTransacao() {
		try {
			 st = DB.getConnection().createStatement();
			 rs = st.executeQuery("select * from caixa");
			 while(rs.next()) {
				 Transacao tran = new Transacao(rs.getInt("id"), rs.getDouble("valor"), rs.getString("cliente"), rs.getString("atendente"),  rs.getString("forma_pagamento"), rs.getString("data"));
				 Caixa.caixa.add(tran);
			 }
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeConnection();
			DB.fechaResultSet(rs);
			DB.fechaStatement(st);
		}
	}
	
	public static void carregar() {
		carregaCliente();
		carregaFuncionario();
		carregaTransacao();
		carregaCaixa();
	}
}
