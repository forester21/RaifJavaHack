import * as React from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { connect } from 'react-redux';

import { getQRCode } from 'actions';
import thematize from 'lib/thematize';
import styles from './QRCode.module.scss';

const theme = thematize(styles);

const socket = new SockJS('http://10.91.6.103:8080/ws');

const mapStateToProps = ({ QRCodeUrl }) => ({ QRCodeUrl });

const mapDispatchToProps = dispatch => ({
  getQRCode: () => dispatch(getQRCode()),
});

class QRCode extends React.Component {
  componentDidMount() {
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, frame => {
      stompClient.subscribe('/topic/QR', greeting => {
        this.props.getQRCode();
      });
    });

    this.props.getQRCode();
  }

  render() {
    return (
      <div className={theme('container')}>
        <img src={this.props.QRCodeUrl} alt="" className={theme('image')} />
      </div>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(QRCode);
