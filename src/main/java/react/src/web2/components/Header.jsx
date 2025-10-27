import { useEffect, useState } from "react"
import axios from "axios";
import { Link } from "react-router-dom";

export default function Header() {
    // 로그인된 유저 정보 저장
    const [user, setUser] = useState(null);

    // 최초 컴포넌트 실행 시 유저 정보 요청
    const getMyInfo = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/user/myInfo", {withCredentials: true});
            setUser(res.data);
        } catch (error) {
            setUser(null);
        }
    }

    useEffect(() => { getMyInfo() }, []);
    
    // 로그아웃 요청
    const getLogout = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/user/logout", {withCredentials: true});
            alert('로그아웃 되었습니다.');
            location.href="/login"; // 접근 권한 변경에 따라 navigate (클라이언트 사이드 렌더링) 대신에 서버 사이드 렌더링을 사용해야 함
        } catch (error) {
            
        }
    }

    return (<>
        <div>
            <nav>
                {user?<>
                    {/* 로그인 상태 */}
                    <Link to="/">홈 </Link>
                    <span>{user.uid} 님 </span>
                    <button onClick={getLogout}>로그아웃 </button>
                    <Link to="/user/info">마이페이지 </Link>
                    {user.urole == "ADMIN"?<>
                        {/* 관리자 로그인 상태 */}
                        <Link to="/admin/dashboard">관리자 페이지 </Link>
                    </>:<></>}
                </>:<>
                    {/* 비로그인 상태 */}
                    <Link to="/">홈 </Link>
                    <Link to="/login">로그인 </Link>
                    <Link to="/signup">회원가입 </Link>
                </>}
            </nav>
        </div>
    </>)
}