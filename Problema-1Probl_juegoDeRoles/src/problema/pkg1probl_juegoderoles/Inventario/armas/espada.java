package problema.pkg1probl_juegoderoles.Inventario.armas;

import java.util.ArrayList;

public class espada extends armas {
    private String material;
    private static final String[] ENCANTAMIENTOS_DISPONIBLES = {
            "Filo V", "Aspecto Ígneo II", "Saqueo III"
    };

    public espada(int durabilidad, ArrayList<String> encantamiento, String nombre, int danio, String tipoDealcance,
            String material) {
        super(0, new ArrayList<>(), nombre, danio, tipoDealcance);
        this.material = material;
        switch (material) {
            case "madera":
                this.durabilidad = 59;
                break;
            case "hierro":
                this.durabilidad = 250;
                break;
            case "diamante":
                this.durabilidad = 1561;
                break;
            case "netherita":
                this.durabilidad = 2031;
                break;
            default:
                this.durabilidad = 250;
                break;
        }
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public int Desgaste() {
        if (durabilidad > 0) {
            durabilidad--;
        } else {
            System.out.println(nombre + " está rota, no se puede usar.");
        }
        return durabilidad;
    }

    @Override
    public String encantar(String encantamiento) {
        for (int i = 0; i < ENCANTAMIENTOS_DISPONIBLES.length; i++) { 
            String e = ENCANTAMIENTOS_DISPONIBLES[i] ;
            if(e.equals(encantamiento)&&!this.encantamiento.contains(e)){
                this.encantamiento.add(e);
                return "has aplicado " + e + " a tu espada";

            }
        }
        return null;
    }

}
