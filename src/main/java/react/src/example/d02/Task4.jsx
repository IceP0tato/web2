import { useRef, useState } from "react"

export default function Task4() {
    const [name, setName] = useState('');
    const [phone, setPhone] = useState('');
    const [age, setAge] = useState('');
    const [list, setList] = useState([]);
    const id = useRef(0);
    
    const addItem = () => {
        id.current += 1;
        setList([...list, {id: id.current, name, phone, age}]);
    }

    const deleteItem = (id) => {
        const newList = list.filter((item) => item.id != id);
        setList(newList);
    }
    
    return (<>
        <h2>전화번호부</h2>
        <input onChange={(e) => {setName(e.target.value)}} value={name} placeholder="성명" />
        <input onChange={(e) => {setPhone(e.target.value)}} value={phone} placeholder="연락처 (예: 010-1234-5678)" />
        <input onChange={(e) => {setAge(e.target.value)}} value={age} placeholder="나이" />
        <button onClick={addItem}>등록</button>

        <ul>
            {list.map((item) => (
                <li key={item.id}>
                    성명: {item.name}&nbsp;&nbsp;
                    연락처: {item.phone}&nbsp;&nbsp;
                    나이: {item.age}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button onClick={() => deleteItem(item.id)}>삭제</button>
                </li>
            ))}
        </ul>
        <h5>총 {list.length}명</h5>
    </>)
}
