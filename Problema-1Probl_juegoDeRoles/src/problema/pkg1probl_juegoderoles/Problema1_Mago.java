package problema.pkg1probl_juegoderoles;

import problema.pkg1probl_juegoderoles.Inventario.armas.baston;

public class Problema1_Mago extends Problema1_Jugadores {

    private int mana;
    private String afinidad;
    private baston baston;

    public Problema1_Mago(int mana, String afinidad, int fuerza, int velocidad, String id, String nombre,
            baston baston) {
        super(fuerza, velocidad, id, nombre);
        this.mana = mana;
        this.afinidad = afinidad;
        this.baston = baston;
        agregarObjeto(baston);
        equiparArma(baston);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getAfinidad() {
        return afinidad;
    }

    public void setAfinidad(String afinidad) {
        this.afinidad = afinidad;
    }

    @Override
    public int calcularAtaque() {
        int danioArma = 0;
        if (armaEquipada != null) {
            danioArma = armaEquipada.getDanio();
            armaEquipada.Desgaste();
        }
        int danioBase = nivelAtaque + (fuerza / 2) + danioArma;
        if (afinidad.equalsIgnoreCase("Fuego")) {
            return danioBase += 15;
        } else if (afinidad.equalsIgnoreCase("Agua")) {
            return danioBase += 5;
        } else if (afinidad.equalsIgnoreCase("Tierra")) {
            return danioBase += 8;
        } else if (afinidad.equalsIgnoreCase("Aire")) {
            return danioBase += 12;
        } else {
            return danioBase += 2;
        }
    }

    @Override
    public int calcularDefensa() {
        int defensaArmadura = 0;
        if (armaduraEquipada != null) {
            defensaArmadura = armaduraEquipada.getDefensa();
        }
        int defensaBase = nivelDefensa + (velocidad / 3) + defensaArmadura;
        if (afinidad.equalsIgnoreCase("Fuego")) {
            return defensaBase += 2;
        } else if (afinidad.equalsIgnoreCase("Agua")) {
            return defensaBase += 12;
        } else if (afinidad.equalsIgnoreCase("Tierra")) {
            return defensaBase += 15;
        } else if (afinidad.equalsIgnoreCase("Aire")) {
            return defensaBase += 5;
        } else {
            return defensaBase += 2;
        }
    }

    @Override
    public void subirNivel() {
        nivelExperiencia++;
        vida += 15;
        mana += 30;
    }

    @Override
    public int costoEnergiaHabilidad() {
        return 40;
    }

    @Override
    public int duracionCooldownHabilidad() {
        return 4;
    }

    /**
     * Habilidad especial del Mago: "Ráfaga Elemental".
     * Inflige daño mágico potenciado según su afinidad y consume maná
     * además de energía.
     */
    @Override
    protected int efectoHabilidadEspecial() {
        int danioBase = (nivelAtaque + fuerza) * 2;
        int bonusElemental;
        if (afinidad.equalsIgnoreCase("Fuego")) {
            bonusElemental = 25;
        } else if (afinidad.equalsIgnoreCase("Agua")) {
            bonusElemental = 10;
        } else if (afinidad.equalsIgnoreCase("Tierra")) {
            bonusElemental = 15;
        } else if (afinidad.equalsIgnoreCase("Aire")) {
            bonusElemental = 20;
        } else {
            bonusElemental = 5;
        }

        int costoMana = 20;
        if (mana >= costoMana) {
            mana -= costoMana;
        } else {
            bonusElemental /= 2; // sin suficiente maná, el efecto se debilita
        }

        int danioTotal = danioBase + bonusElemental;
        System.out.println(nombre + " invoca una ¡RÁFAGA DE " + afinidad.toUpperCase()
                + "! (" + danioTotal + " de daño mágico)");
        return danioTotal;
    }

    @Override
    public String toString() {
        return "[Mago de " + afinidad + "] " + super.toString() + " | Mana: " + mana;
    }
}