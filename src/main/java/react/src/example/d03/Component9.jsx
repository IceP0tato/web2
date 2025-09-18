import axios from 'axios';

export default function Component9(props) {
    // axios : react 에서 주로 사용되는 REST API 비동기 통신 함수
    // https://www.npmjs.com/ (npm install axios)

    const onAxios1 = async () => {
        const response = await axios.get("https://jsonplaceholder.typicode.com/posts");
        console.log(response.status); // HTTP 응답 상태
        console.log(response.data); // HTTP 응답 자료

        const response2 = await axios.get("https://jsonplaceholder.typicode.com/comments?postId=1");
        console.log(response2.data);
    }

    const onAxios2 = async () => {
        const obj = {title: "test", body: "test", userId: 1};
        const response = await axios.post("https://jsonplaceholder.typicode.com/posts", obj);
        console.log(response.data);
    }

    const onAxios3 = async () => {
        const obj = {  id: 1,  title: 'foo', body: 'bar', userId: 1 }
        const response = await axios.put("https://jsonplaceholder.typicode.com/posts/1", obj);
        console.log(response.data);
    }

    const onAxios4 = async () => {
        const obj = {id: 1};
        const response = await axios.delete("https://jsonplaceholder.typicode.com/posts/1", obj);
        console.log(response.data);
    }

    return (<>
        <h3>axios</h3>
        <button onClick={onAxios1}>GET</button>
        <button onClick={onAxios2}>POST</button>
        <button onClick={onAxios3}>PUT</button>
        <button onClick={onAxios4}>DELETE</button>
    </>)
}