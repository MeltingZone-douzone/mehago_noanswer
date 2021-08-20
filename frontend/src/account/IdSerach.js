import React ,{useState} from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import styles from "../assets/sass/account/LoginForm.scss";
import axios from 'axios';

export default function IdSearch(){
    const [accounts, setAccount] = useState({name:"", phoneNumber:""});
    const [searchEmail, setSearchEmail] = useState(true);

    const onChangeUserInput = (e)=>{
        const {name, value } = e.target;
        console.log(name ,":", value);
        setAccount({
            ...accounts,
            [name]:value}
        );
    }
    const emailReceive = () => {
        console.log("이메일 받기");
        console.log(accounts);
        try {
            const url = `/api/account/searchEmail`;
            const account = {
                name : accounts.name,
                phoneNumber : accounts.phoneNumber
            };

            axios.post(url, account , {headers:{'Context-Type': 'application/json'}})
                .then(res => {
                    console.log(res);
                    if(res.data.data == "No Search Email"){
                        console.log("No Search Email");
                        setSearchEmail(false);
                    } else {
                        console.log("success");
                        setSearchEmail(res.data.data);
                    }
            });
            setAccount({name: "", phoneNumber:""});
                
            
        } catch (e) {
            console.log(e);
        }
    }
    return (
        <div className={styles.LoginForm}>
            <div className={styles.Message}>
                <h1>아이디 찾기</h1>
                <div className={styles.MessageInfo}>
                <span>.......</span>
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
                    onChange={(e)=>{onChangeUserInput(e)}}
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
                        value={accounts.phoneNumber}
                        onChange={(e)=>{onChangeUserInput(e)}}/>
                </div>
                {searchEmail === false ? (
                    <div className={styles.LoginFail} name="loginFail">
                        <span>가입되지 않은 이름거나, 잘못된 전화번호입니다.</span>
                    </div>
                    ) : (
                    <div className={styles.LoginFail} name="loginFail">
                        <span>{searchEmail}</span>
                    </div>
                )}
                <div className={styles.LoginButton}>
                    <Button
                        className={styles.LoginBtn}
                        variant="contained"
                        color="primary"
                        size="large"
                        onClick={emailReceive}>
                        찾기
                    </Button>
                </div>
            </form>  
        </div>
    )
}