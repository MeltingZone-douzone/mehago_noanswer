import React,{useEffect, useState} from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import styles from "../assets/sass/account/LoginForm.scss";
import axios from "axios";

export default function PasswordSearch(){
    const [accounts, setAccount] = useState({name:"", email:""});
    const [sendMassege, setSendMassege] = useState("");

    const onChangeUserInput = (e)=>{
        const {name, value } = e.target;
        console.log(name ,":", value);
        setAccount({
            ...accounts,
            [name]:value}
        );
    }
    
    const emailSend = () => {
        console.log("보내기");
        try {
            const url = `/api/account/findByNameAndEmail`;
            const account = {
                name : accounts.name,
                email : accounts.email
            }
            setSendMassege(true);
            axios.post(url, account , {headers:{'Context-Type': 'application/json'}})
            .then(res => {console.log(res.data);
                if(res.data == false){
                    setSendMassege(false);
                } else {
                    setSendMassege(true);
                }
            });
            setAccount({name: "", email:""});

            
        } catch (e) {
            console.log(e);
        }
    }

    return (
        <div className={styles.LoginForm}>
            <div className={styles.Message}>
                <h1>비밀번호 찾기</h1>
                <div className={styles.MessageInfo}>
                <span>입력하신 이메일로 임시 비밀번호가 발송됩니다.</span>
                </div>
            </div>
            <form >
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
                    value={accounts.name} 
                    onChange={(e) => {onChangeUserInput(e)}}
                    />
                </div>
                <div className={styles.Password}>
                    <TextField 
                        id="outlined-basic" 
                        label="email"
                        className={styles.PasswordInput}
                        type="text"
                        variant="outlined"
                        size="medium"
                        name="email"
                        value={accounts.email} 
                        onChange={(e) => {onChangeUserInput(e)}}/>
                </div>
                 
                 {sendMassege === false ? ( 
                    <div className={styles.LoginFail} name="loginFail">
                        <span>가입되지 않은 이름거나, 잘못된 이메일 입니다.</span>
                    </div>
                    
                 ) : (
                    <div className={styles.emailsend} name="loginFail">
                        <span>{sendMassege === true ? "요청하신 이메일로 비밀번호를 전송 하였습니다." : ''}</span>
                    </div>
                 )}
                <div className={styles.LoginButton}>
                    <Button
                        className={styles.LoginBtn}
                        variant="contained"
                        color="primary"
                        size="large"
                        onClick={emailSend}
                    >
                        보내기
                    </Button>
                </div>
            </form>
        </div>
  );
}
