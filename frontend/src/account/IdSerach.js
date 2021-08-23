import React from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import styles from "../assets/sass/account/LoginForm.scss";

import NonMembers from "../components/NonMember";

export default function IdSearch() {
  const searchFail = (e) => {
    "true";
  };
  return (
    <div className={styles.LoginForm}>
      <div className={styles.Message}>
        <h1>아이디 찾기</h1>
        <div className={styles.MessageInfo}>
          <span>.......</span>
        </div>
      </div>
      <form>
        <div className={styles.Id}>
          <TextField
            id="outlined-basic"
            label="name"
            className={styles.IdInput}
            type="text"
            variant="outlined"
            size="medium"
            autoComplete="off"
            name="name"
          />
        </div>
        <div className={styles.Password}>
          <TextField
            id="outlined-basic"
            label="Phone Number"
            className={styles.PasswordInput}
            type="text"
            variant="outlined"
            size="medium"
            name="phoneNumber"
          />
        </div>
        {searchFail === false ? (
          ""
        ) : (
          <div className={styles.LoginFail} name="loginFail">
            <span>가입하지 않은 잘못된 아이디입니다.</span>
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
            찾기
          </Button>
        </div>
      </form>
      <NonMembers />
    </div>
  );
}
