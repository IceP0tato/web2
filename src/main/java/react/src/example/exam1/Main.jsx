import axios from "axios";
import { useEffect, useState } from "react";

export default function Main() {
    const [mno, setMno] = useState(0);
    const [mname, setMName] = useState('');
    const [mdirector, setMDirector] = useState('');
    const [mgenre, setMGenre] = useState('');
    const [mdesc, setMDesc] = useState('');
    const [mpwd, setMPwd] = useState('');
    const [movie, setMovie] = useState({});
    const [movieList, setMovieList] = useState([]);

    const [ano, setAno] = useState(0);
    const [atitle, setATitle] = useState('');
    const [adesc, setADesc] = useState('');
    const [apwd, setAPwd] = useState('');
    const [article, setArticle] = useState({});
    const [articleList, setArticleList] = useState([]);

    const [status, setStatus] = useState(0);
    const [aStatus, setAStatus] = useState(0);

    useEffect(() => {getMovie()}, []);
    useEffect(() => {findMovie()}, [mno]);
    useEffect(() => {getArticle()}, [aStatus]);
    useEffect(() => {findArticle()}, [ano]);

    const postMovie = async () => {
        const obj = {mname, mdirector, mgenre, mdesc, mpwd};
        if (mname == '' || mdirector == '' || mgenre == '' || mdesc == '' || mpwd == '') {
            alert('내용을 모두 입력해주세요.');
            return;
        }
        const response = await axios.post("http://localhost:8080/movie", obj);
        if (response.status >= 200 && response.status < 300) {
            alert('등록이 완료되었습니다.');
            getMovie();
            setStatus(0);
        } else {
            alert('등록에 실패하였습니다.');
        }
    }

    const deleteMovie = async (mno) => {
        const mpwd = prompt("게시글의 비밀번호를 입력해주세요:");
        const response = await axios.delete("http://localhost:8080/movie?mno="+mno+"&mpwd="+mpwd);
        if (response.status >= 200 && response.status < 300) {
            if (response.data == true) {
                alert('삭제가 완료되었습니다.');
                getMovie();
                setStatus(0);
            } else {
                alert('비밀번호가 일치하지 않습니다.');
            }
        } else {
            alert('삭제에 실패하였습니다.');
        }
    }

    const getMovie = async () => {
        const response = await axios.get("http://localhost:8080/movie");
        setMovieList(response.data);
    }

    const findMovie = async () => {
        if (mno == 0) return;
        const response = await axios.get("http://localhost:8080/movie/find?mno="+mno);
        setMovie(response.data);
        getArticle();
    }

    const postArticle = async () => {
        const obj = {atitle, adesc, apwd, mno};
        if (atitle == '' || adesc == '' || apwd == '') {
            alert('내용을 모두 입력해주세요.');
            return;
        }
        const response = await axios.post("http://localhost:8080/article", obj);
        if (response.status >= 200 && response.status < 300) {
            alert('등록이 완료되었습니다.');
            getArticle();
            setAStatus(0);
        } else {
            alert('등록에 실패하였습니다.');
        }
    }

    const deleteArticle = async (ano) => {
        const apwd = prompt("게시글의 비밀번호를 입력해주세요:");
        const response = await axios.delete("http://localhost:8080/article?ano="+ano+"&apwd="+apwd);
        if (response.status >= 200 && response.status < 300) {
            if (response.data == true) {
                alert('삭제가 완료되었습니다.');
                getArticle();
                setAStatus(0);
            } else {
                alert('비밀번호가 일치하지 않습니다.');
            }
        } else {
            alert('삭제에 실패하였습니다.');
        }
    }

    const getArticle = async () => {
        if (mno == 0) return;
        const response = await axios.get("http://localhost:8080/article?mno="+mno);
        setArticleList(response.data);
    }

    const findArticle = async () => {
        if (ano == 0) return;
        const response = await axios.get("http://localhost:8080/article/find?ano="+ano);
        setArticle(response.data);
    }

    return (<>
        <div id="wrap">
            <div id="header">
                <a href="./Main.jsx">메인 페이지로</a>
            </div>
            <div id="container">
                {
                    status==0?
                    (<div>
                        <h3>영화 목록</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>번호</th>
                                    <th>제목</th>
                                    <th>감독</th>
                                    <th>장르</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    movieList.map((movie) => {
                                        return (<tr key={movie.mno}>
                                            <td>{movie.mno}</td>
                                            <td><a href="#" onClick={() => {setMno(movie.mno); setStatus(2)}}>{movie.mname}</a></td>
                                            <td>{movie.mdirector}</td>
                                            <td>{movie.mgenre}</td>
                                        </tr>)
                                    })
                                }
                            </tbody>
                        </table>
                        <button onClick={() => {setStatus(1)}}>등록</button>
                    </div>)
                    :status==1?
                    (<div>
                        <h3>영화 등록</h3>
                        <div>제목 : <input value={mname} onChange={(e) => {setMName(e.target.value)}} /></div>
                        <div>감독 : <input value={mdirector} onChange={(e) => {setMDirector(e.target.value)}} /></div>
                        <div>장르 : <input value={mgenre} onChange={(e) => {setMGenre(e.target.value)}} /></div>
                        <div>내용 : <input value={mdesc} onChange={(e) => {setMDesc(e.target.value)}} /></div>
                        <div>비밀번호 : <input value={mpwd} onChange={(e) => {setMPwd(e.target.value)}} /></div>
                        <button onClick={postMovie}>등록</button>
                        <button onClick={() => {setStatus(0)}}>뒤로</button>
                    </div>)
                    :status==2?
                    (<div>
                        <div>
                            <h3>영화 조회</h3>
                            <div>제목 : {movie.mname}</div>
                            <div>감독 : {movie.mdirector}</div>
                            <div>장르 : {movie.mgenre}</div>
                            <div>내용 : {movie.mdesc}</div>
                            <button onClick={() => {deleteMovie(movie.mno)}}>삭제</button>
                            <button onClick={() => {setStatus(0)}}>뒤로</button>
                        </div>
                        {
                            aStatus==0?
                            (<div>
                                <h3>토론글</h3>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>제목</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            articleList.map((article) => {
                                                return (<tr key={article.ano}>
                                                    <td>{article.ano}</td>
                                                    <td><a href="#" onClick={() => {setAno(article.ano); setAStatus(2)}}>{article.atitle}</a></td>
                                                </tr>)
                                            })
                                        }
                                    </tbody>
                                </table>
                                <button onClick={() => {setAStatus(1)}}>등록</button>
                            </div>)
                            :aStatus==1?
                            (<div>
                                <h3>토론글 작성</h3>
                                <div>제목 : <input value={atitle} onChange={(e) => {setATitle(e.target.value)}} /></div>
                                <div>내용 : <input value={adesc} onChange={(e) => {setADesc(e.target.value)}} /></div>
                                <div>비밀번호 : <input value={apwd} onChange={(e) => {setAPwd(e.target.value)}} /></div>
                                <button onClick={postArticle}>등록</button>
                                <button onClick={() => {setAStatus(0)}}>뒤로</button>
                            </div>)
                            :aStatus==2?
                            (<div>
                                <h3>토론글</h3>
                                <div>제목 : {article.atitle}</div>
                                <div>내용 : {article.adesc}</div>
                                <button onClick={() => {deleteArticle(article.ano)}}>삭제</button>
                                <button onClick={() => {setAStatus(0)}}>뒤로</button>
                            </div>)
                            :null
                        }
                    </div>)
                    :null
                }
            </div>
        </div>
    </>)
}

                

                
