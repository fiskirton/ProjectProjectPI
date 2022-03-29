import {
  ITest,
  ITestQuestion,
  ITestQuestionRightAnswer,
  ITestRating,
  ITestTime,
  TestStatusType,
} from "./interfaces/Test";

const testsMock: ITest[] = [
  {
    id: "1",
    title: "Кто вы из фиксиков?",
  },
  {
    id: "2",
    title: "Кто вы из марвел?",
  },
  {
    id: "3",
    title: "Как правильно жарить картошку?",
  },
  {
    id: "4",
    title: "Тест на знание математики",
  },
  {
    id: "5",
    title: "Россия или Украина",
  },
  {
    id: "6",
    title: "Тест6",
  },
];

class Repository {
  public async testList() {
    return testsMock;
  }

  public async getTestStatus(id: string): Promise<TestStatusType> {
    return "inProgress";
  }

  public async getTestTime(id: string): Promise<ITestTime> {
    return {
      startAt: 1657717424822,
      finishAt: 1657717424822,
    };
  }

  public async getTestRating(id: string): Promise<ITestRating> {
    return [
      { name: "serega", score: 100 },
      { name: "artem", score: 80 },
      { name: "lexa", score: 60 },
      { name: "aaa", score: 40 },
      { name: "bbbbbb", score: 20 },
    ];
  }

  public async getTestCurrentQuestion(id: string): Promise<ITestQuestion | null> {
    return {
        questionNumber: 2,
        totalQuestions: 5,
        text: "В чем сила?",
        answers: [
          {
            id: "1",
            text: "В правде",
          },
          {
            id: "2",
            text: "В неправде asd assdfsdfsdfsdfsdf",
          },
          {
            id: "3",
            text: "В украине",
          },
          {
            id: "4",
            text: "В россии",
          },
        ],
      }
  }

  public async sendTestAnswer(
    questionId: string,
    answerId: string,
    name: string
  ): Promise<ITestQuestionRightAnswer> {
    return "1";
  }
}

export default new Repository();
