package problema.pkg1probl_juegoderoles.Inventario;
import java.util.ArrayList;

public abstract class objetos {
protected int durabilidad;
protected ArrayList<String> encantamiento;
protected String nombre;



public objetos(int durabilidad, ArrayList<String> encantamiento, String nombre) {
    this.durabilidad = durabilidad;
    this.encantamiento = encantamiento;
    this.nombre = nombre;
}



public int getDurabilidad() {
    return durabilidad;
}



public void setDurabilidad(int durabilidad) {
    this.durabilidad = durabilidad;
}



public ArrayList<String> getEncantamiento() {
    return encantamiento;
}



public void setEncantamiento(ArrayList<String> encantamiento) {
    this.encantamiento = encantamiento;
}



public String getNombre() {
    return nombre;
}



public void setNombre(String nombre) {
    this.nombre = nombre;
}



public abstract int Desgaste();
public abstract String encantar();


    
}