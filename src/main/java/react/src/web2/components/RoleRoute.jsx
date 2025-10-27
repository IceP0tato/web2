import { useEffect, useState } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import axios from 'axios';

// 서버로부터 권한 확인 후, 권한에 따른 컴포넌트 제약

export default function RoleRoute(props) {
    console.log(props); // 상위 컴포넌트에서 해당 컴포넌트로 전달받은 속성들

    const [auth, setAuth] = useState({isAuth: null, urole: null});

    // 서버로부터 권한 요청
    const checkAuth = async () => {
        try {
            // withCredentials : httpOnly 쿠키를 자동 포함하기 위한 필수 옵션
            const res = await axios.get("http://localhost:8080/api/user/check", {withCredentials: true});
            setAuth(res.data);
            console.log(res.data);
        } catch (error) {
            setAuth({isAuth: false, urole: null});
        }
    }

    // 최초 렌더링에서 권한 검증
    useEffect(() => { checkAuth() }, []);

    // 서버로부터 권한을 받지 못한 경우
    if (auth.isAuth == null) return <div>권한 확인 중</div>

    // 로그인(쿠키/토큰)하지 않은 경우 페이지 이동
    if (auth.isAuth == false) return <Navigate to="/login" />;

    // 상위 컴포넌트(App.jsx)로부터 전달받은 권한 중에 서버가 전달한 권한이 없으면 권한 없음
    if (!props.roles.includes(auth.urole)) return <Navigate to="/forbidden" />;

    // 이상이 없으면 자식 컴포넌트 보여주기
    return <Outlet />;
}