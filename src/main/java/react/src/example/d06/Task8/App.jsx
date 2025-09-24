import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "../../d06/Task8/components/Header";
import HomePage from "./pages/HomePage";
import MenuPage from "./pages/MenuPage";
import CartPage from "./pages/CartPage";

export default function App(props) {
    return (<>
        <BrowserRouter>
            <h3>루트 페이지</h3>
            <Header />
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/menu" element={<MenuPage />} />
                <Route path="/cart" element={<CartPage />} />
            </Routes>
        </BrowserRouter>
    </>)
}