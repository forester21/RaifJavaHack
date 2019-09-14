import * as Actions from 'actions';
import { remove, find } from 'lodash';

import { selectedProducts as mock } from 'mocks';

const initialState = {
  products: [],
  categories: [],
  selectedProducts: mock,
  QRCodeUrl: null,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case Actions.PRODUCTS_LOADED: {
      return {
        ...state,
        products: action.payload,
      };
    }

    case Actions.CATEGORIES_LOADED: {
      return {
        ...state,
        categories: action.payload,
      };
    }

    case Actions.DELETE_SELECTED_PRODUCT: {
      const restSelectedProducts = remove(
        state.selectedProducts,
        item => action.payload !== item.key
      );

      return {
        ...state,
        selectedProducts: restSelectedProducts,
      };
    }

    case Actions.CHANGE_PRODUCT_COUNT: {
      const { id, newValue } = action.payload;
      const product = find(state.selectedProducts, ['key', id]);
      product.count = newValue;
      product.totalPrice = product.price * newValue;

      return {
        ...state,
        selectedProducts: [...state.selectedProducts],
      };
    }

    case Actions.QR_CODE_LOADED: {
      return {
        ...state,
        QRCodeUrl: action.payload,
      };
    }

    default: {
      return state;
    }
  }
};
