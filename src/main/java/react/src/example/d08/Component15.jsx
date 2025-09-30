import axios from 'axios';
import { useRef } from 'react';

export default function Component15(props) {
    const axios1 = async () => {
        try {
            const response = await axios.get("http://localhost:8080/axios");
            const data = response.data;
            console.log(data);
        } catch (e) {
            console.log(e);
        }
    }

    const axios2 = async () => {
        try {
            const obj = { id: "qwe", password: "1234" };
            const option = { withCredentials : true }; // HTTP 인증(세션 유지) 허용
            const response = await axios.post("http://localhost:8080/axios/login", obj, option);
            const data = response.data;
            console.log(data);
        } catch (e) {
            console.log(e);
        }
    }

    const axios3 = async () => {
        try {
            const option = { withCredentials : true }; // HTTP 인증(세션 유지) 허용
            const response = await axios.get("http://localhost:8080/axios/info", option);
            const data = response.data;
            console.log(data);
        } catch (e) {
            console.log(e);
        }
    }

    // form은 name 속성으로 매핑
    const formRef1 = useRef();
    const axios4 = async () => {
        // axios : 기본 전송 타입 'json' (fetch 는 'form')
        try {
            const form = formRef1.current;
            const option = { 
                headers : {"Content-Type" : "application/x-www-form-urlencoded"}
            }
            const response = await axios.post("http://localhost:8080/axios/form", form, option);
            const data = response.data;
            console.log(data);
        } catch (e) {
            console.log(e);
        }
    }

    const formRef2 = useRef();
    const axios5 = async () => {
        try {
            const form = formRef2.current;
            const formData = new FormData(form); // 폼 데이터를 바이너리(바이트) 폼으로 변환
            const option = {
                headers : {"Content-Type" : "multipart/form-data"}
            }
            const response = await axios.post("http://localhost:8080/axios/formdata", formData, option);
            const data = response.data;
            console.log(data);
        } catch (e) {
            console.log(e);
        }
    }
    
    return (<>
        <h5>AXIOS</h5>
        <button onClick={axios1}>axios1</button>
        <button onClick={axios2}>axios2</button>
        <button onClick={axios3}>axios3</button>
        <form ref={formRef1}>
            <input name="id" /> <br />
            <input name='password' /> <br />
            <button onClick={axios4} type="button">axios4</button>
        </form>
        <form ref={formRef2}>
            <input type="file" name="file" /> <br />
            <button onClick={axios5} type="button">axios5</button>
        </form>
    </>)
}