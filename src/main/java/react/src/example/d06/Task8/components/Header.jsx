import { Link } from "react-router-dom";

export default function App(props) {
    return (<>
        <div>
            <h3>헤더</h3>
            <ul>
                <li><Link to="/">HOME</Link></li>
                <li><Link to="/menu">메뉴</Link></li>
                <li><Link to="/cart">장바구니</Link></li>
            </ul>
        </div>
    </>)
}