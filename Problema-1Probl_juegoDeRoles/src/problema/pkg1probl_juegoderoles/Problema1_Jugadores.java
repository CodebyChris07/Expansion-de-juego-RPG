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

    // --- Sistema de Energía y Cooldown ---
    protected int energia;
    protected int energiaMaxima;
    protected int cooldownHabilidad; // turnos restantes hasta poder volver a usar la habilidad
    protected static final int COOLDOWN_BASE = 3; // turnos de espera tras usar la habilidad
    protected static final int COSTO_ENERGIA_BASE = 25; // costo por defecto de la habilidad especial

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

        this.energiaMaxima = 100;
        this.energia = energiaMaxima;
        this.cooldownHabilidad = 0;
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

    public int getEnergia() {
        return energia;
    }

    public int getEnergiaMaxima() {
        return energiaMaxima;
    }

    public void setEnergiaMaxima(int energiaMaxima) {
        this.energiaMaxima = energiaMaxima;
    }

    public int getCooldownHabilidad() {
        return cooldownHabilidad;
    }

    public boolean habilidadDisponible() {
        return cooldownHabilidad == 0;
    }

    /**
     * Recupera energía (por ejemplo, al final de cada turno) sin pasar
     * el máximo permitido.
     */
    public void recuperarEnergia(int cantidad) {
        this.energia = Math.min(energiaMaxima, this.energia + cantidad);
    }

    /**
     * Reduce en 1 el cooldown de la habilidad especial, sin bajar de 0.
     * Debe llamarse una vez por cada turno transcurrido.
     */
    public void avanzarTurno() {
        if (cooldownHabilidad > 0) {
            cooldownHabilidad--;
        }
        recuperarEnergia(5); // regeneración pasiva de energía por turno
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

    /**
     * Define el costo en energía que consume la habilidad especial de
     * este personaje. Cada subclase puede sobreescribirlo; por defecto
     * usa el costo base.
     */
    public int costoEnergiaHabilidad() {
        return COSTO_ENERGIA_BASE;
    }

    /**
     * Define cuántos turnos de cooldown deja la habilidad especial tras
     * usarse. Cada subclase puede sobreescribirlo; por defecto usa el
     * cooldown base.
     */
    public int duracionCooldownHabilidad() {
        return COOLDOWN_BASE;
    }

    /**
     * Efecto particular de la habilidad especial de cada tipo de
     * personaje (Guerrero, Mago, Arquero, etc). Debe devolver el daño
     * (o valor relevante) producido por la habilidad.
     */
    protected abstract int efectoHabilidadEspecial();

    /**
     * Intenta usar la habilidad especial del personaje. Valida que el
     * personaje tenga suficiente energía y que la habilidad no esté en
     * cooldown. Si ambas condiciones se cumplen, consume la energía,
     * activa el cooldown y ejecuta el efecto particular de la subclase.
     *
     * @return el resultado numérico del efecto de la habilidad (p. ej. daño)
     * @throws Problema1_SinEnergiaException si no hay energía suficiente
     *         o la habilidad todavía está en cooldown.
     */
    public int usarHabilidadEspecial() throws Problema1_SinEnergiaException {
        if (!habilidadDisponible()) {
            throw new Problema1_SinEnergiaException(
                    nombre + " no puede usar su habilidad especial: "
                    + "está en cooldown (" + cooldownHabilidad + " turno(s) restante(s)).");
        }

        int costo = costoEnergiaHabilidad();
        if (energia < costo) {
            throw new Problema1_SinEnergiaException(
                    nombre + " no tiene suficiente energía para usar su habilidad especial "
                    + "(necesita " + costo + ", tiene " + energia + ").");
        }

        energia -= costo;
        cooldownHabilidad = duracionCooldownHabilidad();
        return efectoHabilidadEspecial();
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Nombre: %s | Vida: %d | Nivel: %d | Arma: %s | Energía: %d/%d | Cooldown: %d",
                id, nombre, vida, nivelExperiencia, armas, energia, energiaMaxima, cooldownHabilidad);
    }

}
