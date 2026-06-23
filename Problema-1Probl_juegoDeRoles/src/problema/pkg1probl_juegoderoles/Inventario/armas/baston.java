package problema.pkg1probl_juegoderoles.Inventario.armas;

import java.util.ArrayList;

public class baston extends armas {
    private String elemento;

    private static final String[] ENCANTAMIENTOS_DISPONIBLES = {
        "Canalización I", "Lealtad III", "Maldición de Desaparición"
    };

    public baston(String nombre, String elemento, int danio) {
        super(0, new ArrayList<>(), nombre, danio, "Magia");
        this.elemento = elemento;
        this.durabilidad = 250;
    }

    public String getElemento() { return elemento; }
    public void setElemento(String elemento) { this.elemento = elemento; }

    @Override
    public int Desgaste() {
        if (durabilidad > 0) {
            durabilidad--;
        } else {
            System.out.println(nombre + " está roto, no se puede usar.");
        }
        return durabilidad;
    }

    @Override
    public String encantar(String encantamiento) {
        for (int i = 0; i < ENCANTAMIENTOS_DISPONIBLES.length; i++) {
            String e = ENCANTAMIENTOS_DISPONIBLES[i];
            if (e.equals(encantamiento) && !this.encantamiento.contains(e)) {
                this.encantamiento.add(e);
                return "Encantamiento aplicado: " + e;
            }
        }
        return "Encantamiento no válido.";
    }
}