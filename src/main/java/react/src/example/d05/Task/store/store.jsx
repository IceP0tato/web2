// Store : 여러 개의 상태를 보관하는 저장소. 1개만 존재함

import { configureStore } from "@reduxjs/toolkit";
import userReducer from './userSlice.jsx'

const store = configureStore({
    reducer : {
        user : userReducer
    }
});

export default store;