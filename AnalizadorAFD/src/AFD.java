import java.io.File;
import java.util.Scanner;

public class AFD {
    private char[] alfabeto;
    private int estados = 0;
    private int[] finales;
    private int[][] tabla;
    int simboloIndex = 0;
    String archivo;

    public AFD(String archivo) {
        this.archivo = archivo;
        leerAFD(archivo);
    }

    private boolean leerAFD(String archivo) {
        try {
            File arcAFD = new File(archivo);
            Scanner sc = new Scanner(arcAFD);

            alfabeto = sc.nextLine().replaceAll(" ", "").toCharArray();
            for (int i = 0; i < alfabeto.length; i++) {
                for (int j = i + 1; j < alfabeto.length; j++) {
                    if (alfabeto[i] == alfabeto[j]) {
                        System.out.println("Error, no se puede repetir los caracteres");
                    }
                }
            }

            estados = Integer.parseInt(sc.nextLine());

            String[] linea = sc.nextLine().split(" ");
            finales = new int[linea.length];
            for (int i = 0; i < linea.length; i++) {
                finales[i] = Integer.parseInt(linea[i]);
                if (finales[i] >= estados) {
                    System.out.println("Error, el estado final no se encuentra entre los estados");
                }
            }

            tabla = new int[estados][alfabeto.length];
            for (int i = 0; i < estados; i++) {
                String lineanew = sc.nextLine();
                if (lineanew != null) {
                    String[] fila = lineanew.split(" ");
                    for (int j = 0; j < alfabeto.length; j++) {
                        tabla[i][j] = Integer.parseInt(fila[j]);
                    }

                } else {
                    System.out.println("Linea nula");
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public boolean pertenece(String cadena) {
        int estadoAct = 0;
        int estadoSig = 0;
        boolean bandera = false;
        boolean bandera2 = false;
        boolean match = false;
        char[] cadenaArr = cadena.replaceAll(" ", "").toCharArray();
        for (int i = 0; i < cadenaArr.length; i++) {
            match = false;
            for (int j = 0; j < alfabeto.length; j++) {
                if (alfabeto[j] == cadenaArr[i]) {
                    match = true;
                    estadoSig = j;
                }
            }
            if (match == false) {
                return false;
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
    public int getEstados() {
        return estados;
    }
    public int[][] getTabla() {
        return tabla;
    }
}