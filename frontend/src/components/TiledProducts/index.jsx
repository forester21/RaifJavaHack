import * as React from 'react';
import { useSelector, connect } from 'react-redux';
import Card from 'antd/es/card';
import { filter } from 'lodash';

import thematize from 'lib/thematize';
import { addSelectedProduct } from 'actions';
import styles from './TiledProducts.module.scss';

const theme = thematize(styles);

const showTypes = {
  products: 'products',
  categories: 'categories',
};

const Category = ({ name, id, onClick }) => {
  const memoizedOnClick = React.useCallback(() => onClick(id), [id, onClick]);

  return (
    <Card className={theme('category')} key={id} onClick={memoizedOnClick}>
      {name}
    </Card>
  );
};

const Product = ({ name, price, id, onClick }) => {
  const memoizedOnClick = React.useCallback(() => onClick(id), [id, onClick]);

  return (
    <Card
      size="small"
      bodyStyle={{ textAlign: 'center' }}
      className={theme('product')}
      title={name}
      onClick={memoizedOnClick}
    >
      {price}
    </Card>
  );
};

const mapDispatchToProps = dispatch => ({
  handleProductClick: id => dispatch(addSelectedProduct(id)),
});

const TiledProducts = ({ handleProductClick }) => {
  const [showType, setShowType] = React.useState(showTypes.categories);
  const [selectedCategoryId, setCategoryId] = React.useState(null);
  const products = useSelector(state => state.products);
  const categories = useSelector(state => state.categories);
  const filteredProducts = filter(products, prod => prod.categoryId === selectedCategoryId);

  const onCategoryClick = id => {
    setCategoryId(id);
    setShowType(showTypes.products);
  };

  const onProductClick = id => {
    setShowType(showTypes.categories);
    handleProductClick(id);
  };

  return (
    <div className={theme('tiled-products-container')}>
      {showType === showTypes.categories
        ? categories.map(cat => (
            <Category name={cat.name} id={cat.id} key={cat.id} onClick={onCategoryClick} />
          ))
        : filteredProducts.map(prod => (
            <Product {...prod} key={prod.id} onClick={onProductClick} />
          ))}
    </div>
  );
};

export default connect(
  null,
  mapDispatchToProps
)(TiledProducts);
