import { ITest } from "../interfaces/Test";
import "../styles/Test.css";
import { Link } from "react-router-dom";
import { useAlert } from "react-alert";
export default function Test({ id, title, nick }: ITest & { nick: string }) {
  const alert = useAlert();

  const onClick = (e: any) => {
    if (!nick) {
      alert.error("Для участия в тесте введите ваш никнейм!");
      e.preventDefault();
    }
  };

  return (
    <Link
      onClick={onClick}
      className={"test"}
      to={`/test/${id}/${nick}`}
    >
      {title}
    </Link>
  );
}
