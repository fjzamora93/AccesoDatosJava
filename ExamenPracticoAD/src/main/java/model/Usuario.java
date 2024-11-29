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
public class Usuario implements Serializable {
    private static final long serialVersionUID =  1L;

    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;





    @Override
    public String toString() {
        return "Usuario{" +
                " nombre='" + this.nombre + '\'' +
                ", apellido='" + this.apellido + '\'' +
                ", correo='" + this.correo + '\'' +
                ", contraseña='" + this.contraseña + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(correo, usuario.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(correo);
    }
}