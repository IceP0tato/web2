import { Link as RouterLink } from "react-router-dom";
import Link from '@mui/joy/Link';
import Box from '@mui/joy/Box';

export default function App(props) {
    return (<>
        <h3>헤더</h3>
        <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap' }}>
            <Link component={RouterLink} to="/" href="#basics">HOME</Link>
            <Link component={RouterLink} to="/menu" href="#basics">메뉴</Link>
            <Link component={RouterLink} to="/cart" href="#basics">장바구니</Link>
        </Box>
    </>)
}