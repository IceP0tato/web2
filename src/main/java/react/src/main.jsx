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
// import App from './App.jsx'
// 4. 렌더링하기
// create.render(<App />);

/*
import Component1 from './example/d01/Component1';
create.render(<Component1 />);
import Component2 from './example/d01/Component2';
create.render(<Component2 />); // render는 1번만 가능 (Component1 은 화면에 출력되지 않음)
import Component3 from './example/d01/Component3';
create.render(<Component3 />);
*/
import Task5 from './example/d03/Task5';
create.render(<Task5 />);


// 요약 : createRoot(document.querySelector('#root')).render(<[최초 출력할 함수명] />);
