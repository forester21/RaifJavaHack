import * as React from 'react';
import Table from 'antd/es/table';
import Icon from 'antd/es/icon';
import InputNumber from 'antd/es/input-number';

import thematize from 'lib/thematize';
import styles from './SelectedProducts.module.scss';
import { useSelectedProducts, useLoadProductsAndCategoriesOnMount } from 'hooks';
import { ReduxStore } from 'components/App';
import { deleteSelectedProduct } from 'actions';
import { changeProductCount } from '../../actions';

const theme = thematize(styles);

const IconWrapper = ({ id }) => {
  const memoizedOnClick = React.useCallback(() => {
    ReduxStore.dispatch(deleteSelectedProduct(id));
  }, [id]);
  return <Icon type="delete" className={theme('delete-icon')} onClick={memoizedOnClick} />;
};

const columns = [
  {
    title: 'Имя',
    dataIndex: 'name',
    render: text => {
      return {
        props: {
          className: theme('product-name'),
        },
        children: text,
      };
    },
  },
  {
    title: 'Цена',
    dataIndex: 'price',
  },
  {
    title: 'Кол-во',
    dataIndex: 'count',
    render: (text, record) => (
      <InputNumber
        value={text}
        onChange={value => ReduxStore.dispatch(changeProductCount(record.key, value))}
      />
    ),
  },
  {
    title: 'Сумма',
    dataIndex: 'totalPrice',
  },
  {
    title: '',
    dataIndex: '',
    render: (text, record) => <IconWrapper id={record.key} />,
  },
];

const SelectedProducts = () => {
  useLoadProductsAndCategoriesOnMount();
  const selectedProducts = useSelectedProducts();

  return (
    <div className={theme('container')}>
      <Table
        columns={columns}
        dataSource={selectedProducts}
        size="small"
        title={() => 'Выбранные позиции'}
        pagination={false}
      />
    </div>
  );
};

export default SelectedProducts;
