package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
abstract class Persona {
    protected double rating;
    protected int age;
    protected String name;
    protected String gender;
    protected String email;
    protected String phone;

    // Método abstracto que las clases hijas deberán implementar
    public abstract void mostrarDetalles();
}