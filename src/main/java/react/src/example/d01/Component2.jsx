// props : property 속성 <컴포넌트 속성=값 속성=값 />
// JSP (HTML + JAVA), JSX (HTML + JS)

function Component2(props) {
    const name = "유재석";
    return <div><Header />메인 페이지<Footer /></div>
}

function Header(props) {
    return <div>헤더 메뉴</div>
}

function Footer(props) {
    return <div>푸터 메뉴</div>
}

export default Component2;