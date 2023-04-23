import java.io.File;
import java.util.Scanner;

public class AFD {
    private String[] alfabeto;
    private int estados;
    private int[] finales;
    private int[][] tabla;
    int simboloIndex = 0;
    String archivo;

    public AFD(String archivo) {
        this.archivo = archivo;
        Rellenar(archivo);
    }

    private boolean Rellenar(String archivo) {
        try {
            File arcAFD = new File(archivo);
            Scanner sc = new Scanner(arcAFD);

            alfabeto = sc.nextLine().split(", ");
            for (int i = 0; i < alfabeto.length; i++) {
                for (int j = i + 1; j < alfabeto.length; i++) {
                    if (alfabeto[i].equals(alfabeto[j])) {
                        System.out.println("Error, no se puede repetir los caracteres");
                        System.out.println(0);
                    }
                }
            }
            try {
                estados = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Error al leer el numero de estados");
            }

            String[] linea = sc.nextLine().split(", ");
            finales = new int[100];
            for (int i = 0; i < linea.length; i++) {
                finales[i] = Integer.parseInt(linea[i]);
                if (finales[i] >= estados) {
                    System.out.println("Error, el estado final no se encuentra entre los estados");
                }
            }
            tabla = new int[100][100];
            while (sc.hasNext()) {
                try {
                    for (int i = 0; i < estados; i++) {
                        for (int j = 0; j < alfabeto.length; j++) {
                            tabla[i][j] = Integer.parseInt(sc.next());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error en la tabla de transicion");
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }
        return true;
    }

    public boolean pertenece(String cadena) {
        int estadoAct = 0;
        int estadoSig = 0;
        boolean bandera = false;
        boolean bandera2 = false;
        boolean match = false;
        String[] cadenaArr = cadena.split(" ");

        for (int i = 0; i < cadenaArr.length; i++) {
            match = false;
            for (int j = 0; j < alfabeto.length; j++) {
                if (alfabeto[j].equals(cadenaArr[i])) {
                    match = true;
                }
            }
            if (match == false) {
                return false;
            }

        }
        for (int i = 0; i < cadenaArr.length; i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                if (alfabeto[j].equals(cadenaArr[i])) {
                    simboloIndex = j;
                }
            }
            estadoSig = tabla[estadoAct][estadoSig];
            estadoAct = estadoSig;
        }
        for (int l = 0; l < finales.length; l++) {
            if (estadoAct == finales[l]) {
                bandera = true;
            } else {
                bandera2 = true;
            }
        }
        if (bandera == true) {
            return true;
        }
        if (bandera2 == true && bandera != true) {
            return false;
        }
        return true;
    }
}