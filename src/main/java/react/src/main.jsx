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

/*
import Component13 from './example/d05/Component13';
// 내가 만든 store 불러오기
// dispatch 보다 먼저 실행되어야 함
import store from './example/d05/store.jsx'
// store 공급
import {Provider} from 'react-redux'
create.render(<Provider store={store}><Component13 /></Provider>);
*/

/*
import App from './example/d05/Task7/App';
import {Provider} from 'react-redux'
import store, { persistor } from './example/d05/Task/store/store';
import { PersistGate } from 'redux-persist/integration/react';
// store를 root 컴포넌트에 공급하여 모든 컴포넌트가 사용할 수 있도록 전역변수 지정
// persist 공급, loading = {초기 로딩값} persist = {persistStore}
create.render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      <App />
    </PersistGate>
  </Provider>);
*/

import App from './web2/App.jsx';
create.render(<App />);



// 요약 : createRoot(document.querySelector('#root')).render(<[최초 출력할 함수명] />);
