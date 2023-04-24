import java.io.File;
import java.util.Scanner;

public class AFD {
    private String[] alfabeto;
    private int estados = 0;
    private int[] finales = new int [1000];
    private int[][] tabla = new int[1000][1000];
    int simboloIndex = -1;
    String archivo;

    public AFD(String archivo) {
        this.archivo = archivo;
        leerAFD(archivo);
    }

    private boolean leerAFD(String archivo) {
        try {
            File arcAFD = new File(archivo);
            Scanner sc = new Scanner(arcAFD);
    
            alfabeto = sc.nextLine().split(",");
            for (int i = 0; i < alfabeto.length; i++) {
                for (int j = i + 1; j < alfabeto.length; j++) {
                    if (alfabeto[i].equals(alfabeto[j])) {
                        System.out.println("Error, no se puede repetir los caracteres");
                    }
                }
            }
    
            estados = Integer.parseInt(sc.nextLine());
    
            String[] linea = sc.nextLine().split(",");
            finales = new int[1000];
            for (int i = 0; i < linea.length; i++) {
                finales[i] = Integer.parseInt(linea[i]);
                if (finales[i] >= estados) {
                    System.out.println("Error, el estado final no se encuentra entre los estados");
                }
            }
    
            tabla = new int[estados][alfabeto.length];
            for (int i = 0; i < estados; i++) {
                String[] fila = sc.nextLine().split(",");
                for (int j = 0; j < alfabeto.length; j++) {
                    tabla[i][j] = Integer.parseInt(fila[j]);
                }
            }
    
            sc.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
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
        for (int i = 0; i < estadoSig; i++) {
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