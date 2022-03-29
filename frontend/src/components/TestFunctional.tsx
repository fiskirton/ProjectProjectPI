import repository from "../repository";
import "../styles/TestFunctional.css";
import { useCallback, useEffect, useMemo, useState } from "react";
import { TestStatusType } from "../interfaces/Test";
import {useLocation, useParams} from "react-router-dom";
import TestFunctionalText from "./TestFunctionalText";
import Repository from "../repository";
import TestFunctionalTextWithTimer from "./TestFunctionalTextWithTimer";
import TextFunctionalRating from "./TextFunctionalRating";
import TestFunctionalQuestion from "./TestFunctionalQuestion";

const RenderComponentByStatus = (
  id: string,
  nick: string,
  status: TestStatusType | null,
  repository: typeof Repository,
  updateStatusFunc: () => void
) => {
  switch (status) {
    case "notFound":
      return <TestFunctionalText text={"Тест не найден :("} />;
    case "notStarted":
      return (
        <TestFunctionalTextWithTimer
          id={id}
          field={"startAt"}
          text={"Тест начнется"}
          updateStatusFunc={updateStatusFunc}
          repository={repository}
        />
      );
    case "finished":
      return <TextFunctionalRating repository={repository} id={id} />;
    case "inProgress":
      return (
        <TestFunctionalQuestion
          id={id}
          nick={nick}
          updateStatusFunc={updateStatusFunc}
          repository={repository}
        />
      );
    default:
      return null;
  }
};

export default function TestFunctional() {
  const { id, nick } = useParams() as { id: string, nick: string };
  const [status, updateStatus] = useState<TestStatusType>();
  const updateStatusFunc = useCallback(() => {
    repository.getTestStatus(id).then((status) => updateStatus(status));
  }, []);

  useEffect(updateStatusFunc, []);

  return (
    <div
      id={"test-functional"}
      style={status === "finished" ? { backgroundColor: "inherit" } : {}}
    >
      {RenderComponentByStatus(
        id,
        nick,
        status || null,
        repository,
        updateStatusFunc
      )}
    </div>
  );
}
