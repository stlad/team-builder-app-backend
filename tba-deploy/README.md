## Запуск системы на сервере: 


1) Собрать приложение с помощью ```mvn clean install```.  поместить .jar файл в директорию backend/<название сервиса>
2) Собрать клиент-приложение с помощью ```npm start build```. Поместить полученную директорию build в директорию frontend/
3) Установить docker на сервер
4) Перенести всю директорию tba-deploy на удаленный сервер
5) Перейти в директорию tba-deploy и использовать команду ```docker compose up --build```

6) При первом запуске, в контейнере tba-db необходимо создать схемы:
 <br>Через терминал контейнера:<br> 
 ```
 sudo docker start tba-db
 docker exec -it tba-db bash

 psql
 \c teambuilder_db
 create schema belbin;
 create schema admin;
 create schema hardskills;
 create schema teambuilder;
 ```
 7) Перезапустить все контейнеры ```docker compose up -d```


___
### Обновление сервисов
1) Перенсти новую сборку сервиса в соответствующую директорию на сервер. 
2) ```docker compose build <имя сервиса>```
3) ```docker-compose up -d```
