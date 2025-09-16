  const products = [
    { title: "무선 키보드", price: 45000, inStock: true },
    { title: "게이밍 마우스", price: 32000, inStock: false },
    { title: "27인치 모니터", price: 280000, inStock: true }
  ];
  
// CSS 파일 불러오기
import './Task2.css';

export default function Task2(props) {
    return (<>
        <div class="products">
            <Data product={products[0]} />
            {/* <Data title={products[0].title} price={product[0].price} inStock={products[0].inStock} /> */}
            <Data product={products[1]} />
            <Data product={products[2]} />
        </div>
    </>)
}

function Data(props) {
    const title = props.product.title;
    const price = props.product.price.toLocaleString();
    const inStock = props.product.inStock==true?
        <span style={{color: 'green'}}>재고 있음</span>:<span style={{color: 'red'}}>재고 없음</span>;
    // 구문 분해 방법 : const {title, price, inStock} = props.product;

    return (<>
        <ul>
            <li class="title">{title}</li>
            <li class="price">가격 : {price}</li>
            <li class="isStock">{inStock}</li>
        </ul>
    </>)
}


