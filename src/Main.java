import agenda.*;
import agendaviews.MainForm;

import java.time.LocalDate;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class Main{

    private static ArrayList<Class> classesCargadas = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        //crear agenda
        Agenda agenda1 = new Agenda();

        //crear contactos y agregarlos a la agenda
        agenda1.agregarContacto(new ContactoFamiliar(
                "Juan Pérez",
                35,
                1,
                "8888-1111",
                101010101,
                "Padre"
        ));

        agenda1.agregarContacto(new ContactoFamiliar(
                "María López",
                42,
                2,
                "8888-2222",
                202020202,
                "Hermana"
        ));

        agenda1.agregarContacto(new ContactoNegocios(
                "Carlos Rodríguez",
                50,
                1,
                "8888-3333",
                303030303,
                "carlos@empresa.com"
        ));

        agenda1.agregarContacto(new ContactoFamiliar(
                "Ana Vargas",
                28,
                2,
                "8888-4444",
                404040404,
                "Prima"
        ));

        agenda1.agregarContacto(new ContactoNegocios(
                "Luis González",
                45,
                1,
                "8888-5555",
                505050505,
                "luis@negocios.com"
        ));
        agenda1.agregarEvento(new EventoReunion(
                LocalDate.of(2026,11,15),
                "El evento del año",100));

        agenda1.agregarEvento(new EventoSocial(
                LocalDate.now(),
                "Cumpleaños de Alguien",
                "París"));
        //imprimir agenda
        System.out.println(agenda1);

        //modificar un contacto de agenda
        agenda1.modificarContactoFamiliar(101010101,"Ana María Vargas",29,2,"888-4444","Prima");
        agenda1.modificarContactoFamiliar(404040404,"Ana María Vargas",29,2,"888-4444","Prima");

        //eliminar contacto
        agenda1.eliminarContacto(101010101);
        agenda1.eliminarContacto(606060606);


        //voy a agregar nuevos objetos de forma dinámica
        //cargarClases();
        //voy a instanciar las nuevas clases
        for (Class c:classesCargadas) {
            if (ElementoAgenda.class.isAssignableFrom(c)
                    && !c.isInterface()
                    && !java.lang.reflect.Modifier.isAbstract(
                    c.getModifiers())) {

                ElementoAgenda elemento =
                        (ElementoAgenda) c.getDeclaredConstructor().newInstance();
                agenda1.agregarEvento((Evento) elemento);
            }
        }
        //agregar Eventos
        //imprimir agenda final
        System.out.println(agenda1);

        MainForm startForm = new MainForm(agenda1);

    }

    public static void cargarClases() throws Exception {
        File carpeta = new File("ElementosAgendaClasses");

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            throw new IllegalArgumentException(
                    "No existe la carpeta: " + carpeta.getAbsolutePath()
            );
        }

        URL[] urls = { carpeta.toURI().toURL() };

        try (URLClassLoader loader =
                     new URLClassLoader(urls, Main.class.getClassLoader())) {

            File[] archivos = carpeta.listFiles(
                    (dir, nombre) -> nombre.endsWith(".class")
            );

            if (archivos == null) {
                return;
            }

            for (File archivo : archivos) {
                String nombreClase = archivo.getName()
                        .replaceFirst("\\.class$", "");

                Class<?> clase = loader.loadClass(nombreClase);
                classesCargadas.add(clase);

            }
        }
    }

}
