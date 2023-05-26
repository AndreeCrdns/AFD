public class App {
    public static void main(String[] args) throws Exception {
        Lista lista = new Lista();
        lista.llenar("AFD");
        Archivos arch = new Archivos("prueba.txt");
        String[] palabraspruebas; // arreglo que guarda las palabras para imprimir
        do {
            palabraspruebas = arch.lineapalabra();
            if (palabraspruebas[0] == "Fin del recorrido del archivo") {
                System.out.println("Ya no hay nada para analizar");
                break;
            }
            for (int i = 0; i < palabraspruebas.length; i++) // empezando desde 0, hasta el final del arreglo
            {
                String AFD = lista.validarCad(palabraspruebas[i]); // manda llamar validarcad de lista y valida cada palabra con palabraspruebas[i]
                System.out.println(AFD); // token contiene la cadena de caracteres
            }
        } while (palabraspruebas[0] != "Fin del recorrido del archivo");
    }
}