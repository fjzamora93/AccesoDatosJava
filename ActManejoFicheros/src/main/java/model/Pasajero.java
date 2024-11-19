
//Tiene que terner id, nombre, edad  y peso

package model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pasajero {
    private int id;
    private String nombre;
    private int edad;
    private int peso;

    public Pasajero(String nombre, int edad, int peso) {
        this.peso = peso;
        this.edad = edad;
        this.nombre = nombre;
    }


    public void mostrarDatos(){
        System.out.println("Nombre = " + this.nombre);
        System.out.println("Edad = " + this.edad);
        System.out.println("Peso = " + this.peso);

    }
}