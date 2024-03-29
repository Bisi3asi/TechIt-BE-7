import React from "react";
import { Link } from "react-router-dom";
import Library from "./ch3/Library";
import Clock from "./ch4/clock";
import CommentList from "./ch5/CommentList";
import Accommodate from "./ch7/Accomodate";
import DarkOrLight from "./ch14/DarkOrLight";

function LearningPage() {
  return (
    <div className="mx-2 my-10">
      <Link to="/" className="btn btn-primary mb-2">
        홈으로 이동
      </Link>
      <h1 className="text-3xl font-bold mb-5">React 학습 페이지</h1>
      {/* 실습 시작 */}
      <ul className="py-10">
        <li className="mb-3">
          1장 : 태그의 이해
          <Library />
        </li>
        <li className="mb-3">
          2장 : 엘리먼트의 이해
          <Clock />
        </li>
        <li className="mb-3">
          3장 : 컴포넌트의 이해
          <CommentList />
        </li>
        <li className="mb-3">
          4장 : state의 이해
          {/* <NotificationList /> */}
        </li>
        <li className="mb-3">
          5장 : 훅의 이해 (useState, useEffect)
          <Accommodate></Accommodate>
        </li>
        <li className="mb-3">
          14장 : Context API
          <DarkOrLight></DarkOrLight>
        </li>
      </ul>
    </div>
  );
}

export default LearningPage;
