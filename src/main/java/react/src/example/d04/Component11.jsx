import { useEffect, useRef, useState } from "react";

export default function Component11(props) {
    // useRef : 렌더링하지 않고 데이터를 참조하는 hook
    const inputRef = useRef(null);

    const add = () => {
        console.log(inputRef); // 현재 참조중인 객체 정보
        console.log(inputRef.current); // 참조값 (<input>)
        inputRef.current.focus(); // focus(마우스 커서)
        console.log(inputRef.current.value); // 값 (단순 입력에 사용)
    }

    const [count, setCount] = useState(0);
    const countRef = useRef(count);
    useEffect(() => {countRef.current = count;}, [count]);

    const formRef = useRef();
    const send = () => {
        console.log(formRef.current);
        console.log(formRef.current.elements['textData'].value);
    }
    
    return (<>
        <h3>useRef1</h3>
        <input ref={inputRef} />
        <button onClick={add}>등록</button>

        <h3>useRef2</h3>
        <p>count : {count}, Previous count : {countRef.current}</p>
        <button onClick={(e) => {setCount(count+1);}}>증가</button>

        <h3>useRef3</h3>
        <form ref={formRef}>
            <input name="textData" />
            <select name="selectData">
                <option>바나나</option>
            </select>
            <textarea name="longtextData"></textarea>
            <button onClick={send} type="button">폼 전송</button>
        </form>
    </>)
}