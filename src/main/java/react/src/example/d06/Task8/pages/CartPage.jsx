import { useSelector } from "react-redux"

export default function CartPage(props) {
    const cart = useSelector((state) => state.cart.cartItem);
    
    return (<>
        <h3>장바구니 페이지</h3>
        <table>
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
        </table>
    </>)
}