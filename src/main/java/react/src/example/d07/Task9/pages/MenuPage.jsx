import * as React from 'react';
import { useDispatch } from "react-redux";
import { addToCart } from "../store/cartSlice";
import Table from '@mui/joy/Table';
import Button from '@mui/joy/Button';
import Snackbar from '@mui/joy/Snackbar';
import PlaylistAddCheckCircleRoundedIcon from '@mui/icons-material/PlaylistAddCheckCircleRounded';

export default function MenuPage(props) {
    const [open, setOpen] = React.useState(false);

    const menu = [
        { id: 1, name: "아메리카노", price: 3000 }, 
        { id: 2, name: "카페라떼", price: 4000 },
        { id: 3, name: "카푸치노", price: 4500 },
    ];

    const dispatch = useDispatch();

    const add = (item) => {
        dispatch(addToCart(item));
        console.log(item);
    }

    return (<>
        <h3>메뉴 페이지</h3>
        <Table aria-label="basic table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제품명</th>
                    <th>가격</th>
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
                {
                    menu.map((item) => (
                        <tr key={item.id}>
                            <td>{item.id}</td>
                            <td>{item.name}</td>
                            <td>{item.price}</td>
                            <td><React.Fragment>
                                    <Button variant="outlined" color="neutral" onClick={() => {add(item); setOpen(true)}}>
                                        담기
                                    </Button>
                                    <Snackbar
                                        variant="soft"
                                        color="primary"
                                        open={open}
                                        onClose={() => setOpen(false)}
                                        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
                                        startDecorator={<PlaylistAddCheckCircleRoundedIcon />}
                                        endDecorator={
                                        <Button
                                            onClick={() => setOpen(false)}
                                            size="sm"
                                            variant="soft"
                                            color="primary"
                                        >
                                            닫기
                                        </Button>
                                        }
                                    >
                                        물품이 장바구니에 담겼습니다.
                                    </Snackbar>
                                    </React.Fragment></td>
                        </tr>
                    ))
                }
            </tbody>
        </Table>
    </>)
}

