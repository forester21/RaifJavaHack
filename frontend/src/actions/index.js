import { batch } from 'react-redux';

export const PRODUCTS_LOADING = 'PRODUCTS_LOADING';
export const PRODUCTS_LOADED = 'PRODUCTS_LOADED';
export const loadProducts = () => dispatch => {
  // api call

  dispatch({
    type: PRODUCTS_LOADED,
    payload: [],
  });
};

export const CATEGORIES_LOADING = 'CATEGORIES_LOADING';
export const CATEGORIES_LOADED = 'CATEGORIES_LOADED';
export const loadCategories = () => dispatch => {
  // api call

  dispatch({
    type: CATEGORIES_LOADED,
    payload: [],
  });
};

export const loadProductsAndCategories = () => dispatch => {
  // Promise all

  batch(() => {
    dispatch({
      type: CATEGORIES_LOADED,
      payload: [],
    });

    dispatch({
      type: PRODUCTS_LOADED,
      payload: [],
    });
  });
};

export const DELETE_SELECTED_PRODUCT = 'DELETE_SELECTED_PRODUCT';
export const deleteSelectedProduct = id => dispatch => {
  dispatch({
    type: DELETE_SELECTED_PRODUCT,
    payload: id,
  });
};

export const CHANGE_PRODUCT_COUNT = 'CHANGE_PRODUCT_COUNT';
export const changeProductCount = (id, newValue) => dispatch => {
  dispatch({
    type: CHANGE_PRODUCT_COUNT,
    payload: {
      id,
      newValue,
    },
  });
};
