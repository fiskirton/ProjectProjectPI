import "ts-jest";
import Repository from "../src/repository";

test('Получение списка тестов', async () => {
    const tests = await Repository.testList();
    expect(Array.isArray(tests)).toBeTruthy();
})

test('Получение статуса теста', async () => {
    const status = await Repository.getTestStatus("1");
    expect(typeof status).toBe("string");
})

test('Получение рейтинга', async () => {
    const rating = await Repository.getTestRating("1");
    expect(Array.isArray(rating)).toBeTruthy();
})

test('Получение времени теста', async () => {
    const rating = await Repository.getTestTime("1");
    expect(typeof rating).toBe("object");
})
