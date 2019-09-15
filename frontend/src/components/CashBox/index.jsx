import * as React from 'react';
import Button from 'antd/es/button';
import Statistic from 'antd/es/statistic';
import { connect, useSelector } from 'react-redux';

import { requestGenerateQRCode } from 'actions';
import SelectedProducts from 'components/SelectedProducts';
import TiledProducts from 'components/TiledProducts';
import thematize from 'lib/thematize';
import styles from './CashBox.module.scss';

const theme = thematize(styles);

const mapDispatchToProps = dispatch => ({
  requestGenerateQRCode: () => dispatch(requestGenerateQRCode()),
});

const TotalPrice = () => {
  const selectedProducts = useSelector(state => state.selectedProducts);
  const totalPrice = selectedProducts.reduce((sum, prod) => (sum += prod.totalPrice), 0);

  return <Statistic style={{ marginBottom: '5px' }} title="Итог" value={totalPrice} />;
};

const CashBox = ({ requestGenerateQRCode }) => (
  <div className={theme('container')}>
    <div className={theme('selected-products-container')}>
      <div>
        <SelectedProducts />
      </div>
      <div className={theme('total-and-button-container')}>
        <TotalPrice />
        <Button onClick={requestGenerateQRCode}>QR код</Button>
      </div>
    </div>
    <div className={theme('tiled-products-container')}>
      <TiledProducts />
    </div>
  </div>
);

export default connect(
  null,
  mapDispatchToProps
)(CashBox);
