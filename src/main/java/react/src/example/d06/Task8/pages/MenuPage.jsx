import { useDispatch } from "react-redux";
import { addToCart } from "../store/cartSlice";

export default function MenuPage(props) {
    const menu = [
        { id: 1, name: "아메리카노", price: 3000 }, 
        { id: 2, name: "카페라떼", price: 4000 },
        { id: 3, name: "카푸치노", price: 4500 },
    ];

    const dispatch = useDispatch();

    const add = (item) => {
        dispatch(addToCart(item));
        alert('물품이 장바구니에 담겼습니다.');
    }

    
    return (<>
        <h3>메뉴 페이지</h3>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>가격</th>
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
                {
                    menu.map((item) => (
                        <tr key={item.id}>
                            <td>{item.id}</td>
                            <td>{item.name}</td>
                            <td>{item.price}</td>
                            <td><button onClick={() => {add(item)}}>담기</button></td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    </>)
}