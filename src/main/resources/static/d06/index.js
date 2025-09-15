var a = 10;
var a = 20; // var는 중복 선언 가능 (let, const는 불가)

const point = 90;
console.log(point >= 90 && "A"); // 참이면 A, 거짓이면 false
console.log(point >= 90 || "A"); // 참이면 true, 거짓이면 A

const array = [10, 20, 30, 40, 50];
for (let index in array) { console.log(array[index]); };
for (let value of array) { console.log(value); };
array.forEach((value) => { console.log(value); });
let array1 = array.map((value) => { console.log(value); return value; }); // map : forEach와 다르게 return이 가능
let array2 = array.filter((value) => { console.log(value); return value > 20; }); // filter : 조건부 return 가능

function func1(param1, param2) {} // 선언적 함수
const func2 = function(param1, param2) {} // 익명 함수
const func3 = (param1, param2) => {} // 화살표(람다) 함수
const func4 = (param1, param2 = "강호동") => {} // 매개변수 기본값 설정
func1(4, 10);
func2(10, "유재석");
func3(10, {name: "유재석"});
func4(10);

const name1 = "강호동";
const age1 = 50;
const obj1 = {name1, age1}; // key와 value의 변수명이 같으면 key 생략 가능
console.log(obj1);

// ... : 스프레드 연산자 (배열이나 객체를 복사할 때 사용 -> 주소값 변경 목적)
const obj2 = { ...obj1, phone: "010"};
console.log(obj2);
const obj3 = [name1, age1];
const obj4 = [ ...obj3];
console.log(obj3);
console.log(obj4); // 값은 같지만 새로운 주소값으로 복사됨
const obj5 = [ 6, 7, ...obj4 ];
console.log(obj5);

// 구조 분해 할당 : 객체나 배열에서 값을 분해하는 방법
const user = { name: "유재석", age: 40 };
const {name, age} = user; // 객체 내 key와 동일하게 상수/변수 선언하면 분해 가능
console.log(name);
console.log(age);

// 비구조화 할당과 나머지 연산자
const [num, num2, ...intArray] = [1, 2, 3, 4];
console.log(num); // 인덱스 순서대로 분해 후 나머지는 ...에 저장
console.log(num2);
console.log(intArray);

// async/fetch
// 비동기 (fetch 함수는 원래 비동기(new Promise) 래퍼)
const method1 = () => {
    fetch("url").then(response => response.json()).then(data => console.log(data));
}
// 동기
const method2 = async () => {
    const response = await fetch("url");
    const data = await response.json();
    console.log(data);
}

// *** Promise : await은 promise를 사용하는 함수들에 적용됨 ***
const promiseFunc = async () => {
    return await new Promise((resolve, reject) => { // resolve : 성공 매개변수, reject : 실패 매개변수
        if (10 > 13) { resolve("true"); }
        else { reject("false"); }
    });
}