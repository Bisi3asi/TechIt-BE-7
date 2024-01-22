import { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import axios from "axios";
import LearningPage from "./LearningPage";

function App() {
  const [articleList, setArticleList] = useState(null);
  const [user, setUser] = useState(null);

  const fetchData = async () => {
    const response = await axios.get("http://localhost:8090/api/v1/articles");
    setArticleList(response.data);
  };

  const checkLoggedIn = async () => {
    try {
      const response = await axios.get("http://localhost:8090/api/v1/users", {
        withCredentials: true,
      });
      setUser(response.data);
    } catch (error) {
      console.error("로그인되어 있지 않음");
      setUser(null);
    }
  };

  useEffect(() => {
    fetchData();
    checkLoggedIn();
  }, []);

  const onLoginHandler = async (e) => {
    e.preventDefault();
    const username = e.target.username.value;
    const password = e.target.password.value;

    await axios.post(
      "http://localhost:8090/api/v1/users/login",
      { username, password },
      { withCredentials: true }
    );
    fetchData();
    checkLoggedIn();
  };

  const onLogoutHandler = async () => {
    await axios.get("http://localhost:8090/api/v1/users/logout");
    setUser(null);
  };

  const onSubmitHandler = async (e) => {
    e.preventDefault();

    const title = e.target.title.value;
    const content = e.target.content.value;
    await axios.post(
      "http://localhost:8090/api/v1/articles",
      { title, content },
      { withCredentials: true }
    );
    fetchData();
  };

  return (
    <div className="App mx-2">
      {user ? (
        <p>
          <form onSubmit={onLogoutHandler}>
            환영합니다, {user.nickname}님 / {user.role}
            &nbsp;
            <input type="submit" value="로그아웃" />
          </form>
        </p>
      ) : (
        <form onSubmit={onLoginHandler}>
          <p>
            아이디 : <input name="username" />
            &nbsp; 비밀번호: <input name="password" type="password" />
            &nbsp; <input type="submit" value="로그인" />
          </p>
          <hr></hr>
        </form>
      )}
      <h1 className="text-3xl my-5 font-bold">REST SB 게시판</h1>
      {user ? (
        <form onSubmit={onSubmitHandler}>
          <p>글 제목</p>
          <p>
            <input name="title" />
          </p>
          <p>글 내용</p>
          <p>
            <input name="content" />
          </p>
          <p>
            <button className="btn">등록하기</button>
          </p>
        </form>
      ) : (
        <p>글 작성은 로그인 이후 가능합니다.</p>
      )}
      <hr></hr>
      {articleList && (
        <table className="w-full text-center">
          <thead>
            <tr>
              <th>글 번호</th>
              <th>게시글 제목</th>
              <th>작성자</th>
            </tr>
          </thead>
          <tbody>
            {articleList?.content.map((article) => (
              <tr key={article.id}>
                <td>{article.id}</td>
                <td>{article.title}</td>
                <td>{article.authorName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      <div className="btn btn-primary my-6">
        <Link to="/learning">이동하기 (학습 페이지)</Link>
      </div>
    </div>
  );
}

function Main() {
  return (
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="/learning" element={<LearningPage />} />
    </Routes>
  );
}

function Root() {
  return (
    <Router>
      <Main />
    </Router>
  );
}

export default Root;