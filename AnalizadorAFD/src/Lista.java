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
            return "Lista de tokens vacia";
        }
    }
}
