// 퍼시스턴스 : 로컬/세션 스토리지에 상태 유지하는 방법
// npm i redux-persist

// redux-persist 설정
import storage from 'redux-persist/lib/storage' // localStorage
import storageSession from 'redux-persist/lib/storage/session' // sessionStorage
const persistConfig = {key : 'user', storage}; // localStorage 에 'user'라는 이름으로 상태 저장

// Store : 여러 개의 상태를 보관하는 저장소. 1개만 존재함
import { configureStore } from "@reduxjs/toolkit";
import userSlice from './userSlice.jsx'

// 리듀서에서 persist 설정
import {persistStore, persistReducer} from 'redux-persist'
const persistedReducer = persistReducer(persistConfig, userSlice);

const store = configureStore({
    reducer : {
        // user : userSlice 퍼시스턴스 적용 전
        user : persistedReducer // 퍼시스턴스 적용 후
    }
});

export default store;
// 퍼시스턴스 스토어 내보내기
export const persistor = persistStore(store);