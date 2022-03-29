import "../styles/TestFunctionalText.css";
import TestFunctionalText from "./TestFunctionalText";
import { useEffect, useState } from "react";
import Repository from "../repository";
import {ITestTime} from "../interfaces/Test";

export default function TestFunctionalTextWithTimer({
  id,
  text,
  field,
  updateStatusFunc,
  repository,
}: {
  id: string;
  text: string;
  field: keyof ITestTime;
  updateStatusFunc: () => any;
  repository: typeof Repository;
}) {
  const [seconds, updateSeconds] = useState(100);

  const decrementSeconds = () =>
    updateSeconds((prevSeconds) => (prevSeconds <= 0 ? 0 : prevSeconds - 1));

  useEffect(() => {
    let interval: NodeJS.Timer;

    repository.getTestTime(id).then((time) => {
      updateSeconds(Math.floor((time[field] - Date.now()) / 1000));
      interval = setInterval(() => {
        decrementSeconds();
      }, 1000);
    });

    return () => clearInterval(interval);
  }, []);

  useEffect(() => {
    if (seconds <= 0) {
      updateStatusFunc();
    }
  }, [seconds, updateStatusFunc]);

  return <TestFunctionalText text={`${text} через ${seconds} секунд`} />;
}
