package dao;
import database.DbConnection;
import database.DbScheme;
import model.Usuario;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String path = "./src/main/resource/usuarios.obj";
    private static File file = null;

    //private Usuario usuario;
    private Usuario usuario;
    private List<Usuario> usuarioList;
    static Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UsuarioDAO(){
        this.usuarioList = new ArrayList<>();
        file = new File(path);

    }

    public int addNew(Usuario usuario) throws SQLException {

        this.findAll();
        for (Usuario user: usuarioList){
            if (user.equals(usuario)){
                System.out.println("El usuario ya está registrado");
                return 0;
            }
        }

        this.connection = new DbConnection().getConnection();
        String query = String.format(
                "INSERT INTO %s (%s, %s, %s,  %s) VALUES(?,?,?,?)",
                DbScheme.TAB_USUARIOS,
                DbScheme.COL_NOMBRE, DbScheme.COL_APELLIDO, DbScheme.COL_CORREO, DbScheme.COL_CONTRASEÑA
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, usuario.getNombre());
        preparedStatement.setString(2, usuario.getApellido());
        preparedStatement.setString(3, usuario.getCorreo());
        preparedStatement.setString(4, usuario.getContraseña());
        return preparedStatement.executeUpdate();
    }

    public List<Usuario> findAll() throws SQLException {
        this.connection = new DbConnection().getConnection();
        String query = String.format(
                "SELECT * FROM %s",
                DbScheme.TAB_USUARIOS
        );

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            usuarioList.add(
                    new Usuario(
                            resultSet.getString(DbScheme.COL_NOMBRE),
                            resultSet.getString(DbScheme.COL_APELLIDO),
                            resultSet.getString(DbScheme.COL_CORREO),
                            resultSet.getString(DbScheme.COL_CONTRASEÑA)
                    )
            );
        }
        return this.usuarioList;
    }


    public Usuario checkCredentials(String correo, String contraseña) throws SQLException {
        this.connection = new DbConnection().getConnection();
        String query = String.format(
                "SELECT * FROM %s WHERE %s = ? AND %s = ?",
                DbScheme.TAB_USUARIOS,
                DbScheme.COL_CORREO,
                DbScheme.COL_CONTRASEÑA
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, correo);
        preparedStatement.setString(2, contraseña);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            return new Usuario(
                resultSet.getString(DbScheme.COL_NOMBRE),
                resultSet.getString(DbScheme.COL_APELLIDO),
                resultSet.getString(DbScheme.COL_CORREO),
                resultSet.getString(DbScheme.COL_CONTRASEÑA)
            );

        }
        return this.usuario;

    }

    public void exportToObj(){
        ObjectOutputStream objectOutputStream = null;
        try{
            if (!file.exists()) {
                System.out.println("Creando fichero ");
                file.createNewFile();
            }
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));

            for (Usuario usuario: usuarioList){
                objectOutputStream.writeObject(usuario);
            }

        }  catch (IOException e) {
            throw new RuntimeException("Error de escritura: " + e.getMessage(), e);
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar");
            }
        }
    }


}
