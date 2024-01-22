import { useEffect, useState } from 'react';

function App() {
  const[articleList, setArticleList] = useState(null);


  useEffect(() => {
  fetch('http://localhost:8090/api/v1/articles',{
    method: 'GET',
    mode: 'cors',
  })
  .then((response) => response.json())
  .then((data) => setArticleList(data));
  }, []);

  return (
    <div className="App">
      <h1>REST SB 게시판</h1>
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
