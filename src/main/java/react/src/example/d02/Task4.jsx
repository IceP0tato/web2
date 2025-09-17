import { useState } from "react"

export default function Task4(props) {
    const [name, setName] = useState('');
    const [phone, setPhone] = useState('');
    const [age, setAge] = useState('');
    const [list, setList] = useState([]);

    const addItem = (props) => {
        setList([...list, {name, phone, age}]);
    }

    const deleteItem = (props) => {

    }
    
    return (<>
        <h2>전화번호부</h2>
        <input onChange={(e) => {setName(e.target.value)}} value={name} placeholder="성명" />
        <input onChange={(e) => {setPhone(e.target.value)}} value={phone} placeholder="연락처 (예: 010-1234-5678)" />
        <input onChange={(e) => {setAge(e.target.value)}} value={age} placeholder="나이" />
        <button onClick={addItem}>등록</button>

        <ul>
            {list.map((item) => {
                <li>성명: {item.name} 연락처: {item.phone} 나이: {item.age}<button onClick={deleteItem}>삭제</button></li>
            })}
        </ul>
        <h5>총 {list.length}명</h5>
    </>)
}
