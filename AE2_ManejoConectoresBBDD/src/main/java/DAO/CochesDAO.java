package DAO;

import database.DBConnecion;
import database.DBScheme;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase destinada a la gestion de los coches contra la BD -> querys
public class CochesDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CochesDAO() {
        connection = new DBConnecion().getConnection();
    }

    // que se pueda añadir un coche a la base de datos
    public void addCoche(Coche coche) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                DBScheme.TAB_COCHE, DBScheme.COL_COCHE_MATRICULA, DBScheme.COL_COCHE_MARCA,
                DBScheme.COL_COCHE_MODELO, DBScheme.COL_COCHE_COLOR);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());
        preparedStatement.execute();
    }

    //BORRAR COCHE POR ID
    public void deleteById(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                DBScheme.TAB_COCHE, DBScheme.COL_ID);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();

    }

    //CONSULTAR COCHE POR ID
    public Coche findById(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = id",
                DBScheme.TAB_COCHE, DBScheme.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if (!getResultados(resultSet).isEmpty()){
            return getResultados(resultSet).get(0);
        } else {
            return null;
        }
    }

    //MODIFICAR COCHE POR ID
    public void replaceOne(Coche coche, int IdCoche) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                DBScheme.TAB_COCHE, DBScheme.COL_COCHE_MATRICULA, DBScheme.COL_COCHE_MARCA,
                DBScheme.COL_COCHE_MODELO, DBScheme.COL_COCHE_COLOR, DBScheme.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());
        preparedStatement.setInt(5, IdCoche);
        preparedStatement.execute();

    }

    // LISTAR TODOS LOS COCHES
    public ArrayList<Coche> findAll() throws SQLException {
        String query = String.format("SELECT * FROM %s",
                DBScheme.TAB_COCHE);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }



    private ArrayList<Coche> getResultados(ResultSet datosResultantes) throws SQLException {
        ArrayList<Coche> listaResultado = new ArrayList<>();
        while (datosResultantes.next()){
            String matricula = resultSet.getString(DBScheme.COL_COCHE_MATRICULA);
            String marca = resultSet.getString(DBScheme.COL_COCHE_MARCA);
            String modelo = resultSet.getString(DBScheme.COL_COCHE_MODELO);
            String color = resultSet.getString(DBScheme.COL_COCHE_COLOR);
            listaResultado.add(mapearCoche(matricula,marca,modelo,color));
        }
        return listaResultado;
    }
    private Coche mapearCoche(String matricula, String marca, String modelo, String color){
        return new Coche(matricula,marca,modelo,color);
    }


}
