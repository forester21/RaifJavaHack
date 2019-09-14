import { batch } from 'react-redux';

import * as api from 'api';

export const PRODUCTS_LOADING = 'PRODUCTS_LOADING';
export const PRODUCTS_LOADED = 'PRODUCTS_LOADED';

export const CATEGORIES_LOADING = 'CATEGORIES_LOADING';
export const CATEGORIES_LOADED = 'CATEGORIES_LOADED';

export const loadProductsAndCategories = () => dispatch => {
  const promises = [api.getProducts(), api.getCategories()];
  Promise.all(promises)
    .then(responses => Promise.all(responses.map(response => response.json())))
    .then(results => {
      batch(() => {
        dispatch({
          type: PRODUCTS_LOADED,
          payload: results[0],
        });

        dispatch({
          type: CATEGORIES_LOADED,
          payload: results[1],
        });
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

export const requestGenerateQRCode = () => (dispatch, getState) => {
  const productsIds = getState().selectedProducts.map(product => ({
    id: product.key,
    count: product.count,
  }));

  api.requestGenerateQRCode(productsIds);
};

export const QR_CODE_LOADED = 'QR_CODE_LOADED';
export const getQRCode = () => dispatch => {
  api
    .getQRCode()
    .then(res => res.blob())
    .then(blob => {
      const objectURL = URL.createObjectURL(blob);

      dispatch({
        type: QR_CODE_LOADED,
        payload: objectURL,
      });
    });
};
