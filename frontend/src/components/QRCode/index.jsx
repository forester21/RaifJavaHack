import * as React from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { connect } from 'react-redux';

import { getQRCode } from 'actions';

const socket = new SockJS('http://10.91.6.103:8080/ws');

const mapStateToProps = ({ QRCodeUrl }) => ({ QRCodeUrl });

const mapDispatchToProps = dispatch => ({
  getQRCode: () => dispatch(getQRCode()),
});

class QRCode extends React.Component {
  componentDidMount() {
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, frame => {
      console.log('connected', frame);
      stompClient.subscribe('/topic/test', greeting => {
        console.log('message', greeting);
        this.props.getQRCode();
      });
    });

    this.props.getQRCode();
  }

  render() {
    return (
      <div>
        <img src={this.props.QRCodeUrl} alt="" />
      </div>
    );
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(QRCode);
