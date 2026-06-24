package problema.pkg1probl_juegoderoles.Inventario.armadura;

import java.util.ArrayList;
import problema.pkg1probl_juegoderoles.Inventario.objetos;

public abstract class armadura extends objetos {
    protected int defensa;
    protected String material;

    public armadura(int durabilidad, ArrayList<String> encantamiento, String nombre, int defensa, String material) {
        super(durabilidad, encantamiento, nombre);
        this.defensa = defensa;
        this.material = material;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}