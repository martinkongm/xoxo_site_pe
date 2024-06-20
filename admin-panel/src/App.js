import React from 'react';
import { Container } from 'react-bootstrap';
import Sidebar from './components/Sidebar';
import Routes from './api/Routes';

const App = () => {
  return (
    <Container>
      <Sidebar />
      <Routes />
    </Container>
  );
};

export default App;
