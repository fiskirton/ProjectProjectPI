# ProjectProjectPI

## Приложение для прохождения тестов по типу Kahoot.it

## Функциональность

Данное приложение позволяет создавать тесты с произвольным числом вопросов, начинающиеся и заканчивающиеся по таймеру.

После завершения теста пользователи видят рейтинг участников.

## Эндпоинты бекенда

#### Получить список тестов

GET /tests/allTests

Входящие параметры: -

Ответ:
```
{
    id: string;
    title: string;
}
```
---
#### Получить статус теста

GET /tests/${id}/status

Входящие параметры: id теста

Ответ:
```
{
    status: string;
}
```
---
#### Получить время начала и окончания теста

GET /tests/${id}/time

Входящие параметры: id теста

Ответ:
```
{ 
    startAt: string; 
    endAt: string 
}
```
---
#### Получить рейтинг

GET /tests/${id}/getRating

Входящие параметры: id теста

Ответ:
```
{
    name: string;
    score: number;
}[]
```

---
#### Получить текущий вопрос для текущего пользователя

GET /getCurrentAnswer

Входящие параметры: id теста, никнейм пользователя

Ответ:
```
{
    question: {
        questionId: number;
        questionNumber: number;
        testId: number;
        text: string;
    };
    answer: Array<{
        answerId: number;
        questionId: number;
        text: string;
    }>;
    totalQuestions: number;
}
```
---
#### Отправить ответ на вопрос

POST /sendAnswer

Входящие параметры: id вопроса, id ответа, никнейм пользователя

Ответ:
```
{
      answerId: number;
      questionId: number;
      text: string;
      right: boolean;
}
```
