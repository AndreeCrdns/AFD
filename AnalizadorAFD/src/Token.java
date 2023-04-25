public class Token { // validar si una cadena cumple con el patron especifico

    private String nombre;
    private AFD patron;

    public Token(String nombre, AFD patron) { // constructor que inicializa las variables de instancia
        this.nombre = nombre;
        this.patron = patron;
    }

    public String Validar(String cadena) throws Exception { // valida la cadena, coincida con los txt de los automatas
        if (patron.pertenece(cadena)) {
            return nombre;
        } else {
            throw new Exception("No es compatible (AFD)");
        }
    }
}