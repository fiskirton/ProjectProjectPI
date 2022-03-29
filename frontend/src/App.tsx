import './App.css';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import TestList from "./components/TestList";
import LogoComponent from "./components/LogoComponent";
import TestFunctional from "./components/TestFunctional";

function App() {
  return (
  <Router>
    <LogoComponent/>
    <Routes>
      <Route path={'/'} element={<TestList/>}/>
      <Route path={'/test/:id/:nick'} element={<TestFunctional/>}/>
    </Routes>
  </Router>
  );
}

export default App;
