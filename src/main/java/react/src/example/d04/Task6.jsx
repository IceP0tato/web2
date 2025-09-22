import { useRef } from "react";
import { BrowserRouter, Link, Route, Routes, useNavigate } from "react-router-dom";

function HomePage(props) {
    return (<>
        <div>
            <h3>홈 페이지</h3>
            <p>좌측 메뉴에서 회원가입 또는 로그인으로 이동해보세요.</p>
        </div>
    </>)
}

function SignupPage(props) {
    const idRef = useRef(null);
    const pwdRef = useRef(null);
    // 라우터 전용 페이지 전환 함수
    const navigate = useNavigate();

    const signup = async () => {
        const id = idRef.current.value;
        const pwd = pwdRef.current.value;
        const obj = {id, pwd};
        
        // axios 회원가입 성공 시 (가정)
        alert('회원가입 성공');
        navigate('/login'); // 라우터 방식의 페이지 전환 (location.href : HTML 방식)
    }

    return (<>
        <div>
            <h3>회원가입 페이지</h3>
            <form>
                <input ref={idRef} placeholder="아이디" />
                <input ref={pwdRef} placeholder="비밀번호" />
                <button onClick={signup} type="button">회원가입</button>
            </form>
        </div>
    </>)
}

function LoginPage(props) {
    const formRef = useRef(null);
    // 라우터 전용 페이지 전환 함수
    const navigate = useNavigate();

    const login = async () => {
        const id = formRef.current.elements['id'].value;
        const pwd = formRef.current.elements['pwd'].value;
        // axios 처리 완료 후 (로그인 성공 시, 가정)
        if (id == 'admin' && pwd == '1234') {
            alert('로그인 성공');
            navigate('/'); // 라우터 방식의 페이지 전환 (location.href : HTML 방식)
        } else {
            alert('로그인 실패');
        }
    }

    return (<>
        <div>
            <h3>로그인 페이지</h3>
            <form ref={formRef}>
                <input name="id" placeholder="아이디" />
                <input name="pwd" placeholder="비밀번호" />
                <button onClick={login} type="button">로그인</button>
            </form>
        </div>
    </>)
}

import './Task6.css'
export default function Task6(props) {
    return (<>
        <BrowserRouter>
            <div className="container">
                <ul>
                    <li><Link to='/'>홈</Link></li>
                    <li><Link to='/signup'>회원가입</Link></li>
                    <li><Link to='/login'>로그인</Link></li>
                </ul>
                <div>
                    {/* 렌더링되는 곳 */}
                    <Routes>
                        <Route path='/' element={<HomePage />} />
                        <Route path='/signup' element={<SignupPage />} />
                        <Route path='/login' element={<LoginPage />} />
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
    </>)
}