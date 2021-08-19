import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import styles from "../assets/sass/account/SignUpForm.scss";
import { signUpApi } from "../../api/AccountApi";

export default function SignUpForm() {
    const [user, setUser] = useState({ email: '', password: '', nickName: '',  phoneNumber: 0});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser({
            ...user,
            [name]: value
        })
    }

    const signUp = () => {
      signUpApi(user);
    }

    return (
        <div className={styles.SignUpForm}>
          <div className={styles.Message}>
            <h1>회원가입</h1>
          </div>
          <form>
            <div className={styles.Email}>
              <TextField
                id="email"
                className={styles.EmailInput}
                type="text"
                label="이메일"
                variant="outlined"
                size="medium"
                autoComplete="off"
                name="email"
                onChange={handleChange}
                // onBlur={validation.checkEmail}
              />
              <Button variant="contained" color="primary">
                중복체크
              </Button>
            </div>
            <div className={styles.Password}>
              <TextField
                id="password"
                className={styles.PasswordInput}
                type="password"
                label="패스워드"
                variant="outlined"
                size="medium"
                name="password"
                onChange={handleChange}
                // onBlur={validation.checkPassword}
              />
            </div>
            <div className={styles.NickName}>
              <TextField
                id="nickname"
                className={styles.NickNameInput}
                type="text"
                label="닉네임"
                variant="outlined"
                size="medium"
                autoComplete="off"
                name="nickname"
                onChange={handleChange}
                // onBlur={validation.checkNickName}
              />
            </div>
            <div className={styles.PhoneNumber}>
              <TextField
                id="phoneNumber"
                className={styles.PhoneNumberInput}
                type="text"
                label="휴대폰 번호"
                variant="outlined"
                size="medium"
                autoComplete="off"
                name="phoneNumber"
                onChange={handleChange}
                // onBlur={validation.checkPhoneNumber}
              />
            </div>
            <div className={styles.LoginButton}>
              <Button
                className={styles.LoginBtn}
                variant="contained"
                color="primary"
                size="large"
                onClick={signUp}
              >
                가입하기
              </Button>
              <br/>
              <Button 
                className={styles.SignUpCancel}
                variant="outlined" 
                color="primary"
                size="large"

                >
                  취소하기
              </Button>
            </div>
          </form>
        </div>
    );
}