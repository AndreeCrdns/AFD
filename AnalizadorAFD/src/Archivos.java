import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Archivos {

   FileReader lector;
   BufferedReader leerlinea;

   public Archivos(String nombreArchivo) {
      try {
         this.lector = new FileReader(nombreArchivo); // se crea objeto que lee el archivo
         this.leerlinea = new BufferedReader(lector); // se crea el objeto leerlinea que lee el archivo linea por linea
      } catch (IOException e) {
         System.out.println("Error, no se pudo leer el archivo"); // si no se puede leer el archivo se lanza la
                                                                  // excepción
      }
   }

   public String[] lineapalabra() // Lee las palabras y las separa en un arreglo, hay restrincciones con #, si
                                  // esta vacia la liena y si es una cadena
   {
      String linea;
      try {
         while ((linea = leerlinea.readLine()) != null) // Mientras linea sea diferente de null, se va a imprimir la
                                                        // linea
         {
            if (linea.trim().startsWith("#") || linea.isEmpty()) {
               continue;
            }
            if (linea.trim().replaceAll("\\t", "").isEmpty()) {
               continue;
            }
            String[] palabras = linea.trim().split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            return palabras;
         }
         leerlinea.close();
         String[] mensaje = { "Fin del recorrido del archivo" };
         return mensaje;
      } catch (Exception e) {
         String[] mensaje = { "Error de entradas y salidas" };
         return mensaje;
      }
   }
}
