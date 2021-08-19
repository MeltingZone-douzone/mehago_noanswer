import React,{useState} from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import styles from "../assets/sass/account/LoginForm.scss";
import axios from 'axios';

export default function PasswordSearch(){

    const [user, setUser] = useState({name:"", email:""});

    const onChangeUserInput = (e)=>{
        const {name, value } = e.target;
        console.log(name ,":", value);
        setUser({
            ...user,
            [name]:value}
        );
    }
    
    const emailSend = () => {
        console.log("보내기");
        try {
            const url = `/spring/user/search`;
            const account = {
                name : user.name,
                email : user.email
            }

            axios.post(url, account , {headers:{'Context-Type': 'application/json'}})
                .then(res => console.log(res));
            
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
                    value={user.name} 
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
                        value={user.email} 
                        onChange={(e) => {onChangeUserInput(e)}}/>
                </div>
                <div className={styles.LoginButton}>
                    <Button
                        className={styles.LoginBtn}
                        variant="contained"
                        color="primary"
                        size="large"
                        type="submit"
                        onClick={emailSend}
                    >
                        보내기
                    </Button>
                </div>
            </form>
        </div>
    )
}