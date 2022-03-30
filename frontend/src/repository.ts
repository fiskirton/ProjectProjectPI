import {
  ITest,
  ITestQuestion,
  ITestQuestionRightAnswer,
  ITestRating,
  ITestTime,
  TestStatusType,
} from "./interfaces/Test";
import axios from "axios";

const currentQuestionMock: ITestQuestion = {
  id: "1",
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
};

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
  url = "http://localhost:8080";

  public async testList() {
    if (process.env.REACT_APP_ENVIRONMENT !== 'production') {
      return testsMock;
    }

    const result: ITest[] = (await axios.get(this.url + "/tests/allTests"))
      .data;
    return result;
  }

  public async getTestStatus(id: string): Promise<TestStatusType> {
    if (process.env.REACT_APP_ENVIRONMENT !== 'production') {
      return 'inProgress';
    }

    const result: { status: TestStatusType } = (
      await axios.get(this.url + `/tests/${id}/status`)
    ).data;
    return result.status;
  }

  public async getTestTime(id: string): Promise<ITestTime> {
    if (process.env.REACT_APP_ENVIRONMENT !== 'production') {
      return {
        startAt: 1657717424822,
        finishAt: 1657717424822,
      };
    }

    const result: { startAt: string; endAt: string } = (
      await axios.get(this.url + `/tests/${id}/time`)
    ).data;

    const threeHours = 3 * 60 * 60 * 1000;
    return {
      startAt: Date.parse(result.startAt) + threeHours,
      finishAt: Date.parse(result.endAt) + threeHours,
    };
  }

  public async getTestRating(id: string): Promise<ITestRating> {
    if (process.env.REACT_APP_ENVIRONMENT !== 'production') {
      return [
        { name: "serega", score: 100 },
        { name: "artem", score: 80 },
        { name: "lexa", score: 60 },
        { name: "aaa", score: 40 },
        { name: "bbbbbb", score: 20 },
      ];
    }

    const result: ITestRating = (
      await axios.get(this.url + `/tests/${id}/getRating`)
    ).data;

    return result;
  }

  public async getTestCurrentQuestion(
    id: string,
    nick: string
  ): Promise<ITestQuestion | null> {
    if (process.env.REACT_APP_ENVIRONMENT !== 'production') {
      return currentQuestionMock;
    }

    const url = new URL(this.url + `/getCurrentAnswer`);
    url.searchParams.set("test_id", id);
    url.searchParams.set("user_nick_name", nick);

    let result: {
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
    };
    try {
      result = (await axios.get(url.href)).data;
    } catch (e) {
      return null;
    }

    return {
      id: String(result.question.questionId),
      questionNumber: result.question.questionNumber,
      totalQuestions: result.totalQuestions,
      text: result.question.text,
      answers: result.answer.map((answer) => ({
        id: String(answer.answerId),
        text: answer.text,
      })),
    };
  }

  public async sendTestAnswer(
    questionId: string,
    answerId: string,
    nick: string
  ): Promise<ITestQuestionRightAnswer> {
    if (process.env.REACT_APP_ENVIRONMENT !== 'production') {
      return "1";
    }

    const url = new URL(this.url + `/sendAnswer`);
    url.searchParams.set("question_id", questionId);
    url.searchParams.set("answer_id", answerId);
    url.searchParams.set("user_nick_name", nick);

    const result: {
      answerId: number;
      questionId: number;
      text: string;
      right: boolean;
    } = (await axios.post(url.href)).data;

    return String(result.answerId);
  }
}

export default new Repository();
