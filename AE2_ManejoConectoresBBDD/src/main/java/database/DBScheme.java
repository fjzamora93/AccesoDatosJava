package database;

public interface DBScheme {

    // 1. conectar dos clases que no tienen nada que ver
    // con los metodos abs que tiene dicha interfaz

    // 2. Almacen de constantes variables -> finales (no de metodos)
    String DB_NAME = "concesionario";
    String HOST = "127.0.0.1";
    String PORT = "3306";
    String DATABASE = "concesionario";

    String COL_ID = "id";

    String TAB_COCHE = "coches";

    String COL_COCHE_MATRICULA= "matricula";
    String COL_COCHE_MARCA= "marca";
    String COL_COCHE_MODELO = "modelo";
    String COL_COCHE_COLOR = "color";

    //Los pasajeros tendrán los siguientes atributos, id, nombre, edad y peso.
    String TAB_PAS = "pasajeros";

    String COL_PAS_NOMBRE = "nombre";
    String COL_PAS_EDAD = "edad";
    String COL_PAS_PESO = "peso";


    String TAB_COCHE_PAS = "coche_pas";
    String COL_PAS_ID = "id";
    String COL_COCHE_ID = "id";


}
