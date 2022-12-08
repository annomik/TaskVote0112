package by.it_academy.jd2.MJD29522.dao;


import java.util.ArrayList;
import java.util.List;

public class GenreDaoSingleton  {

   private volatile static  GenreDao instance = null;
   static List<Genre> genres = new ArrayList<Genre>();

   public static GenreDao getInstance(){
      if(instance == null){
         synchronized (GenreDaoSingleton.class){
            if(instance == null){
               instance = new GenreDao();
               genres.add(new Genre("Classic"));
               genres.add(new Genre("Soul"));
               genres.add(new Genre("Rock"));
               genres.add(new Genre("Folk"));
               genres.add(new Genre("Opera"));
               genres.add(new Genre("Rumba"));
               genres.add(new Genre("Jazz"));
               genres.add(new Genre("Electric"));
               genres.add(new Genre("Blues"));
               genres.add(new Genre("Disco"));
            }
         }
      }
      return instance;
   }
}