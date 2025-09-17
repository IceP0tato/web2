import { useState } from "react";

export default function Component7(props) {
    // 구문 분해 이용한 useState 반환값 변수화
    const [count, setCount] = useState(0);
    const countAdd = () => {
        const value = count + 1;
        setCount(value); // Hook : 특정 기능을 실행하면 다른 기능들도 실행됨
    }

    const [array, setArray] = useState(['수박']);
    const arrayAdd = () => {
        array.push('사과');
        // useState 는 배열의 내용물 ('수박')이 아니라 배열 객체 ([])를 관리함 -> 주소 변경이 없으므로 재렌더링되지 않음
        // setArray(array);
        setArray([...array]);
    }

    const [data, setData] = useState('');
    const dataAdd = (event) => {
        // onChange 실행 시 이벤트 결과가 함수 매개변수로 전달됨
        console.log(event);
        console.log(event.target); // onChange 가 발동한 마크업
        console.log(event.target.value); // 마크업의 입력받은 값

        // 입력받은 값을 useState 로 변경
        setData(event.target.value);
    }

    return (<>
        <h3>useState 1 {count}</h3>
        <button onClick={countAdd}>count 증가</button>

        <h3>useState 2 {array}</h3>
        <button onClick={arrayAdd}>array 삽입</button>

        <h3>useState 3 </h3>
        <input value={data} onChange={dataAdd} />
        <input value={data} onChange={(e) => {setData(e.target.value)}} />
    </>)
}