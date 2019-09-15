import * as React from 'react';
import Button from 'antd/es/button';
import Statistic from 'antd/es/statistic';
import { connect, useSelector } from 'react-redux';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import message from 'antd/es/message';

import { requestGenerateQRCode, clearSelectedProducts } from 'actions';
import SelectedProducts from 'components/SelectedProducts';
import TiledProducts from 'components/TiledProducts';
import thematize from 'lib/thematize';
import styles from './CashBox.module.scss';

const theme = thematize(styles);

const socket = new SockJS('http://10.91.6.103:8080/ws');

const mapDispatchToProps = dispatch => ({
  requestGenerateQRCode: () => dispatch(requestGenerateQRCode()),
  clearSelectedProducts: () => dispatch(clearSelectedProducts()),
});

const TotalPrice = () => {
  const selectedProducts = useSelector(state => state.selectedProducts);
  const totalPrice = selectedProducts.reduce((sum, prod) => (sum += prod.totalPrice), 0);

  return <Statistic style={{ marginBottom: '5px' }} title="Итог" value={totalPrice} />;
};

class CashBox extends React.Component {
  componentDidMount() {
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, frame => {
      stompClient.subscribe('/topic/cashBox', greeting => {
        message.success('Заказ успешно оплачен!', 3);
        setTimeout(this.props.clearSelectedProducts(), 1000);
      });
    });
  }
  render() {
    return (
      <div className={theme('container')}>
        <div className={theme('selected-products-container')}>
          <div>
            <SelectedProducts />
          </div>
          <div className={theme('total-and-button-container')}>
            <TotalPrice />
            <Button onClick={this.props.requestGenerateQRCode}>QR код</Button>
          </div>
        </div>
        <div className={theme('tiled-products-container')}>
          <TiledProducts />
        </div>
      </div>
    );
  }
}

export default connect(
  null,
  mapDispatchToProps
)(CashBox);
