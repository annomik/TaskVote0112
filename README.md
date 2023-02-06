# Team3. task1. Voting 
Приложение предназначено для проведения голосования по двум номинациям:
1. Singer - можно проголосовать только за одного артиста.
2. Genres - можно проголосовать за  3 - 5 жанров.

Также есть возможность просмотреть статистику голосования.
```sh
Команда проекта:  Дмитрий Булавицкий, Павел Клименко, Анна Микулич
```
В приложении предусмотрены следующие странцы и функции:
 <b><p>1. Страница приветствия</b></p>
```sh
 (GET) http://host:port/WarFileName/welcome
  ```
 <b><p>2. Страница артистов</b></p>
На данной странице предусмотрено выполнения следующих действий:
 Просмотреть  список исполнителей: 
```sh
 (GET) http://host:port/WarFileName/singer
  ```
  Добавить, обновить, удалить исполнителя:
```sh
 (POST)   http://host:port/WarFileName/singer?add=(singer_name) 
 (PUT)    http://host:port/WarFileName/singer?updateId=(singer_id)&newName=(new_singer_name)
 (DELETE) http://host:port/WarFileName/singer?deleteId=(singer_id)
  ```
 <b><p>3. Страница жанров</b></p>
URL "/genre"
На данной странице предусмотрено выполнения следующих действий:
  Просмотреть список жанров: 
 ```sh
 (GET) http://host:port/WarFileName/genre
  ```
 - Добавить, обновить, удалить жанр
  ```sh
 (POST)   http://host:port/WarFileName/genre?add=(genre_name) 
 (PUT)    http://host:port/WarFileName/genre?updateId=(genre_id)&newName=(new_genre_name)
 (DELETE) http://host:port/WarFileName/genre?deleteId=(genre_id)
  ```
  
  <b><p> 4. Страница голосования</b></p>
URL "/vote"  (1 vote singer, 3-5 votes for genres)
(POST)http://host:port/WarFileName/vote?singer=(singer_id)&genre=(genre_id)&genre=(genre_id)&genre=(genre_id)&message=(message)&email=(email)
На данной странице предусмотрено голосование. Необходимо указать следующие ключи:
 - Для голосования за артиста "singer" и указать id
 - Для голосования за жанры "genre" и указать id
 - Добавить информации к голосу "message" и написать текст сообщения
 - Указать свой email для отправки информации об отправленном голосе "email".
 После отправки голоса пользователь получает информацию о текущих результатах голосования
  <b><p>4. Страница результатов голосования</b></p>
  На данной станице можно просмотреть текущие результаты голосования
- (GET) http://host:port/WarFileName/result
