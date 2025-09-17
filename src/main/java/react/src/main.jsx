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
import Task1 from './example/d01/Task1';
create.render(<Task1 />);
import Task2 from './example/d01/Task2';
create.render(<Task2 />);
import Component4 from './example/d02/Component4';
create.render(<Component4 />);
import Component5 from './example/d02/Component5';
create.render(<Component5 />);
import Component6 from './example/d02/Component6';
create.render(<Component6 />);
import Component7 from './example/d02/Component7';
create.render(<Component7 />);
import Task3 from './example/d02/Task3';
create.render(<Task3 />);
*/
import Task4 from './example/d02/Task4';
create.render(<Task4 />);



// 요약 : createRoot(document.querySelector('#root')).render(<[최초 출력할 함수명] />);
