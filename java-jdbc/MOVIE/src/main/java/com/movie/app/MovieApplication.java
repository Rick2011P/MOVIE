package com.movie.app;

import com.movie.model.Movie;
import com.movie.repository.MovieRepository;
import com.movie.service.MovieService;
import java.time.LocalDate;
import java.util.Scanner;

/** Aplicación de consola para demostrar el CRUD completo del módulo Películas. */
public class MovieApplication {
    private static final Scanner SC = new Scanner(System.in);
    private static final MovieService SERVICE = new MovieService(new MovieRepository());

    public static void main(String[] args) {
        int option;
        do {
            menu(); option = readInt("Opción: ");
            try { switch(option) { case 1 -> create(); case 2 -> list(); case 3 -> find(); case 4 -> update(); case 5 -> delete(); case 0 -> System.out.println("MOVIE finalizado."); default -> System.out.println("Opción no válida."); } }
            catch (Exception ex) { System.out.println("Error: " + ex.getMessage()); }
        } while(option!=0);
    }
    private static void menu(){ System.out.println("\n=== MOVIE | CRUD PELÍCULAS ===\n1. Crear\n2. Listar\n3. Buscar por ID\n4. Actualizar\n5. Eliminar\n0. Salir"); }
    private static void create() throws Exception { Movie m=readMovie(null); SERVICE.create(m); System.out.println("Creada con ID: "+m.getId()); }
    private static void list() throws Exception { SERVICE.list().forEach(System.out::println); }
    private static void find() throws Exception { int id=readInt("ID: "); System.out.println(SERVICE.find(id).map(Object::toString).orElse("No encontrada")); }
    private static void update() throws Exception { int id=readInt("ID a actualizar: "); Movie m=readMovie(id); System.out.println(SERVICE.update(m)?"Actualizada":"No encontrada"); }
    private static void delete() throws Exception { int id=readInt("ID a eliminar: "); System.out.println(SERVICE.delete(id)?"Eliminada":"No encontrada"); }
    private static Movie readMovie(Integer id){ System.out.print("Título: "); String t=SC.nextLine(); System.out.print("Descripción: "); String d=SC.nextLine(); int du=readInt("Duración (min): "); System.out.print("Fecha estreno (AAAA-MM-DD): "); LocalDate f=LocalDate.parse(SC.nextLine()); System.out.print("Clasificación: "); String c=SC.nextLine(); int g=readInt("ID género: "); int dir=readInt("ID director: "); return new Movie(id,t,d,du,f,c,g,dir); }
    private static int readInt(String label){ while(true){ System.out.print(label); try{return Integer.parseInt(SC.nextLine().trim());}catch(NumberFormatException e){System.out.println("Ingrese un número válido.");}} }
}
