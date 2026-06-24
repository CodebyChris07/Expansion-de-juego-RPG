package problema.pkg1probl_juegoderoles;

import java.io.Serializable;
import java.util.ArrayList;
import problema.pkg1probl_juegoderoles.Inventario.objetos;
import problema.pkg1probl_juegoderoles.Inventario.armas.armas;
import problema.pkg1probl_juegoderoles.Inventario.armadura.armadura;

public abstract class Problema1_Jugadores implements Serializable {

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
    protected int cooldownHabilidad;
    protected static final int COOLDOWN_BASE = 3;
    protected static final int COSTO_ENERGIA_BASE = 25;

    // Nuevos atributos
    protected ArrayList<IEstadoAlterado> estadoAlterado;
    protected boolean puedeAtacar;
    protected ArrayList<objetos> inventario;
    protected armas armaEquipada;
    protected armadura armaduraEquipada;

    public Problema1_Jugadores(int fuerza, int velocidad, String id, String nombre) {
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

        this.estadoAlterado = new ArrayList<>();
        this.puedeAtacar = true;
        this.inventario = new ArrayList<>();
        this.armaEquipada = null;
        this.armaduraEquipada = null;
    }

    public void agregarObjeto(objetos obj) {
        inventario.add(obj);
        System.out.println(obj.getNombre() + " añadido al inventario.");
    }

    public void equiparArma(armas arma) {
        if (inventario.contains(arma)) {
            this.armaEquipada = arma;
            System.out.println(nombre + " equipó " + arma.getNombre());
        } else {
            System.out.println("El arma no está en el inventario.");
        }
    }

    public void equiparArmadura(armadura arm) {
        if (inventario.contains(arm)) {
            this.armaduraEquipada = arm;
            System.out.println(nombre + " equipó " + arm.getNombre());
        } else {
            System.out.println("La armadura no está en el inventario.");
        }
    }

    // Métodos Abstractos correctos
    public abstract int calcularAtaque();

    public abstract int calcularDefensa();

    public abstract void subirNivel();

    protected abstract int efectoHabilidadEspecial();

    // Gestión de vida
    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    // Gestión de Estados Alterados
    public ArrayList<IEstadoAlterado> getEstados() {
        return estadoAlterado;
    }

    public void recibirEstados(IEstadoAlterado estadoNuevo) {
        estadoAlterado.add(estadoNuevo);
    }

    public boolean isPuedeAtacar() {
        return puedeAtacar;
    }

    public void setPuedeAtacar(boolean puedeAtacar) {
        this.puedeAtacar = puedeAtacar;
    }

    public void evaluarEstados() {
        for (int i = estadoAlterado.size() - 1; i >= 0; i--) {
            IEstadoAlterado estadoActual = estadoAlterado.get(i);
            estadoActual.aplicarEfecto(this);
            if (estadoActual.haTerminado()) {
                estadoAlterado.remove(i);
            }
        }
    }

    public boolean tieneEstado(Class<?> claseEstado) {
        for (IEstadoAlterado estado : estadoAlterado) {
            if (claseEstado.isInstance(estado)) {
                return true;
            }
        }
        return false;
    }

    // --- Métodos de Habilidades y Energía ---
    public boolean habilidadDisponible() {
        return cooldownHabilidad == 0;
    }

    public void recuperarEnergia(int cantidad) {
        this.energia = Math.min(energiaMaxima, this.energia + cantidad);
    }

    public void avanzarTurno() {
        if (cooldownHabilidad > 0) {
            cooldownHabilidad--;
        }
        recuperarEnergia(5);
    }

    public int costoEnergiaHabilidad() {
        return COSTO_ENERGIA_BASE;
    }

    public int duracionCooldownHabilidad() {
        return COOLDOWN_BASE;
    }

    public int usarHabilidadEspecial() throws Problema1_SinEnergiaException {
        if (!habilidadDisponible()) {
            throw new Problema1_SinEnergiaException(nombre + " no puede usar su habilidad especial.");
        }
        int costo = costoEnergiaHabilidad();
        if (energia < costo) {
            throw new Problema1_SinEnergiaException(nombre + " no tiene suficiente energía.");
        }
        energia -= costo;
        cooldownHabilidad = duracionCooldownHabilidad();
        return efectoHabilidadEspecial();
    }

    // Getters y Setters básicos
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = Math.max(vida, 0);
    }

    public int getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setNivelExperiencia(int n) {
        this.nivelExperiencia = n;
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

    @Override
    public String toString() {
        return String.format("ID: %s | Nombre: %s | Vida: %d | Nivel: %d | Arma: %s | Energía: %d/%d",
                id, nombre, vida, nivelExperiencia,
                armaEquipada != null ? armaEquipada.getNombre() : "ninguna", energia, energiaMaxima);
    }
}
