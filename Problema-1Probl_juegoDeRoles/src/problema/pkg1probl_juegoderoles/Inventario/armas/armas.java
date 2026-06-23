package problema.pkg1probl_juegoderoles.Inventario.armas;

import java.util.ArrayList;

import problema.pkg1probl_juegoderoles.Inventario.objetos;

public abstract class armas extends objetos {
    protected int danio;
    protected String tipoDealcance;
    
    public armas(int durabilidad, ArrayList<String> encantamiento, String nombre, int danio, String tipoDealcance) {
        super(durabilidad, encantamiento, nombre);
        this.danio = danio;
        this.tipoDealcance = tipoDealcance;
    }
    public int getDanio() {
        return danio;
    }
    public void setDanio(int danio) {
        this.danio = danio;
    }
    public String getTipoDealcance() {
        return tipoDealcance;
    }
    public void setTipoDealcance(String tipoDealcance) {
        this.tipoDealcance = tipoDealcance;
    }
    
    
    
}
