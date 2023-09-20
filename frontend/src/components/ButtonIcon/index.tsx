import './styles.scss';

import { ReactComponent as ArrowIcon } from 'assets/images/seta.svg';

export const ButtonIcon = () => {
  return (
    <div className="btn-container btn btn-primary">
      <div className='btn-text-container'>
        <h6 className="text-uppercase">Inicie agora a sua busca</h6>
      </div>
      <div className="btn-icon-container">
        <ArrowIcon />
      </div>
    </div>
  );
};
