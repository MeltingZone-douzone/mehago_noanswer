import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import styles from "../assets/sass/account/SignUpForm.scss";
import { signUpApi } from "../../api/AccountApi";
import axios from "axios";

export default function SignUpForm() {
  const [user, setUser] = useState({ email: '', password: '', nickName: '',  phoneNumber: 0});  
  const headers = {
    "Content-Type": "application/json; charset=UTF-8",
    "Accept": "application/json"
  }
// kwb103@naver.com
// dfdf@dfdf.com
  const isExist = (name, value) => {
    // console.log(name, " : ", value);
    axios.post('/api/account/signup/valid-' + name,
              value,
              { headers: {"Content-Type": "text/plain"} }
      )
      .then(response => {
        console.log(response.data, ' 사용가능함'); // null 이면 사용가능한 이메일
        // if(response.data !== 'null') {
        if(response.data !== null) {  // 이미 있을 경우
          // console.log("이미 있는 ",name," 입니다"); // TODO 
          console.log(`이미 있는 ${name} : ${response.data}`);
          return false;
        }

      })
  }


  const validation = {
    checkEmail: function(e) {
        const regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/
        const data = regExp.test(e.target.value);
        console.log(data? "유효성ok" : "유효성ㄴㄴ");
        if(data) {
          isExist(e.target.name, e.target.value)
        }
    },
    checkPassword: function(e)  {
        //  8 ~ 10자 영문, 숫자 조합
        const regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/
        console.log(e.target.value);
        const data = regExp.test(e.target.value);
        console.log(data? "유효성ok" : "유효성ㄴㄴ");
        if(data) {
          isExist(e.target.name, e.target.value)
        }
    },
    checkNickName: function(e) {
        isExist(e.target.name, e.target.value)
        // DB 중복체크
    },
    checkPhoneNumber: function(e)  {
        const regExp = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/ // (-) 없는 정규식 
        console.log(e.target.value);
        const data = regExp.test(e.target.value);
        console.log(data? "유효성ok" : "유효성ㄴㄴ");
        if(data) {
          isExist(e.target.name, e.target.value)
        }
    },
  // const validation = (e) => {
  //   switch(e.target.name) {
  //     case "email": { // 이메일 정규식, DB중복체크
  //       const regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/
  //     }
  //     case "password": { // 비밀번호 정규식
  //       const regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/
  //     }
  //     case "nickName":  // DB 중복체크
  //     case "phoneNumber": { // 휴대폰 번호 정규식
  //       const regExp = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/ // (-) 없는 정규식 
  //     }
      
  //   }
  // }

  }


  const handleChange = (e) => {
      const { name, value } = e.target;
      setUser({
          ...user,
          [name]: value
      })
  }

  const apiFunction = {
    emailCheck: function() {
    },
    SignUp: function(user) {
        console.log(user);
        const response = fetch('/api/account/signup', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(user)
        });
        if(!response.ok){
          console.log('호이');
            // throw new Error(`${response.status} ${response.statusText}`)
        }
        const json  = response.json();
        if(json.result !== 'success') {
            throw new Error(`${json.result} ${json.message}`)
        }
        setUser({ email: '', password: '', nickname: '', phoneNumber: 0}) // 안해도 되는거아이가
    }
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
            onBlur={(e) => e.target.value !== '' && validation.checkEmail(e)}
          />
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
            onBlur={(e) => e.target.value !== '' && validation.checkPassword(e)}
          />
        </div>
        <div className={styles.NickName}>
          <TextField
            id="nickName"
            className={styles.NickNameInput}
            type="text"
            label="닉네임"
            variant="outlined"
            size="medium"
            autoComplete="off"
            name="nickName"
            onChange={handleChange}
            onBlur={(e) => e.target.value !== '' && validation.checkNickName(e)}
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
            onBlur={(e) => e.target.value !== '' && validation.checkPhoneNumber(e)}
          />
        </div>
        <div className={styles.LoginButton}>
          <Button
            className={styles.LoginBtn}
            variant="contained"
            color="primary"
            size="large"
            onClick={() => apiFunction.SignUp(user)}
          >
            가입하기
          </Button>
          {/* <br/>
          <Button 
            className={styles.SignUpCancel}
            variant="outlined" 
            color="primary"
            size="large"
            >
              취소하기
          </Button> */}
        </div>
      </form>
    </div>
  );
}
