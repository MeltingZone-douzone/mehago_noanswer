import React from "react";
import styles from "../assets/scss/login/Links.scss";

export default function Links() {
  return (
    <div className={styles.Links}>
      <ul>
        <li>
          <a href="#">아이디 찾기</a>
        </li>
        <li>
          <a href="#"> 비밀번호 찾기</a>
        </li>
      </ul>
      <a href="#" className={styles.MemberJoin}>
        회원가입
      </a>
    </div>
  );
}
