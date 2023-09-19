import { ReactComponent as MainImage } from 'assets/images/main-image.svg';
import { ButtonIcon } from 'components/ButtonIcon';
import './styles.scss';
import { Link } from 'react-router-dom';

export const Home = () => {
  return (
    <div className="home-container">
      <div className="base-card home-card">
        <div className="home-content-container">
          <div>
            <h1>Conheça o melhor catálogo de filmes</h1>
            <p>
              Encontre os filmes mais assistidos, para assistir com a família
              toda reunida
            </p>
          </div>
          <Link to="/filmes">
            <ButtonIcon />
          </Link>
        </div>
        <div className="home-image-container">
          <MainImage />
        </div>
      </div>
    </div>
  );
};
