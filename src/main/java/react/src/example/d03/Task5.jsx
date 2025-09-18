import axios from "axios";
import { useEffect, useState } from "react"

// TASK5 : 기존 TASK4.jsx 이어 useEffect/axios를 활용해서 spring+mybatis 서버 와 통신하여 TASK5 완성(등록/전체조회/삭제)하시오.

export default function Task5( props ){
    const [name, setName] = useState('');
    const [phone, setPhone] = useState('');
    const [age, setAge] = useState('');
    const [list, setList] = useState([]);

    useEffect(() => {printMember()}, []);

    const addMember = async () => {
        const obj = {name, phone, age};
        const response = await axios.post("http://localhost:8080/task5", obj);
        console.log(response.status);
        printMember();
    }

    const printMember = async () => {
        const response = await axios.get("http://localhost:8080/task5");
        setList(response.data);
    }

    const deleteMember = async (mno) => {
        const response = await axios.delete("http://localhost:8080/task5?mno="+mno);
        console.log(response.status);
        printMember();
    }

    return (<>
        <div>성명 : <input value={name} onChange={(e) => {setName(e.target.value)}} /></div>
        <div>연락처 : <input value={phone} onChange={(e) => {setPhone(e.target.value)}}/></div>
        <div>나이 : <input value={age} onChange={(e) => {setAge(e.target.value)}} /></div>
        <div><button onClick={addMember}>등록</button></div>

        <hr />
        <div>
        {   
            list.map((obj) => {
                return <div key={obj.mno}>
                        이름 : {obj.name}, 연락처 : {obj.phone}, 나이 : {obj.age}
                        <button onClick={() => {deleteMember(obj.mno)}}>삭제</button>
                    </div>
            })
        }
        </div>
    </>)
}

