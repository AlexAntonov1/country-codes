**REST / Spring Boot / Spring Cloud Openfeign / Gradle / Lombok / JUnit / H2**
##
**TEST TASK**
##

**Description**
>Требуется реализовать микросервис, использую любой современный фреймворк согласно требованиям:

1. Сервис должен загрузить данные о странах и телефонных кодах стран в реляционную БД.
   Источник данных:
   Старны: http://country.io/names.json
   Телефоныне коды: http://country.io/phone.json
2. Реализовать REST сервис POST /reload, который по требованию обновит данные в БД о странах и телефонных кодах.
3. Выполнять автоматический запуск обновления данных в БД о странах и телефонных кодах, по расписанию, каждую ночь в 2 часа ночи по Москве. (Обязательный для эксперта)
4. Реализовать REST сервис GET /code/${COUNTRYNAME}, который возвращает телефонный код по имени страны, где ${COUNTRYNAME} - это имя страны в любом регистре, например JAMAICA. Возвращать HTTP код 200 в случае успешного ответа, возвращать код 404 в случае, если страна не найдена.
5. Реализовать unit test к сервису GET /code/${COUNTRYNAME}
6. Реализовать логирование ошибок приложения и логирование запросов к реализованным REST сервисам. (Обязательный для эксперта, желательный для остальных)
7. Написать Dockerfile сборки и деплоя приложения (Обязательный для эксперта)

**Technology stack:**
- Spring Boot
- REST
- Spring Cloud Openfeign
- Gradle
- JUnit
- Lombok
- H2 in memory DB

##
**How to use this program**

**1. Clone a repository**

**2. Open the project using the IDE**

**3. Run this app in your IDE or create Docker container**

**(Optional) Create Docker container**
```sh
gradle bootJar
Run Dockerfile script
docker build --build-arg JAR_FILE=build/libs/*.jar -t antonov/country-codes .
docker run --name country-codes -dp 8080:8080 antonov/country-codes
```

**4. Start country-codes service :**
```sh
  POST http://localhost:8080/reload Перезагрузка данных с country.io , для заполнения данными базы данных нужно вызвать этот метод.
```
```sh
  GET http://localhost:8080/code/{COUNTRYNAME} COUNTRYNAME - Russia,Bangladesh и тд.
```
##