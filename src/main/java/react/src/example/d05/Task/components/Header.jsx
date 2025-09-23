import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { logout } from "../store/userSlice.jsx";

export default function Header(props) {
    const {isAuthenticated, userInfo} = useSelector((state) => state.user);

    const dispatch = useDispatch();
    const navigate = useNavigate();
    
    const onLogout = () => {
        alert('로그아웃 성공');
        dispatch(logout());
        navigate("/login");
    }

    return (<>
        <div>
            <h3>헤더</h3>
            <ul>
                <li><Link to="/">홈</Link></li>
                {isAuthenticated?
                    <>
                        <li><span>안녕하세요, {userInfo.name}님</span></li>
                        <li><Link to="/profile">프로필</Link></li>
                        <li><Link onClick={onLogout} to="/login">로그아웃</Link></li>
                    </>
                    :
                    <>
                        <li><Link to="/login">로그인</Link></li>
                    </>
                }
            </ul>
        </div>
    </>)
}