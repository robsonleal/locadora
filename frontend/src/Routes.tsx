import { Navbar } from 'components/Navbar';
import { Admin } from 'pages/Admin';
import { Catalogo } from 'pages/Catalogo';
import { Home } from 'pages/Home';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

export const MyRoutes = () => {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path='/filmes' element={<Catalogo />} />
        <Route path='/admin' element={<Admin />} />
      </Routes>
    </BrowserRouter>
  );
};
