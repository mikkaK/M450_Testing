import React from 'react';

import './App.css';

import { ActiveUserContextProvider } from './Contexts/ActiveUserContext';
import Router from './Router/Router';
import {AppBar, Toolbar, Typography} from "@mui/material";

function App() {
  return (
    <ActiveUserContextProvider>
        <AppBar position="static" sx={{ backgroundColor: "#2D2F31", boxShadow: "none" }}>
            <Toolbar>
                <Typography variant="h6" sx={{ fontWeight: 600 }}>
                    OurSpace
                </Typography>
            </Toolbar>
        </AppBar>
      <Router />
    </ActiveUserContextProvider>
  );
}

export default App;
