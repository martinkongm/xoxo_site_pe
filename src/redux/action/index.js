// redux/action.js
export const addCart = (product) => ({
    type: 'ADDITEM',
    payload: product,
  });
  
  export const delCart = (product) => ({
    type: 'DELITEM',
    payload: product,
  });
  