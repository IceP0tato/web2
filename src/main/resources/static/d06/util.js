const hello = (name) => {
    return name + "님";
}
// default 내보내기는 1개만 가능
export default hello;
// 이름을 붙인 내보내기 (named export : 여러 개 가능)
export const PI = 3.14;
export const E = 2.71;