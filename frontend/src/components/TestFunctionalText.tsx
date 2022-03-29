import "../styles/TestFunctionalText.css";

export default function TestFunctionalText({ text } : {text: string}) {
    return <div id={"test-functional-text"}>
        <p>{ text }</p>
    </div>;
}
