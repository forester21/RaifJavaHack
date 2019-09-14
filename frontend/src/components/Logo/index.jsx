import * as React from 'react';

import styles from './Logo.module.scss';
import thematize from 'lib/thematize';
import logo from 'img/logo-raif.svg';

const theme = thematize(styles);

const Logo = () => (
  <div className={theme('container')}>
    <img src={logo} alt="" className={theme('logo')} />
  </div>
);

export default Logo;
