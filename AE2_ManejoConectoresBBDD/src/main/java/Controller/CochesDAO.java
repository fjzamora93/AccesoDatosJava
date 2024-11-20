package Controller;

import model.Coche;


/**  FUNCIONES DE UN CONTROLLER:

 1. Validar la información de entrada: Asegurarse de que los datos que ingresan cumplen
 con ciertos requisitos antes de ser enviados al DAO. Por ejemplo:

     -Marca: Validar que sea de una lista de marcas permitidas o no esté vacía.
    -Modelo: Verificar que el modelo no esté vacío o tenga una longitud válida.
      -Matrícula: Comprobar que la matrícula sigue un formato específico (por ejemplo, usando una expresión regular).

 2. Gestionar errores o excepciones: Si alguna validación falla,
 el controlador podría manejar la excepción o devolver un mensaje claro
 a la interfaz de usuario en lugar de dejar que el error llegue desde la base de datos.

 3. Coordinar flujos entre distintas entidades: En el caso de procesos que involucren más de una entidad
 (por ejemplo, asociar un Coche a un Pasajero), el controlador podría coordinar estas interacciones.

 4. Implementar lógica adicional si es necesario:
 Si hay alguna regla de negocio que dictamine cómo interactúan los objetos Coche
 o Pasajero (por ejemplo, que no se puedan duplicar matrículas),
 el controlador podría encargarse de verificar esas reglas.

*/

public class CochesDAO {

    public void addNew(Coche coche){
        System.out.println(coche.toString());
    }

    public void findById(int id){
        System.out.println("ID seleccionado:" + id);
    }

    public void findAll(){
        System.out.println("Buscar todos");
    }


    public void deleteById(int id){
        System.out.println("Borrando :" + id);
    }

}




