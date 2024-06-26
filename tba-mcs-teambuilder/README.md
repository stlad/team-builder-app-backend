## tba-mcs-teambuilder
___
### Ответственность сервиса:
1. Команды
2. Алгоритм формирования команд
___
### Сборка
1. Собрать tba-mcs-rest-client: ```mvn clean install```
2. Собрать tba-mcs-teambuilder
___

### Swagger для локального запуска:
http://localhost:8093/api/teambuilder/swagger-ui/index.html#/

___
## Алгоритм формировния команд
### Этап 1. Предподготовка
1. Условие: все студенты зарегистрированы и есть в системе
2. Расчитать квоту по командам.
```
Расчет квоты:
   1. Получить количество студентов studCount из tba-mcs-admin-api;
   2. Получить количество проф. ролей hardRoleCnt из tba-mcs-hardskills-api;
   3. quota = studCount / hardRoleCnt (человек / роль);    
```
3. Предоставить квоту по API через эндпоинт (используется tba-mcs-hardskills)
4. Студенты должны выбрать себе профессиональную роль, укладываясь в квоту


___
## Этап 2. Выбор ролей
1. Каждый студент выбирает профессиональную роль,укладываясь в квоту (только после этапа 1.)
2. Каждый студент определяет командую роль через тест Белбина (нужно сохранить результат. Проходить в любое время до этапа 3)

___
## Этап 3. Распределение

> В данной реализации за одним студентам закреплена одна единственная проф. роль. 
> Таким образом не требуется распределение по проф. ролям - студенты уже занимают сви роли.

> ПРИМЕР: После этапа 2, в системе имеются 100 (например) разработчиков, 100 дизайнеров, 100 аналитиков.
> Нужно каждому студенту поставить в соответствие одного студента из всех остальных проф ролей так, 
> чтобы их командные роли (по БЕлбину) были как можно более разнообразны. 


....
