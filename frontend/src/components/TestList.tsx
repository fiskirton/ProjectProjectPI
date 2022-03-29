import Test from "./Test";
import repository from "../repository";
import "../styles/TestList.css";
import { ChangeEvent, useEffect, useState } from "react";
import { ITest } from "../interfaces/Test";

export default function TestList() {
  const [tests, updateTests] = useState<ITest[]>([]);
  const [nick, updateNick] = useState("");

  const onChange = (event: ChangeEvent<HTMLInputElement>) => {
    updateNick(event.target.value);
  };

  useEffect(() => {
    repository.testList().then((tests) => updateTests(tests));
  }, []);

  return (
    <>
      <input
        className={"nickname"}
        type={"text"}
        value={nick}
        onChange={onChange}
        placeholder={"Введите ваш никнейм"}
      />
      <div id={"test-list"}>
        {tests.map(({ id, title }) => (
          <Test key={id} id={id} title={title} nick={nick} />
        ))}
      </div>
    </>
  );
}
