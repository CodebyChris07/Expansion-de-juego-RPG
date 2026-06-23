/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Problema1_Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreUsuario;
    private String id;
    private ArrayList<Problema1_Jugadores> equipo;

    public Problema1_Usuario(String nombreUsuario, String id) {
        this.nombreUsuario = nombreUsuario;
        this.id = id;
        this.equipo = new ArrayList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Problema1_Jugadores> getEquipo() {
        return equipo;
    }

    public void agregarPersonaje(Problema1_Jugadores personaje) {

        if (equipo.size() < 5) {
            equipo.add(personaje);
            System.out.println(personaje.getNombre() + " agregado al equipo de " + nombreUsuario);
        } else {
            System.out.println("El equipo ya está lleno.");
        }
    }

    public void mostrarPersonajes() {
        if (equipo.isEmpty()) {
            System.out.println(nombreUsuario + " no tiene personajes.");
        } else {
            System.out.println("Equipo de " + nombreUsuario + ":");
            for (Problema1_Jugadores j : equipo) {
                System.out.println(j.toString());
            }
        }

    }
}
