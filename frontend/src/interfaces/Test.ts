export interface ITest {
    id: string;
    title: string;
}

export type TestStatusType = 'notStarted' | 'notFound' | 'inProgress' | 'finished';

export interface ITestTime {
    startAt: number;
    finishAt: number;
}

export interface ITestRatingRow {
    name: string;
    score: number;
}

export type ITestRating = ITestRatingRow[];

export interface ITestQuestionAnswer {
    id: string;
    text: string;
    status?: 'not_checked' | 'wrong' | 'right'
}

export interface ITestQuestion {
    questionNumber: number;
    totalQuestions: number;
    text: string,
    answers: ITestQuestionAnswer[];
}

export type ITestQuestionRightAnswer = string;
