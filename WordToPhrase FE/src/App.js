import logo from './logo.svg';
import './App.css';
import Home from './pages/Home';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Words from './pages/Words';
import Register from './pages/Register';
import Login from './pages/Login';

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/words" element={<Words />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Register />} />
          
         
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
