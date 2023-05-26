import java.io.File;
import java.util.Scanner;

public class AFD {
    private char[] alfabeto;
    private int estados = 0;
    private int[] finales;
    private int[][] tabla; //matriz de enteros de dos dimensiones
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

            alfabeto = sc.nextLine().replaceAll(" ", "").toCharArray(); /*lee la primera linea del archivo y almacena
            el contenido de este en un array de caracteres
            Verfica si hay caracteres repetidos en el alfabeto de AFD*/
            for (int i = 0; i < alfabeto.length; i++) { // Aqui se asegura que itera sobre los caracteres del alfabeto
                for (int j = i + 1; j < alfabeto.length; j++) { 
                    if (alfabeto[i] == alfabeto[j]) { // Y si son iguales imprime un Error
                        System.out.println("Error, no se puede repetir los caracteres");
                    }
                }
            }
            estados = Integer.parseInt(sc.nextLine()); // Lee el numero de estados y lo guarda en parseInt, es decir, enteros

            String[] linea = sc.nextLine().split(" "); /*Lee la primera linea y la separa por espacios y los
            almacena en un array de tipo String*/
            finales = new int[linea.length]; // Crea un array de enteros con la longitud igual a la longitud del array anterior
            for (int i = 0; i < linea.length; i++) { // itera sobre los elementos del array linea
                finales[i] = Integer.parseInt(linea[i]); // convierte los elementos del array linea a int utilizando el metodo parseInt
                if (finales[i] >= estados) { /*Se verifica si el elemento del array final esta dentro del rango de
                estados del afd y sino imprime error.*/
                    System.out.println("Error, el estado final no se encuentra entre los estados");
                }
            }

            tabla = new int[estados][alfabeto.length]; /*Se crea una nueva matriz de tipo entero con un tamaño de el
            numero de estados filas y la longitud del alfabeto como columnas*/
            for (int i = 0; i < estados; i++) { // itera sobre cada fila de la matriz
                String lineanueva = sc.nextLine(); /*en cada iteracion se lee una nueva linea del archivo y se almacena en
                la variable lineanueva*/
                if (lineanueva != null) { // si la linea no es nula
                    String[] fila = lineanueva.split(" "); /*Se utiliza el metodo split para separar los elementos de la
                    linea por espacios y almacenarlos en un array de tipo String*/
                    for (int j = 0; j < alfabeto.length; j++) { // Se itera sobre cada elemento de la columna
                        tabla[i][j] = Integer.parseInt(fila[j]); /*Se convierte cada elemento de tipo string a int,
                    cada elmento convertido se almacena en la posicion correspondiente de la matriz*/
                    }
                } else {
                    System.out.println("Linea nula");
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error en la lectura del AFD");
            return false;
        }
        return true; // Si se pudo leer y almacenar corectamente en las variables estados, finales, alfabeto y tabla regresa true
    }
    public boolean pertenece(String cadena) {
        int estadoAct = 0;
        int columna = 0;
        boolean match = false;
        char[] cadenaPruebas = cadena.replaceAll(" ", "").toCharArray();
        //Verfica que la cadena empiece con el estado inicial
        for (int i = 0; i < cadenaPruebas.length; i++) { 
            for (int j = 0; j < alfabeto.length; j++) { //Se recorre el arreglo alfabeto y se compara 
                if (alfabeto[j] == cadenaPruebas[i]) { //Si el caracter actual de la cadena de entrada es igual al del caracter alfabeto
                    match = true; //Se establece la variable match a true
                    columna = j; //Se asigna el estado siguiente con la variable j 
                }
            }
            if (match == false) {
                return false;
            }
            columna = tabla[estadoAct][columna]; //
            estadoAct = columna;

        }
        //Se comprueba si el estado actual concide con un estado final si es asi regresa un true que significa que coincide o pertenece
        for (int l = 0; l < finales.length; l++) {
            if (estadoAct == finales[l]) { //si el estado final coincide da true
                return true;
            } 
        }
        return false;
}
}