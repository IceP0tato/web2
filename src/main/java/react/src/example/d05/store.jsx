import { configureStore } from "@reduxjs/toolkit";
import userReducer from './userSlice.jsx'

// 여러 개의 slice 들을 하나의 store 에서 관리

// store 생성 : 모든 컴포넌트에서 store 참조하여 저장된 슬라이스 사용
// ({reducer : {상태명 : 슬라이스명, 상태명2 : 슬라이스명2}})
const store = configureStore({
    reducer : {
        user : userReducer // user 상태에 개발자가 만든 슬라이스를 대입
    }
});

// store 를 다른 컴포넌트가 사용할 수 있도록 export
export default store;