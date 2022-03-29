import repository from "../repository";
import "../styles/TestFunctionalQuestion.css";
import { useEffect, useState } from "react";
import {
  ITestQuestion,
  ITestQuestionAnswer,
  ITestQuestionRightAnswer,
  TestStatusType,
} from "../interfaces/Test";
import Repository from "../repository";
import TestFunctionalTextWithTimer from "./TestFunctionalTextWithTimer";

function assertQuestion(question: any): asserts question is ITestQuestion {
  if (!question) {
    throw new Error("Question is not an object");
  }
}

export default function TestFunctionalQuestion({
  id,
  nick,
  updateStatusFunc,
  repository,
}: {
  id: string;
  nick: string;
  updateStatusFunc: () => any;
  repository: typeof Repository;
}) {
  const [question, updateQuestion] = useState<ITestQuestion | null>(null);
  const [clicked, updateClicked] = useState(false);

  const updateCurrentQuestion = () => {
    updateClicked(false);
    repository.getTestCurrentQuestion(id).then((q) => updateQuestion(q));
  };

  const onAnswer = async (answerId: string) => {
    if (clicked) return;
    assertQuestion(question);
    const rightAnswer = await repository.sendTestAnswer(id, answerId, nick);

    const answers = question.answers;
    answers.forEach((el) => (el.status = "not_checked"));

    const rightAnswerObject = answers.find(
      (el) => el.id === rightAnswer
    ) as ITestQuestionAnswer;
    rightAnswerObject.status = "right";

    if (answerId !== rightAnswer) {
      const wrongAnswerObject = answers.find(
        (el) => el.id === answerId
      ) as ITestQuestionAnswer;
      wrongAnswerObject.status = "wrong";
    }
    updateQuestion({ ...question, answers });
    updateClicked(true);

    setTimeout(() => updateCurrentQuestion(), 1500);
  };

  useEffect(() => {
    updateCurrentQuestion();
  }, []);

  return question === null ? (
    <TestFunctionalTextWithTimer
      id={id}
      field={"finishAt"}
      text={"Результаты будут доступы"}
      updateStatusFunc={updateStatusFunc}
      repository={repository}
    />
  ) : (
    question && (
      <div id={"test-functional-question"}>
        <h1>{question.text}</h1>
        <div className={"test-functional-question-answers"}>
          {question.answers.map((answer) => (
            <div
              onClick={() => onAnswer(answer.id)}
              key={answer.id}
              className={`test-functional-question-answer${
                answer.status
                  ? ` test-functional-question-answer__${answer.status}`
                  : ""
              }`}
            >
              {answer.text}
            </div>
          ))}
        </div>
        <div className={"test-functional-question-number"}>
          <p>
            Вопрос: {question.questionNumber} / {question.totalQuestions}
          </p>
        </div>
      </div>
    )
  );
}
