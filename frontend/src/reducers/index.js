import * as Actions from 'actions';
import { remove, find } from 'lodash';

const initialState = {
  products: [],
  categories: [],
  selectedProducts: [],
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

    case Actions.ADD_SELECTED_PRODUCT: {
      const prod = find(state.products, ['id', action.payload]);
      const alreadySelectdProduct = find(state.selectedProducts, ['key', action.payload]);

      if (alreadySelectdProduct) {
        alreadySelectdProduct.count = alreadySelectdProduct.count + 1;
        alreadySelectdProduct.totalPrice =
          alreadySelectdProduct.totalPrice + alreadySelectdProduct.price;
        return {
          ...state,
          selectedProducts: [...state.selectedProducts],
        };
      }

      const newSelectedProduct = {
        key: prod.id,
        name: prod.name,
        price: prod.price,
        count: alreadySelectdProduct ? alreadySelectdProduct.count + 1 : 1,
        totalPrice: prod.price,
      };

      return {
        ...state,
        selectedProducts: [...state.selectedProducts, newSelectedProduct],
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
