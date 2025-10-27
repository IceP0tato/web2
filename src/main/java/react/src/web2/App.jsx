import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import RoleRoute from './components/RoleRoute'
import Header from './components/Header'
import Login from './pages/user/Login'

export default function App() {
    return (<>
        <Router>
            <Header />
            <Routes>
                {/* 권한에 따른 조건 */}
                {/* ALL */}
                <Route path='/' element={<h1>Main Page</h1>} />
                <Route path='/signup' element={<h1>Signup Page</h1>} />
                <Route path='/login' element={<Login />} />

                {/* USER */}
                <Route element={<RoleRoute roles={["USER", "ADMIN"]} />}>
                    <Route path='/user/info' element={<h1>My Page</h1>} />
                </Route>

                {/* ADMIN */}
                <Route element={<RoleRoute roles={["ADMIN"]} />}>
                    <Route path='/admin/dashboard' element={<h1>Admin Page</h1>} />
                </Route>

                {/* ERROR */}
                <Route path='/forbidden' element={<h1>403 Forbidden</h1>} />

            </Routes>
        </Router>
    </>)
}