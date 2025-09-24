import { useSelector } from "react-redux"

export default function CartPage(props) {
    const cart = useSelector((state) => state.cart);
    
    return (<>
        <h3>장바구니 페이지</h3>
        <table>
            <thead>
                <tr>
                    <th>제품명</th>
                    <th>담은 수량</th>
                    <th>금액</th>
                </tr>
            </thead>
            <tbody>
                {
                    cart.map((item) => (
                        <tr kay={item.id}>
                            <td>{item.name}</td>
                            <td>{}</td>
                            <td>{item.price}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    </>)
}