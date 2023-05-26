public class Nodo { // clase nodo hace el espacio para guardar los datos

    private Token dato; // de la clase token se toma nombre y patron y se guarda en dato
    private Nodo sig; // de la clase nodo se crea objeto sig

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    public Nodo(String nombre, AFD patron) {
        this.dato = new Token(nombre, patron); // constructor para crear nuevo nodo para que el nodo tenga un token
    }

    public String validarC(String cadena) { 
        try {
            return dato.Validar(cadena);
        } catch (Exception e) { 
            return "ERROR";
        }
    }
}
