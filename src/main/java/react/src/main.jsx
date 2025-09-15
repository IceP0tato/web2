/* import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
*/

// 1. React root 함수 호출
import { createRoot } from 'react-dom/client'
// 2. index.html에서 root 마크업 가져오기
const root = document.querySelector('#root');
// 3. 가져온 root 마크업에 렌더링할 객체 생성
const create = createRoot(root);
// 렌더링할 컴포넌트 가져오기
import App from './App.jsx'
// 4. 렌더링하기
create.render(<App />);

// 요약 : createRoot(document.querySelector('#root')).render(<[최초 출력할 함수명] />);
