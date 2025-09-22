import { BrowserRouter, Route, Routes, Link, useSearchParams, useParams, useNavigate } from 'react-router-dom'

function Home(props) { return (<>Main Page</>)}
function About(props) { return (<>About Page</>)}
function MyPage(props) {
    // React queryString ('/mypage?name=유재석&age=40')
    const [searchParams] = useSearchParams();
    const name = searchParams.get('name');
    const age = searchParams.get('age');
    
    return (<>
        <h3>My Page</h3>
        <p>name : {name}</p>
        <p>age : {age}</p>
    </>)
}
function Product(props) {
    // React path ('/product/coke/1000')
    const {name, price} = useParams();
    return (<>
        <h3>Product Page</h3>
        <p>name : {name}</p>
        <p>price : {price}</p>
    </>)
}
function Page404(props) {
    const navigate = useNavigate();
    const Move = () => {
        navigate('/');
    }

    return (<>
        <h3>존재하지 않는 페이지입니다.</h3>
        <Link to='/'>Main</Link>
        <button onClick={Move}>Home</button>
    </>)
}

// 라우터 : 하나의 컴포넌트가 여러 컴포넌트를 연결 구조 (가상의 URL 만들기)
// 설치 : npm i react-router-dom

export default function Component12(props) {
    return (<>
        <BrowserRouter>
            <ul>
                <a href='/'>Main(Html) | </a>
                <Link to='/'>Main(React) | </Link> {/* 가상의 URL로 이동할 때 사용 */}
                <Link to='/about'>About | </Link>
                <Link to='/mypage'>My Page | </Link>
                <Link to='/mypage?name=유재석&age=40'>My Page (QS) | </Link>
                <Link to='/product'>Product | </Link>
                <Link to='/product/coke/1000'>Product (path)</Link>
            </ul>
            <Routes> {/* 가상의 URL를 정의하고, 정의한 URL과 매핑할 컴포넌트 정의 */}
                <Route path='/' element={<Home />} />
                <Route path='/about' element={<About />} />
                <Route path='/mypage' element={<MyPage />} />
                <Route path='/product/:name/:price' element={<Product />} />
                {/* 존재하지 않는 가상 URL 요청 시 404 컴포넌트 */}
                <Route path='*' element={<Page404 />} />
            </Routes>
        </BrowserRouter>
    </>)
}