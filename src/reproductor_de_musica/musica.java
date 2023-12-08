package reproductor_de_musica;

public class musica {
    
    String nombre, direccion;
    
    musica siguiente;
    musica anterior;

    public musica(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }
}
