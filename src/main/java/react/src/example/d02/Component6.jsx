import { useState } from "react";

export default function Component6(props) {
    // React 상태 관리 라이브러리 함수
    // useState(초기값)
    const state = useState(0);
    console.log(state);
    console.log(state[0]); // 데이터 : useState 가 관리하는 값
    console.log(state[1]); // 값이 변경되면 실행되는 재렌더링 함수

    const StateChange = () => {
        state[0] = 1;
        state[1](2);
        console.log(state);
    }

    return (<>
        <h3>state 관리</h3>
        <h4>{state[0]}</h4>
        <button onClick={StateChange}>변경</button>
    </>)
}