import './styles.scss';

import ProductImg from 'assets/images/capa1.jpg';

export const ProductCard = () => {
  return (
    <div className="base-card product-card">
      <div className="card-top-container">
        <img src={ProductImg} alt="Imagem capa do filme" />
      </div>
      <div className="card-bottom-container">
        <h6>Nome do filme</h6>
        <p>Lançamento</p>
        <p>Categoria</p>
      </div>
    </div>
  );
};
