## О чем проект
Данное приложение предназначено для визуального отображения данных, получаемых с АПИ https://swapi.dev/ - АПИ по серии фильмов Звездные войны.
На данный момент реализован следующий функционал:
1) Поиск планет по имени 
2) Поиск звездолетов по имени 
3) Поиск персонажей по имени
4) Сохранение в избранное
5) Просмотр избранного
6) Просмотр подробной информации о персонаже
7) Просмотр подробной информации о звездолете
8) Просмотр подробной информации о планете
## Стек технологий
* Compose Jetpack - для создания пользовательского интерфейса
* Retrofit - для работы с АПИ
* Room - для работы с SQLite
* Kotlin Coroutines
* Coil - для загрузки изображений из интернета
* Koin - для внедрения зависимостей
* MVVM - шаблон проектирования
* Clean architecture
* Многомодульность
* Paging3 - для реализации пагинации
## Описание функционала
<p align="center">
  <img src="https://github.com/EvgenieBespalov/StarWars_App/assets/95974491/654fde5f-bc4e-4217-9d1f-6b10b7b8b422" style="height:500px">
  <br>Вкладка "Search" 
</p>
На данной странице пользователь может выбрать категорию поиска, после чего перейдет на соответствующую страницу поиска.

<p align="center">
  <br>
  <img src="https://github.com/EvgenieBespalov/StarWars_App/assets/95974491/0e61a758-5f19-47dc-b114-3b45d18936c2" style="height:500px">
  <br>Страницы поиска
</p>
Страницы поиска по названию. При введении от 2х символов начинается загрузка выбранных объектов, при нажатии на любой элемент будет переход на страницу с подробной информацией об объекте.

<p align="center">
  <br>
  <img src="https://github.com/EvgenieBespalov/StarWars_App/assets/95974491/109bc07f-aede-4318-9e28-d8d4db73f896" style="height:500px">
  <br>Страницы с информацией о объектах
</p>
Страницы с информацией об объектах.
<br>

<p align="center">
  <br>
  <img src="https://github.com/EvgenieBespalov/StarWars_App/assets/95974491/faad9e72-ccd3-4761-9aa3-1004125c9ad0" style="height:500px">
  <br>Страница зведолета до и после добавления в избранное
</p>
На каждой странице информации об объекте можно добавить его в избранное нажатием на кнопку в виде серой звезды около его названия. При успешном сохранении или при уже наличии в избранном в избранном кнопка перекрашивается в желтый цвет. 
<br>

<p align="center">
  <br>
  <img src="https://github.com/EvgenieBespalov/StarWars_App/assets/95974491/8678790a-3c00-47a0-9fbd-c77948c62d30" style="height:500px">
  <br>Вкладка избранного
</p>
На данной странице выводятся все объекты, добавленные пользователем в избранное. 
<br>
