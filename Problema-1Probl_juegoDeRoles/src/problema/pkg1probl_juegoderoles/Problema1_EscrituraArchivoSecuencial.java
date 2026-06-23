/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Problema1_EscrituraArchivoSecuencial {

    private String nombreArchivo;
    private ObjectOutputStream salida;
    private Problema1_Usuario registroUsuario;
    private ArrayList<Problema1_Usuario> listaUsuarios;

    public Problema1_EscrituraArchivoSecuencial(String nombreArc) {
        nombreArchivo = nombreArc;
        establecerListaUsuarios();

        try {
            salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
            if (obtenerListaUsuarios().size() > 0) {
                for (int i = 0; i < obtenerListaUsuarios().size(); i++) {
                    establecerRegistroUsuario(obtenerListaUsuarios().get(i));
                    establecerSalida();
                }
            }
        } catch (IOException ioException) {
            System.err.println("Error al abrir el archivo.");
        }
    }

    public void establecerNombreArchivo(String n) {
        nombreArchivo = n;
    }

    public void establecerRegistroUsuario(Problema1_Usuario u) {
        registroUsuario = u;
    }

    public void establecerSalida() {
        try {
            salida.writeObject(registroUsuario);
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo.");
        }
    }

    public void establecerListaUsuarios() {
        Problema1_LecturaArchivoSecuencial l = new Problema1_LecturaArchivoSecuencial(obtenerNombreArchivo());
        l.establecerUsuarios();
        listaUsuarios = l.obtenerUsuarios();
    }

    public String obtenerNombreArchivo() {
        return nombreArchivo;
    }

    public ArrayList<Problema1_Usuario> obtenerListaUsuarios() {
        return listaUsuarios;
    }

    public ObjectOutputStream obtenerSalida() {
        return salida;
    }

    public void cerrarArchivo() {
        try {
            if (salida != null) {
                salida.close();
            }
        } catch (IOException ioException) {
            System.err.println("Error al cerrar el archivo.");
        }
    }

}
