// JSX 템플릿 (React 내장) : {}

export default function Component3(props) {
    let name = "유재석";
    // return 부터는 jsx 문법 (이전까지는 js 코드)
    // 함수의 반환값은 무조건 1개 (2개 이상 해야 하면 소괄호와 태그 (<></>)로 묶어야 함)
    return (<>
        <div>{name}입니다.</div>
        <div>test</div>
        {/* 다른 컴포넌트 포함하기 */}
        <SubCom1 key1="value1" key2="40" />
    </>);
}

function SubCom1(props) {
    const obj = {name: "강호동", age: 50};
    console.log(obj);
    console.log(props);
    // props 구조 분해
    const {key1, key2} = props;
    return (<>
        <h4>{obj.name} 님의 나이는 {obj.age}입니다.</h4>
        <h4>{props.key1} 님의 나이는 {props.key2}입니다.</h4>
        <h4>{key1} 님의 나이는 {key2}입니다.</h4>
    </>)
}