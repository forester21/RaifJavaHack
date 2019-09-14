import * as React from 'react';
import Button from 'antd/es/button';
import { connect } from 'react-redux';

import { getQRCode } from 'actions';
import SelectedProducts from 'components/SelectedProducts';

const mapDispatchToProps = dispatch => ({
  getQRCode: () => dispatch(getQRCode()),
});

const CashBox = ({ getQRCode }) => (
  <div>
    <SelectedProducts />
    <Button onClick={getQRCode}>GET QR CODE</Button>
  </div>
);

export default connect(
  null,
  mapDispatchToProps
)(CashBox);
