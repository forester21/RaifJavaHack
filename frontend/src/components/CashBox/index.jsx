import * as React from 'react';
import Button from 'antd/es/button';
import { connect } from 'react-redux';

import { requestGenerateQRCode } from 'actions';
import SelectedProducts from 'components/SelectedProducts';

const mapDispatchToProps = dispatch => ({
  requestGenerateQRCode: () => dispatch(requestGenerateQRCode()),
});

const CashBox = ({ requestGenerateQRCode }) => (
  <div>
    <SelectedProducts />
    <Button onClick={requestGenerateQRCode}>GET QR CODE</Button>
  </div>
);

export default connect(
  null,
  mapDispatchToProps
)(CashBox);
