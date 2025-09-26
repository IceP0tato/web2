import { persistReducer, persistStore } from 'redux-persist'
import storage from 'redux-persist/lib/storage'
import cartReducer from './cartSlice.js'
import { configureStore } from '@reduxjs/toolkit';

const persistConfig = {key : 'cart', storage}
const persistedReducer = persistReducer(persistConfig, cartReducer);

const store = configureStore({
    reducer : {
        cart : persistedReducer
    }
});

export default store;
export const persistor = persistStore(store);