import React from 'react';

import './App.css';

import Router from './Router/Router';

function App() {
  return (
      <>
          <header className="app-header">
              <div className="header-image"></div>
              <a href={"/"} className="app-name">Letterix</a>
          </header>
          <div className="app-background">
              <Router />
          </div>
      </>

  );
}

export default App;
