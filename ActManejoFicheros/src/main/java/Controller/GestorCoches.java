package Controller;

import model.Coche;

import java.io.*;

import java.util.ArrayList;



public class GestorCoches {
    // Para este ejercicio, haremos que el 'path' sea un atributo ESTÁTICO y NO-MODIFICABLE de la clase
    private static final String path = "./src/main/resources/concesionario.obj";
    private static final String csvPath = "./src/main/resources/concesionario.csv";
    private static File file = null;
    private static File csvFile = null;

    // Declaramos un ArrayList para la clase Coche y una instancia individual de Coche -luego la usaremos.
    private ArrayList <Coche> concesionarioList = new ArrayList<>();
    private Coche coche;

    public GestorCoches(){
        init();
    }


    public void addNew(Coche cocheNuevo) {
        System.out.println("Se ha seleccionado la opción: Añadir coche");
        ObjectOutputStream objectOutputStream = null;

        for (Coche coche: concesionarioList){
            if (coche.getMatricula().equals(cocheNuevo.getMatricula())){
                System.out.println("El vehículo ya está registrado en el sistema");
                return;
            }
        }
        System.out.println("Añadiendo al concesionario: " + cocheNuevo);
        concesionarioList.add(cocheNuevo);
        // overWriteFile(); Aún no, solo al salir
    }


    // En lugar de iterar con un for, vamos a aprovechar el hashcode y el método contains para eliminar el objeto de la lista.
    public void deleteById(int id){
        System.out.println("Se ha seleccionado la opción: Borrar coche por id");
        if (concesionarioList.contains(new Coche(id) )){
            concesionarioList.remove(concesionarioList.indexOf(new Coche(id)));
            // overWriteFile(); Aún no, solo al salir
            return;
        }
        System.out.println("No existe ningún coche con esa identificación");

    }


    public void findById(int id){
        System.out.println("Se ha seleccionado la opción: Consultar por id");
        if (concesionarioList.contains(new Coche(id) )){
            int index = concesionarioList.indexOf(new Coche(id));
            coche = concesionarioList.get(index);
            System.out.println(coche.toString());
            return;
        }
        System.out.println("Búsqueda sin éxito, pruebe otra ID");
    }

    public void init(){
        file = new File(path);
        csvFile = new File(csvPath);

        System.out.println("Inicializando programa");
        ObjectInputStream objectInputStream = null;
        try{

            int maxId = 0;
            objectInputStream = new ObjectInputStream(new FileInputStream(file));

            if (file.exists()) {
                concesionarioList = (ArrayList<Coche>) objectInputStream.readObject();
                for (Coche item : concesionarioList) {
                    maxId = item.getId();
                }
                Coche.setNextId(maxId);
            }

        } catch (FileNotFoundException e) {
            System.err.println("La ruta del archivo de entrada no existe: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Error de clase: " + e.getMessage());
        }

    }

    public void findAll(){
        for (Coche item: concesionarioList){
            System.out.println("-" + item.toString());
        }

    }

    public void toCsv(){
        System.out.println("Se ha seleccionado la opción: Convertir a CSV");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvPath))) {
            if (!csvFile.exists()) {
                System.out.println("Creando fichero de CSV");
                csvFile.createNewFile();
            }
            writer.write("id, matricula, marca, modelo, color");
            writer.newLine();

            for (Coche coche : concesionarioList) {
                writer.write(coche.getId() + "," + coche.getMatricula() + "," + coche.getMarca() + ","
                        + coche.getModelo() + "," + coche.getColor());
                writer.newLine();
            }
            System.out.println("Datos convertidos a CSV exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
        }

    }

    public void overWriteFile(){
        ObjectOutputStream objectOutputStream = null;
        try{

            if (!file.exists()) {
                System.out.println("Creando fichero ");
                file.createNewFile();
            }
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(concesionarioList);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("No existe el archivo: " + e.getMessage(), e);
        } catch (IOException e) {
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

