import java.io.File;

public class Lista { // crea la lista

    Nodo raiz;
    Nodo fin;

    // metodos, llenar, insertar, vaciar, vacio, validarlexema

    public boolean vacio() {
        return raiz == null;
    }

    public void vaciar() {
        raiz = null;
    }

    public void insertar(String nombre, AFD patron) //
    {
        Nodo nuevo = new Nodo(nombre, patron); // creo nuevo nodo
        if (vacio()) {
            raiz = nuevo; // primer nodo
            fin = nuevo;
        } else {
            fin.setSig(nuevo); 
            fin = fin.getSig();
        }
    }

    public String validarCad(String cadena) // recorre la lista hasta que encuentre el token al que le corresponde la
                                            // cadena
    { // error saldria si la lista estuviera vacia
        String valid = null; // valid hace funcion del nombre del token
        if (!vacio()) {
            Nodo aux = raiz;
            do {
                valid = aux.validarC(cadena);
                aux = aux.getSig();
            } while (aux != null && valid == "ERROR");
            return valid;
        } else {
            return "Lista de AFD vacia";
        }
    }

    public void llenar(String nombreCarpeta) // lee el archivo y llena la lista de tokens
    {
        File carpeta = new File(nombreCarpeta);
        File[] archivos = carpeta.listFiles();

        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().endsWith(".txt")) {
                try {
                    String nombre = archivo.getName().substring(0, archivo.getName().lastIndexOf("."));
                    AFD afd = new AFD(archivo.toString());
                    this.insertar(nombre, afd);
                } catch (Exception e) {
                    System.out.println("Error en el llenado de la lista");
                }
            }
        }
    }
}
