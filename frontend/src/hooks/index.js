import * as React from 'react';
import { useSelector } from 'react-redux';

import { ReduxStore } from 'components/App';
import { loadProductsAndCategories } from 'actions';

export const useLoadProductsAndCategoriesOnMount = () =>
  React.useEffect(() => {
    ReduxStore.dispatch(loadProductsAndCategories());
  }, []);

export const useSelectedProducts = () => useSelector(state => state.selectedProducts);
