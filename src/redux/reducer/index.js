import { combineReducers } from 'redux';
import handleCart from './handleCart';
import products from './products';

const rootReducers = combineReducers({
  cart: handleCart,
  products,
});

export default rootReducers;
