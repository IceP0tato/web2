import { useSelector } from "react-redux"
import Table from '@mui/joy/Table';

export default function CartPage(props) {
    // 상태에서 'cart' 라는 상태의 객체 (cartItem) 를 가져옴
    const cart = useSelector((state) => state.cart.cartList);
    
    return (<>
        <h3>장바구니 페이지</h3>
        <Table aria-label="basic table">
            <thead>
                <tr>
                    <th>제품명</th>
                    <th>수량</th>
                    <th>가격</th>
                </tr>
            </thead>
            <tbody>
                {
                    cart.map((item) => (
                        <tr key={item.id}>
                            <td>{item.name}</td>
                            <td>{item.amount}</td>
                            <td>{item.price * item.amount}</td>
                        </tr>
                    ))
                }
            </tbody>
        </Table>
    </>)
}