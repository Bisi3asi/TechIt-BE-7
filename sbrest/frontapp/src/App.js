import { useEffect, useState } from "react";
import axios from 'axios';

function App() {
  const [articleList, setArticleList] = useState(null);

  const fetchData = async () => {
    const response = await axios.get("http://localhost:8090/api/v1/articles");
    setArticleList(response.data);
  };
  useEffect(() => {
    fetchData();
  }, []);

  const onLoginHandler = async (e) => {
    e.preventDefault();
    const username = e.target.username.value;
    const password = e.target.password.value;

    await axios.post("http://localhost:8090/api/v1/users/login", 
    {username, password}, {withCredentials: true})
    fetchData();
  };

  const onSubmitHandler = async (e) => {
    e.preventDefault();

    const title = e.target.title.value;
    const content = e.target.content.value;
    await axios.post("http://localhost:8090/api/v1/articles", {title, content}, {withCredentials: true})
    fetchData();
  };

  return (
    <div className="App">
      <form onSubmit={onLoginHandler}>
        <p>
          아이디 : <input name="username" />
        </p>
        <p>
          비밀번호: <input name="password" type="password" />
        </p>
        <p>
          <input type="submit" value="로그인하기" />
        </p>
        <h1>REST SB 게시판</h1>
      </form>
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
          <input type="submit" value="등록하기" />
        </p>
      </form>
      <p></p>
      {articleList && (
        <table>
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
    </div>
  );
}

export default App;
