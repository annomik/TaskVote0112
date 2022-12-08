package by.it_academy.jd2;

import by.it_academy.jd2.MJD29522.comparator.*;

import by.it_academy.jd2.MJD29522.dao.Genre;
import by.it_academy.jd2.MJD29522.dao.Singer;
import by.it_academy.jd2.MJD29522.models.*;

import java.util.ArrayList;
import java.util.Map;

public class Service {
   public static Service service;
   private ArrayList<Genre> genres = new ArrayList<>();

   private ArrayList<Singer> singers = new ArrayList<>();
   private ArrayList<User> users = new ArrayList<>();

   GenreComparator genreComparator = new GenreComparator();
   SingerComparator singerComparator = new SingerComparator();
   UserComparator userComparator = new UserComparator();

   private Service(){

   }

   public static Service getService(){
      if(service == null){
         service = new Service();
         service.genres.add(new Genre("Classic"));
         service.genres.add(new Genre("Soul"));
         service.genres.add(new Genre("Rock"));
         service.genres.add(new Genre("Folk"));
         service.genres.add(new Genre("Opera"));
         service.genres.add(new Genre("Rumba"));
         service.genres.add(new Genre("Jazz"));
         service.genres.add(new Genre("Electric"));
         service.genres.add(new Genre("Blues"));
         service.genres.add(new Genre("Disco"));
         service.singers.add(new Singer("Тимати"));
         service.singers.add(new Singer("Madonna"));
         service.singers.add(new Singer("Shakira"));
         service.singers.add(new Singer("Цой"));
      }
      return service;
   }
   public String vote(Map<String, String[]> paramMap) {
      String[] singersParams = paramMap.get("singer");
      String result = "";
      if (singersParams.length == 1) {
         for (int i = 0; i < singers.size(); i++) {
            if (service.singers.get(i).getName().equals(singersParams[0])) {
               service.singers.get(i).addCountVote();
               result = result + "<p>Спасибо за Ваш голос в наминации лучшего исполнителя!</p>";
            }
         }
      } else {
         result = "<p>Необходимо проголосовать за одного исполнителя! Повторите голосование!</p>";
      }

      String[] genresParams = paramMap.get("genre");
      if (genresParams.length > 2 && genresParams.length <= service.genres.size()) {
         for (int i = 0; i < genresParams.length; i++) {
            for (Genre genre : service.genres) {
               if (genre.getName().equals(genresParams[i])) {
                  genre.addCount();
               }
            }
            if (i == (genresParams.length - 1)) {
               result = result + "<p>Спасибо за Ваше мнение о любимых жанрах!</p>";
            }
         }
      } else result = result + "Необходимо отдать от 3 голосов до " + service.genres.size() + " в номинации жанров!";

      String[] userInformations = paramMap.get("userInfo");
      String userInfo = "";
      for (String userInformation : userInformations) {
         userInfo = userInfo + userInformation + " ";
      }

      String[] userNames = paramMap.get("userName");
      if(userNames.length == 1){
         service.users.add(new User(userNames[0],userInfo));
      }
      return result;
   }

   public String viewSinger(){
      StringBuilder stringBuilder = new StringBuilder();
      ArrayList<Singer> sortSingers = new ArrayList<>();
      sortSingers = singers;
      sortSingers.sort(singerComparator);
      for(Singer sortSinger : sortSingers){
         stringBuilder.append("<p>");
         stringBuilder.append(sortSinger.toString());
         stringBuilder.append("</p>");
      }
      return stringBuilder.toString();
   }

   public String viewGenres(){
      StringBuilder stringBuilder = new StringBuilder();
      ArrayList<Genre> sortGenres = new ArrayList<>();
      sortGenres = genres;
      sortGenres.sort(genreComparator);
      for(Genre sortGenre : sortGenres ){
         stringBuilder.append("<p>");
         stringBuilder.append(sortGenre.toString());
         stringBuilder.append("</p>");

      }
      return stringBuilder.toString();
   }

   public String viewUsers(){
      if(users!=null){
         StringBuilder stringBuilder = new StringBuilder();
         ArrayList<User> sortUsers = new ArrayList<>();
         sortUsers = users;
         sortUsers.sort(userComparator);
         for(User user : users){
            stringBuilder.append("<p>");
            stringBuilder.append(user.toString());
            stringBuilder.append("</p>");
         }
         return stringBuilder.toString();
      } else {
         return "Нет пользователей";
      }
   }
   public String getSingers(){
      String result = "";
      for (Singer singer : singers) {
         result = result + "<p>Исполнитель: " + singer.getName() + "</p>";
      }
      return result;
   }

   public String getGenres(){
      String result = "";
      for (Genre genre : genres) {
         result = result + "<p>Жанр: " + genre.getName() + "</p>";
      }
      return result;
   }
}
