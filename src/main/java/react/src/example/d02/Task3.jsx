import { useState } from "react";

export default function Task3(props) {
    const [count, setCount] = useState(0);
    const [name, setName] = useState('');

    const increase = () => {
        setCount(count + 1);
    }

    const decrease = () => {
        setCount(count - 1);
    }

    const changeName = (e) => {
        setName(e.target.value);
    }

    return (<>
        <h3>Task3</h3>
        제품명: <input value={name} onChange={changeName} /> <br />
        현재 수량: <span>{count}</span> <br />
        <button onClick={decrease}>감소</button><button onClick={increase}>증가</button>
    </>)
}

