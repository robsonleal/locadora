import { ReactComponent as MainImage } from 'assets/images/main-image.svg';
import { Navbar } from 'components/Navbar';
import './styles.scss';
import { ButtonIcon } from 'components/Navbar/ButtonIcon';

export const Home = () => {
  return (
    <>
      <Navbar />
      <div className="home-container">
        <div className="home-card">
          <div className="home-content-container">
            <div>
              <h1>Conheça o melhor catálogo de filmes</h1>
              <p>
                Encontre os filmes mais assistidos, para assistir com a família
                toda reunida
              </p>
            </div>
            <ButtonIcon />
          </div>
          <div className="home-image-container">
            <MainImage />
          </div>
        </div>
      </div>
    </>
  );
};
