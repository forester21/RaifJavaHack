import * as React from 'react';
import Button from 'antd/es/button';
import { connect } from 'react-redux';

import { requestGenerateQRCode } from 'actions';
import SelectedProducts from 'components/SelectedProducts';

import thematize from 'lib/thematize';
import styles from './CashBox.module.scss';

const theme = thematize(styles);

const mapDispatchToProps = dispatch => ({
  requestGenerateQRCode: () => dispatch(requestGenerateQRCode()),
});

const CashBox = ({ requestGenerateQRCode }) => (
  <div className={theme('container')}>
    <div className={theme('selected-products-container')}>
      <div>
        <SelectedProducts />
      </div>
      <div className={theme('request-button-container')}>
        <Button onClick={requestGenerateQRCode}>GET QR CODE</Button>
      </div>
    </div>
    <div className={theme('tiled-products-container')}>
      FGHJKL
    </div>
  </div>
);

export default connect(
  null,
  mapDispatchToProps
)(CashBox);
