import React from "react";
import { Link } from "react-router-dom";
import Library from './Library'

function LearningPage() {
  return (
    <div className="mx-2 my-10">
      <Link to="/" className="btn btn-primary mb-2">홈으로 이동</Link>
      <h1 className="text-3xl font-bold mb-5">React 학습 페이지</h1>
    {/* 실습 시작 */}
    <ul className="py-10">
      <li>1장<Library /></li>
      <li>2장</li>
    </ul>
    </div>
  );
}

export default LearningPage;