import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import styles from "../assets/sass/account/LoginForm.scss";

export default function LoginForm() {
  const [memberVo, setMemberVo] = useState({ id: "", password: "" });
  const [loginFail, setLoginFail] = useState(false);

  const login = (e) => {
    e.preventDefault();
    try {
      axios
        .post("/api/account/login", memberVo, {
          headers: {
            ContentType: "application/json",
            Accept: "application/json",
          },
        })
        .then((res) => {
          if (res.data.result == "success") {
            console.log(res.data);
            if (res.data.data === "cant find account") {
              // 틀렸을 경우에
              setLoginFail(true);
              setMemberVo({ ...memberVo, password: "" });
            } // 성공하면 메인화면 가기
          }
        });
    } catch (err) {
      console.error(err);
    }
  };
  const handleChange = (e) => {
    const { name, value } = e.target;
    console.log(name, value);
    setMemberVo({ ...memberVo, [name]: value });
  };

  return (
    <div className={styles.LoginForm}>
      <div className={styles.Message}>
        <h1>로그인</h1>
        <div className={styles.MessageInfo}>
          <span>로그인을 통해 mehago를 이용해 보세요.</span>
        </div>
      </div>
      <form onSubmit={login}>
        <div className={styles.Id}>
          <TextField
            id="outlined-basic"
            className={styles.IdInput}
            type="text"
            label="ID"
            variant="outlined"
            size="medium"
            autoComplete="off"
            name="id"
            value={memberVo.id}
            onChange={(e) => handleChange(e)}
          />
        </div>
        <div className={styles.Password}>
          <TextField
            id="outlined-basic"
            className={styles.PasswordInput}
            type="password"
            label="패스워드"
            variant="outlined"
            size="medium"
            name="password"
            value={memberVo.password}
            onChange={(e) => handleChange(e)}
          />
        </div>
        {loginFail === false ? (
          ""
        ) : (
          <div className={styles.LoginFail} name="loginFail">
            <span>가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.</span>
          </div>
        )}
        <div className={styles.LoginButton}>
          <Button
            className={styles.LoginBtn}
            variant="contained"
            color="primary"
            size="large"
            type="submit"
          >
            로그인
          </Button>
        </div>
      </form>
    </div>
  );
}
