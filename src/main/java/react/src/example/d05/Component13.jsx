import {useDispatch, useSelector} from 'react-redux'
import {login, logout} from './userSlice.jsx'

export default function Component13(props) {
    // store 에 저장된 상태 가져오기
    const dispatch = useDispatch();
    // (state) => state.상태명
    const {isAuthenticated} = useSelector((state) => state.user);
    console.log(isAuthenticated);

    // 로그인 처리
    const loginHandle = () => {
        // axios 처리 가정 후
        dispatch(login()); // dispatch 이용한 login 액션 요청
    }
    // 로그아웃 처리
    const logoutHandle = () => {
        // axios 처리 가정 후
        dispatch(logout()); // dispatch 이용한 logout 액션 요청
    }
    
    return (<>
        <h3>redux</h3>
        {
            isAuthenticated==true
            ?
                <div>
                    <p>로그인 상태입니다.</p>
                    <button onClick={logoutHandle}>로그아웃</button>
                </div>
            :
                <div>
                    <p>로그인 상태가 아닙니다.</p>
                    <button onClick={loginHandle}>로그인</button>
                </div>
        }          
    </>)
}