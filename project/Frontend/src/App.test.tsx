import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';
import {BrowserRouter, Route, Routes} from "react-router-dom";

test('renders learn react link', () => {
  render(
      <BrowserRouter>
        <Routes>
          <Route path="*" element={<App/>}/>
        </Routes>
      </BrowserRouter>
  );
});
