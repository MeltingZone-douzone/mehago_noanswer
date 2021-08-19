import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import styles from "../assets/scss/signUp/SignUpForm.scss";

export default function SignUpForm() {
  const [user, setUser] = useState({ email: '', password: '', nickName: '',  phoneNumber: 0});

  // 핸드폰 번호 체크 정규식
  /* function isCelluar(asValue) {
    const regExp = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
    console.log('asValue: ', asValue);
    return regExp.test(asValue); // 형식에 맞는 경우 true 리턴
  } */


  const validation = {
    checkEmail: function(e) {
        const regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i

    },
    checkPassword: function(e)  {
         //  8 ~ 10자 영문, 숫자 조합
        const regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/
    },
    checkPhoneNumber: function(e)  {
        const regExp = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/ // (-) 없는 정규식 
        const num = e.target.value;
        const result = phoneNumberRegExp.test(num);
        console.log(num);
        console.log(result);
        if(result) {
          // validation 처리
        }
    },

  }


  const handleChange = (e) => {
      const phoneNumberRegExp = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
      // console.log(e.target.value);
      const { name, value } = e.target;
      setUser({
          ...user,
          [name]: value
      })

      // if(user.phoneNumber) {
      //   if(phoneNumberRegExp.test(user.phoneNumber)){
      //     const response = await fetch('/spring/account/pass')
      //   }
      // }
      // setUser({[name]: value});
  }

  // const handleBlur = (e) => {
  //   const phoneNumberRegExp = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/; // (-) 없는 정규식 
  //   const num = e.target.value;
  //   console.log(num);
  //   console.log(phoneNumberRegExp.test(num));
  // }



  const apiFunction = {
    emailCheck: function() {
    },
    SignUp: async function(user) {
        console.log(user);
        const response = await fetch('/spring/account/sign-up', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(user)
        });
        if(!response.ok){
          console.log('호이');
            // throw new Error(`${response.status} ${response.statusText}`)
        }
        const json  = await response.json();
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
            onBlur={validation.checkEmail}
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
            onBlur={validation.checkPassword}
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
            onBlur={validation.checkNickName}
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
            onBlur={validation.checkPhoneNumber}
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
