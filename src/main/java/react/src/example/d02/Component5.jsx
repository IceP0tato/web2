
export default function Component5(props) {
    const items = ['사과', '바나나', '딸기'];

    // forEach : return 없음
    const newItems = items.forEach((item) => {console.log(item); return item;});
    console.log(newItems);

    // map : return 있음
    const newItems2 = items.map((item) => {console.log(item); return item;});
    console.log(newItems2);

    const onAdd = () => {
        items.push('수박');
        console.log(items);
    }

    // item 을 추가해도 html 은 변하지 않음 -> 훅의 useState 를 사용하여 해결 가능
    return (<>
        <h3>JSX 반복문</h3>
        <ul>
            {items.map((item) => {return <li>{item}</li>})}
        </ul>
        <button onClick={onAdd}>item 추가</button>
    </>)
}