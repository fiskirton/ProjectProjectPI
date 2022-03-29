import "../styles/TextFunctionalRating.css";
import { useEffect, useState } from "react";
import { ITestRating } from "../interfaces/Test";
import Repository from "../repository";

function RatingRow({
  place,
  name,
  score,
}: {
  place: number;
  name: string;
  score: number;
}) {
  return (
    <li>
      <div>{place + 1}</div>
      <div>{name}</div>
      <div>{score}</div>
    </li>
  );
}

export default function TextFunctionalRating({
  id,
  repository,
}: {
  id: string;
  repository: typeof Repository;
}) {
  const [participants, updateParticipants] = useState<ITestRating>([]);

  useEffect(() => {
    repository.getTestRating(id).then((rating) => updateParticipants(rating));
  }, []);

  return (
    <div id={"test-functional-rating"}>
      <h1>Рейтинг участников теста: </h1>
      <ul>
        {participants.map(({ name, score }, i) => (
          <RatingRow key={i} name={name} score={score} place={i} />
        ))}
      </ul>
    </div>
  );
}
