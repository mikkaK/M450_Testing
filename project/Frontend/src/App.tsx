import React from 'react';

import './App.css';

import ActiveUserContext, { ActiveUserContextProvider } from './Contexts/ActiveUserContext';
import Router from './Router/Router';
import {AppBar, Toolbar, Typography} from "@mui/material";

function App() {
    const context = React.useContext(ActiveUserContext);
  return (
    <ActiveUserContextProvider>
        <AppBar position="static" sx={{ backgroundColor: "#1E1E1E", boxShadow: "none" }}>
            <Toolbar>
                <Typography variant="h5" sx={{ fontWeight: 200 }}>
                    Palantir
                </Typography>
                {context.user?.firstName + context.user?.lastName}
            </Toolbar>
        </AppBar>
      <Router />
    </ActiveUserContextProvider>
  );
}

export default App;
