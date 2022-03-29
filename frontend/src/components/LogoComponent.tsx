import "../styles/LogoComponent.css";
import { Link } from "react-router-dom";

export default function LogoComponent() {
  return (
    <Link style={{ textDecoration: "none" }} to={`/`}>
      <header className={"logo"}>
        <img src={"/hselogo.svg"} className={"logo-image"} alt={"hse"} />
        <p className={"logo-text"}>Хсе Тестс</p>
      </header>
    </Link>
  );
}
