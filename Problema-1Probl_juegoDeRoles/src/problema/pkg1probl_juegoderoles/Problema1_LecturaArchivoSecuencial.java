/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Problema1_LecturaArchivoSecuencial {

    private String nombreArchivo;
    private ObjectInputStream entrada;
    private Problema1_Usuario registroUsuario;
    private ArrayList<Problema1_Usuario> usuarios;

    public Problema1_LecturaArchivoSecuencial(String nombreArc) {
        nombreArchivo = nombreArc;
        usuarios = new ArrayList<>();
    }

    public void establecerUsuarios() {
        try {
            entrada = new ObjectInputStream(new FileInputStream(nombreArchivo));
            boolean continuar = true;
            while (continuar) {
                try {
                    registroUsuario = (Problema1_Usuario) entrada.readObject();
                    usuarios.add(registroUsuario);
                } catch (EOFException eof) {
                    continuar = false;
                } catch (ClassNotFoundException cnf) {
                    System.err.println("Clase no encontrada.");
                    continuar = false;
                }
            }
            entrada.close();
        } catch (IOException ioException) {
            System.out.println("No se encontró el archivo, se creará uno nuevo.");
        }
    }

    public ArrayList<Problema1_Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public String obtenerNombreArchivo() {
        return nombreArchivo;
    }

}
