import { useDispatch } from "react-redux";
import { login } from "../store/userSlice.jsx";
import { useNavigate } from "react-router-dom";

export default function LoginPage(props) {
    // 상태 변경을 하기 위한 함수
    const dispatch = useDispatch();
    // 가상 URL 로 페이지 전환 함수
    const navigate = useNavigate();

    const onLogin = () => {
        const obj = {id: 3, name: "유재석"}; // 로그인 데이터 (가정)
        alert('로그인 성공');

        dispatch(login(obj)); // 매개변수를 포함한 login 액션 요청
        navigate("/");
    }

    return (<>
        <h3>로그인 페이지</h3>
        <button onClick={onLogin}>로그인</button>
    </>)
}