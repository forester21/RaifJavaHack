import * as React from 'react';
import { useSelector } from 'react-redux';

import { ReduxStore } from 'components/App';
import { loadProducts, loadCategories } from 'actions';

export const useLoadProductsAndCategoriesOnMount = () =>
  React.useEffect(() => {
    ReduxStore.dispatch(loadProducts());
    ReduxStore.dispatch(loadCategories());
  }, []);

export const useSelectedProducts = () => useSelector(state => state.selectedProducts);
