// Slice : 상태, 리듀서, 액션 정의

import { createSlice } from "@reduxjs/toolkit";

const initialState = {isAuthenticated : false, userInfo : null}; // 로그인 여부, 사용자 정보

const userSlice = createSlice({
    name : "user", // 상태명
    initialState,
    reducers : {
        login : (state, action) => {
            state.isAuthenticated = true;
            // action 시 전달되는 매개변수(action)의 payload 안의 값을 가져옴
            // dispatch(login("안녕")) -> payload = "안녕"
            state.userInfo = action.payload;
        },
        logout : (state) => {
            state.isAuthenticated = false;
            state.userInfo = null;
        }
    }
});

export default userSlice.reducer;
export const {login, logout} = userSlice.actions;