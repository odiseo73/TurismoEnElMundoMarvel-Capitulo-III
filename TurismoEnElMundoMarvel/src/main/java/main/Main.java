package main;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import lectorDeArchivos.Parque;
import modelos.Usuario;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;


public class Main {

	public static void main(String[] args) throws SQLException, FileNotFoundException {
	
	

UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
userDAO.update(new Usuario(1,"Thor","Thor",300.0,15.0));
userDAO.update(new Usuario(2,"Loki","Loki",200.0,10.0));
userDAO.update(new Usuario(3,"Hulk","Hulk",350.0,3.0));
userDAO.update(new Usuario(4,"Ironman","Ironman",400.0,8.0));
userDAO.update(new Usuario(5,"Capitan America","Capitan America",300.0,12.0));
userDAO.update(new Usuario(6,"Thanos","Thanos",100.0,20.0));
userDAO.update(new Usuario(7,"Black Panther","Black Panther",350.0,11.0));
userDAO.update(new Usuario(8,"Spiderman","Spiderman",250.0,7.0));
	}

}
