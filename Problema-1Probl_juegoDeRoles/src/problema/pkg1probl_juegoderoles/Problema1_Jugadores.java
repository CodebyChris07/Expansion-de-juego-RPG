/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public abstract class Problema1_Jugadores implements Serializable {

    protected String armas;
    protected int vida;
    protected int nivelExperiencia;
    protected int fuerza;
    protected int velocidad;
    protected int nivelAtaque;
    protected String id;
    protected String nombre;
    protected int nivelDefensa;

    public Problema1_Jugadores(String armas, int fuerza, int velocidad, String id, String nombre) {
        this.armas = armas;
        this.vida = 100;
        this.nivelExperiencia = 0;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.nivelAtaque = 5;
        this.id = id;
        this.nombre = nombre;
        this.nivelDefensa = 5;

    }

    public String getArmas() {
        return armas;
    }

    public void setArmas(String armas) {
        this.armas = armas;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = Math.max(vida, 0);
    }

    public int getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setNivelExperiencia(int nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getNivelAtaque() {
        return nivelAtaque;
    }

    public void setNivelAtaque(int nivelAtaque) {
        this.nivelAtaque = nivelAtaque;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelDefensa() {
        return nivelDefensa;
    }

    public void setNivelDefensa(int nivelDefensa) {
        this.nivelDefensa = nivelDefensa;
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public boolean mejorarAtaque() {
        int costo = nivelAtaque;
        if (nivelExperiencia < costo) {
            return false;
        }
        nivelAtaque++;
        nivelExperiencia -= costo;
        return true;
    }

    public boolean mejorarDefensa() {
        int costo = nivelDefensa;
        if (nivelExperiencia < costo) {
            return false;
        }
        nivelDefensa++;
        nivelExperiencia -= costo;
        return true;
    }

    public abstract int ataque();

    public abstract int defensa();

    public abstract void subirNivel();

    @Override
    public String toString() {
        return String.format("ID: %s | Nombre: %s | Vida: %d | Nivel: %d | Arma: %s", id, nombre, vida, nivelExperiencia, armas);
    }

}
