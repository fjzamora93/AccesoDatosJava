package DAO;

import database.DBConnection;
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



    /** que se pueda añadir un coche a la base de datos*/
    public int addNew(Coche coche) throws SQLException {
        if (findByMatricula(coche.getMatricula()) == null){
            connection = new DBConnection().getConnection();
            String query = String.format(
                    "INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                    DBScheme.TAB_COCHE, DBScheme.COL_COCHE_MATRICULA, DBScheme.COL_COCHE_MARCA,
                    DBScheme.COL_COCHE_MODELO, DBScheme.COL_COCHE_COLOR
            );
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, coche.getMatricula());
            preparedStatement.setString(2, coche.getMarca());
            preparedStatement.setString(3, coche.getModelo());
            preparedStatement.setString(4, coche.getColor());
            return preparedStatement.executeUpdate();
        }
        System.out.println("Ya existe un coche con la ID indicada");
        return 0;
    }

    private Coche findByMatricula(String matricula) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
                "SELECT * FROM %s WHERE %s = ?",
                DBScheme.TAB_COCHE, DBScheme.COL_COCHE_MATRICULA
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, matricula);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Coche> arrayResult = getResultados(resultSet);
        if (!arrayResult.isEmpty()){
            return arrayResult.get(0);
        } else {
            System.out.println("No hay coincidencias de búsqueda");
            return null;
        }
    }

    /**BORRAR COCHE POR ID*/
    public int deleteById(int id) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
                "DELETE FROM %s WHERE %s = ?",
                DBScheme.TAB_COCHE, DBScheme.COL_ID
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();

    }

    /**Consulta coche por Id*/
    public Coche findById(int id) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
                "SELECT * FROM %s WHERE %s = ?",
                DBScheme.TAB_COCHE, DBScheme.COL_ID
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        ArrayList<Coche> arrayResult = getResultados(resultSet);
        if (!arrayResult.isEmpty()){
            arrayResult.get(0).showDetails();
            return arrayResult.get(0);
        } else {
            System.out.println("No hay coincidencias de búsqueda");
            return null;
        }
    }

    /**Modifica coche por Id*/
    public int replaceOne(Coche coche, int idToUpdate) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? " + "WHERE %s = ?",
                DBScheme.TAB_COCHE, DBScheme.COL_COCHE_MATRICULA, DBScheme.COL_COCHE_MARCA,
                DBScheme.COL_COCHE_MODELO, DBScheme.COL_COCHE_COLOR, DBScheme.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());
        preparedStatement.setInt(5, idToUpdate);

        //executeUpdate retorna el número de filas modificadas
        return preparedStatement.executeUpdate();
    }

    /**Obtiene todos los coches*/
    public ArrayList<Coche> findAll() throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
            "SELECT * FROM %s",
            DBScheme.TAB_COCHE
        );
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Coche> arrayResult = getResultados(resultSet);
        for (Coche coche: arrayResult){
            coche.showDetails();
        }
        return arrayResult;
    }



    private ArrayList<Coche> getResultados(ResultSet datosResultantes) throws SQLException {
        ArrayList<Coche> listaResultado = new ArrayList<>();
        while (datosResultantes.next()){
            int id = resultSet.getInt(DBScheme.COL_ID);
            String matricula = resultSet.getString(DBScheme.COL_COCHE_MATRICULA);
            String marca = resultSet.getString(DBScheme.COL_COCHE_MARCA);
            String modelo = resultSet.getString(DBScheme.COL_COCHE_MODELO);
            String color = resultSet.getString(DBScheme.COL_COCHE_COLOR);
            listaResultado.add(mapearCoche(id, matricula,marca,modelo,color));
        }
        return listaResultado;
    }
    private Coche mapearCoche(int id, String matricula, String marca, String modelo, String color){
        return new Coche(id,matricula,marca,modelo,color);
    }


}
