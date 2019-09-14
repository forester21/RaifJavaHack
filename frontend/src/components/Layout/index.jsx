import * as React from 'react';

import thematize from 'lib/thematize';
import styles from './Layout.module.scss';

const theme = thematize(styles);

const Layout = ({ children }) => <div className={theme('container')}>{children}</div>;

export default Layout;
