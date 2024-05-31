# Создание Spring Boot Starter для логирования HTTP запросов

## Описание:

Spring Boot Starter для логирования HTTP запросов и ответов в вашем приложении. Этот стартер обеспечивает логирование
HTTP метода,
URL, заголовков запросов и ответов, кода ответа, времени обработки запросов и других данных.

## Функциональность

- Логирование всех входящих HTTP запросов и ответов.
- Поддержка логирования HTTP метода, URL, заголовков запросов и ответов, кода ответа и времени обработки запросов.
- Настраиваемый уровень логирования и формат вывода логов.

## Установка

### 1. Добавьте стартер в локальный Maven репозиторий

#### Соберите и опубликуйте стартер в локальный репозиторий Maven:

```sh
./gradlew clean build
./gradlew publishToMavenLocal
```

### 2. Подключите стартер в вашем проекте

Добавьте следующий зависимость в файл `build.gradle.kts` вашего проекта:

```kotlin
repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.forafox:t1-open-school-spring-boot-starter:1.0")
}
```

## Использование

Стартер автоматически настроится и начнет логировать HTTP запросы и ответы при запуске вашего приложения.

## Конфигурация

Вы можете настроить логирование, добавив свойства в ваш `application.yml` или `application.properties` файл:

#### Пример конфигурации в `application.yml`

```yaml
logging:
  http:
    enabled: true
http:
  logging:
    log-headers: true
```

#### Пример конфигурации в `application.properties`

```properties
logging.http.enabled=true
http.logging.log-headers=true
```

## Примеры логов
Пример логов для входящего запроса и исходящего ответа:

```less
INFO  [main] c.e.logging.HttpLoggingFilter -- HTTP GET - /test - 200 - 17ms 
INFO  [main] c.e.logging.HttpLoggingFilter -- Request Headers: User-Agent: [JUnit Test]
INFO  [main] c.e.logging.HttpLoggingFilter -- Response Headers: Content-Type: [application/json]
```
## Стек

- Java
- Spring Boot
- Gradle

