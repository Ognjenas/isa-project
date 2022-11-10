import React from 'react';
import logo from './logo.svg';
import './App.css';
import Pages from './routing';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <div className="App">
      <Pages />
      <ToastContainer position='bottom-right' />
    </div>
  );
}

export default App;
