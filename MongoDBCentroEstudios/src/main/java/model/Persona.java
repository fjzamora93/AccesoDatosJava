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
    double rating;
    int age;
    String name;
    String gender;
    String email;
    String phone;

    // Método abstracto que las clases hijas deberán implementar
    public abstract void mostrarDetalles();
}