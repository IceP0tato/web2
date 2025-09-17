
export default function Component4(props) {
    const obj = {name: "유재석", age: 40};
    
    return (<>
        <h3>{obj.name}님의 나이는 {obj.age}입니다.</h3>
        <SubComp key1="value1" key2="value2" />
        <SubComp name="유재석" age={40} />
        <SubComp name={obj.name} age={obj.age} />
    </>)
}

function SubComp(props) {
    console.log(props);
    return (<>
        <h4>SubComp</h4>
        <SubComp2 key3="value3" />
    </>)
}

let count2 = 0; // 전역 변수
function SubComp2(props) {
    console.log(props);

    let count = 0; // 지역 변수
    const onAdd = () => {
        count++;
        count2++;
        console.log("count : " + count);
        console.log("count2 : " + count2);
    }

    return (<>
        <h6>SubComp2</h6>
        {/* JSX 에서는 onClick (대문자) */}
        <button onClick={onAdd}>버튼</button>
        {/* HTML 불변성으로 인해 count, count2 는 변하지 않음 */}
        <h6>count : {count}, count2 : {count2}</h6>
    </>)
}