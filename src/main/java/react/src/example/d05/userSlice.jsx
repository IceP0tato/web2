import {createSlice} from '@reduxjs/toolkit'

// redux
// npm i @reduxjs/toolkit , npm i react-redux

// 전역변수 초기값
const initialState = {isAuthenticated : false};

// 상태를 변경하는 리듀서 함수 정의
const userSlice = createSlice({
    // name : slice 이름, 초기값, reducers : {액션함수명 : (state) => {}}
    name : "user", // slice (하나의 저장소(store)에 저장되는 일부분의 값) 이름
    initialState, // 정의한 객체로 초기값 설정 (노출이 위험한 정보 제외)
    reducers : {
        login : (state) => {state.isAuthenticated = true}, // 로그인 액션 (로그인 함수 실행 시 처리되는 코드)
        logout : (state) => {state.isAuthenticated = false} // 로그아웃 액션 (로그아웃 함수 실행 시 처리되는 코드)
    }
})

// store 에 리듀서를 import 할 수 있게 처리
export default userSlice.reducer
// 다른 컴포넌트에서 로그인과 로그아웃 액션 사용 가능하도록 내보내기
export const {login, logout} = userSlice.actions;