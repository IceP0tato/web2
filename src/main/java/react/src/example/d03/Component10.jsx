import { useEffect, useState } from "react";
import axios from 'axios';

export default function Component10(props) {
    const [bcontent, setBcontent] = useState('');
    const [bwriter, setBwriter] = useState('');
    const [boards, setBoards] = useState([{bno: 1, 'bcontent': '', 'bwriter': ''}]);

    // 최초 컴포넌트 실행 시 출력 함수 실행 (의존성 배열이 비어있어 1번만 실행)
    useEffect(() => {boardPrint()}, []);

    const boardWrite = async () => {
        const obj = {bcontent, bwriter};
        const response = await axios.post("http://localhost:8080/board", obj);
        console.log(response.status);
        boardPrint(); // post 후 데이터 다시 불러오기
    }

    const boardPrint = async () => {
        const response = await axios.get("http://localhost:8080/board");
        setBoards(response.data);
    }

    const boardDelete = async (bno) => {
        // bno 를 매개변수로 받아 삭제할 bno 를 제외한 새로운 리스트 생성
        // const newBoards = boards.filter((board) => {return board.bno != bno});
        const response = await axios.delete("http://localhost:8080/board?bno="+bno);
        console.log(response.status);

        // 리스트 다시 불러오기
        // setBoards([...newBoards]);
        boardPrint();
    }

    return (<>
        <h3>Spring Server 통신</h3>
        
        <div>
            bcontent : <input value={bcontent} onChange={(e) => setBcontent(e.target.value)} />
            bwriter : <input value={bwriter} onChange={(e) => setBwriter(e.target.value)} />
            <button onClick={boardWrite}>등록</button>
        </div>

        <div>
            {
                boards.map((board) => {
                    return <div>
                            {board.bno} {board.bcontent} {board.bwriter}
                            <button onClick={() => boardDelete(board.bno)}>삭제</button>
                        </div>
                })
            }
            <button onClick={boardPrint}>불러오기</button>
        </div>
    </>)
}