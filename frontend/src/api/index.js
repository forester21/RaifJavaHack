import Cookies from 'js-cookie';

const csrfToken = Cookies.get('csrftoken');

const get = url => fetch(url);

const post = (url, data) => {
  console.log(data);
  return fetch(url, {
    method: 'POST',
    body: JSON.stringify(data),
    headers: {
      'Content-Type': 'application/json',
      'X-CSRFToken': csrfToken,
    },
  });
};

export const getProducts = () => get('/api/products');

export const getCategories = () => get('/api/categories');

export const generateQRCode = productsIds => post('/api/products', productsIds);
