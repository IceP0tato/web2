import { useEffect, useState } from "react"

export default function Component8(props) {
    // React Hook (useState 등)
    const [count, setCount] = useState(0);

    // useEffect(정의함수) : 특정 시점(생명 주기)에 함수 실행
    // 시점 : 컴포넌트 탄생 (mount), 인생/업데이트 (update), 죽음 (컴포넌트가 화면에서 사라짐, unmount)
    // [] : 의존성 배열 (상태 변수를 대입하여 상태 변수가 재렌더링하면 useEffect 실행)
    useEffect(() => {console.log('useEffect - Component mount')}, []); // 최초 1회 실행
    useEffect(() => {console.log('useEffect - Component update')}, [count]); // count -> setCount 되면 useEffect 실행
    useEffect(() => {console.log('useEffect - Component mount/update')}); // 최초 실행 및 재실행

    const [count2, setCount2] = useState(0);

    return (<>
        <h3>useEffect : {count}</h3>
        <button onClick={() => {setCount(count+1)}}>버튼1</button>
        <button onClick={() => {setCount2(count2+1)}}>버튼2</button>
    </>)
}