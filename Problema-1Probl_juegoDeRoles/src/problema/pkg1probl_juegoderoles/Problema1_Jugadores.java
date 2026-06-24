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
    protected int cooldownHabilidad; // turnos restantes hasta poder volver a usar la habilidad
    protected static final int COOLDOWN_BASE = 3; // turnos de espera tras usar la habilidad
    protected static final int COSTO_ENERGIA_BASE = 25; // costo por defecto de la habilidad especial



    public Problema1_Jugadores(String armas, int fuerza, int velocidad, String id, String nombre) {
        this.armas = armas;


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

    public abstract int calcularAtaque();

    public abstract int calcularDefensa();

    public abstract void subirNivel();

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

    // getters y setters
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
     * Recupera energía (por ejemplo, al final de cada turno) sin pasar el
     * máximo permitido.
     */
    public void recuperarEnergia(int cantidad) {
        this.energia = Math.min(energiaMaxima, this.energia + cantidad);
    }

    /**
     * Reduce en 1 el cooldown de la habilidad especial, sin bajar de 0. Debe
     * llamarse una vez por cada turno transcurrido.
     */
    public void avanzarTurno() {
        if (cooldownHabilidad > 0) {
            cooldownHabilidad--;
        }
        recuperarEnergia(5); // regeneración pasiva de energía por turno
    }

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
            // 3. Preguntamos si el estado ya caducó
            if (estadoActual.haTerminado()) {
                // Si devuelve true, lo eliminamos 
                estadoAlterado.remove(i);
            }
        }
    }

    public boolean tieneEstado(Class<?> claseEstado) {
        //Class<?> es un tipo de dato que representa metainformación sobre 
        //cualquier clase o interfaz en tiempo de ejecución
        for (IEstadoAlterado estado : estadoAlterado) {
            if (claseEstado.isInstance(estado)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Define el costo en energía que consume la habilidad especial de este
     * personaje. Cada subclase puede sobreescribirlo; por defecto usa el costo
     * base.
     */
    public int costoEnergiaHabilidad() {
        return COSTO_ENERGIA_BASE;
    }

    /**
     * Define cuántos turnos de cooldown deja la habilidad especial tras usarse.
     * Cada subclase puede sobreescribirlo; por defecto usa el cooldown base.
     */
    public int duracionCooldownHabilidad() {
        return COOLDOWN_BASE;
    }

    /**
     * Efecto particular de la habilidad especial de cada tipo de personaje
     * (Guerrero, Mago, Arquero, etc). Debe devolver el daño (o valor relevante)
     * producido por la habilidad.
     */
    protected abstract int efectoHabilidadEspecial();

    /**
     * Intenta usar la habilidad especial del personaje. Valida que el personaje
     * tenga suficiente energía y que la habilidad no esté en cooldown. Si ambas
     * condiciones se cumplen, consume la energía, activa el cooldown y ejecuta
     * el efecto particular de la subclase.
     *
     * @return el resultado numérico del efecto de la habilidad (p. ej. daño)
     * @throws Problema1_SinEnergiaException si no hay energía suficiente o la
     * habilidad todavía está en cooldown.
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
                id, nombre, vida, nivelExperiencia,
                armaEquipada != null ? armaEquipada.getNombre() : "ninguna",
                energia, energiaMaxima, cooldownHabilidad);


        return String.format("ID: %s | Nombre: %s | Vida: %d | Nivel: %d | Arma: %s | Energía: %d/%d | Cooldown: %d",
                id, nombre, vida, nivelExperiencia, armas, energia, energiaMaxima, cooldownHabilidad);

        return String.format("ID: %s | Nombre: %s | Vida: %d | Nivel: %d | Arma: %s",
                id, nombre, vida, nivelExperiencia,
                armaEquipada != null ? armaEquipada.getNombre() : "ninguna");


    }
}
