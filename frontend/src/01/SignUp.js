import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import React, { useState } from 'react';


const useStyles = makeStyles((theme) => ({
    root: {
      '& > *': {
        margin: theme.spacing(2),
        width: 'auto',
      },
    },
  }));

export default function SignUp() {
    const [user, setUser] = useState({ email: '', password: '', nickname: '' });
    const classes = useStyles();

    const onChangeInputValue = (e) => {
        console.log(e.target.value);
        const { name, value } = e.target;
        setUser({
            ...user,
            [name]: value
        })
        // setUser({[name]: value});
    }
    let headers = new Headers();

    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    headers.append('Origin','http://localhost:8080');
    const apiFunction = {
        
        emailCheck: function() {

        },
        SignUp: async function(user) {
            console.log(user);
            const response = await fetch('http://localhost:8080/boot/account/sign-up', {
                method: 'POST',
                headers: headers,
                body: JSON.stringify(user)
            });
            if(!response.ok){   // ok : HTTP 상태코드가 200~299 사이
                throw new Error(`${response.status} ${response.statusText}`)
            }
            const json  = await response.json();
            if(json.result !== 'success') {
                throw new Error(`${json.result} ${json.message}`)
            }
            setUser({ email: '', password: '', nickname: ''})
        }
    }


    return (

            <div>
                <form className={classes.root} noValidate autoComplete="off">
                    <TextField 
                        id="email" 
                        name="email" 
                        label="이메일" 
                        value={ user.email }
                        onChange={(e) => onChangeInputValue(e)}
                        />
                    <Button variant="contained" color="primary">
                        중복체크
                    </Button>
                </form>
                <TextField
                    id="password"
                    name="password"
                    label="Password"
                    type="password"
                    autoComplete="current-password"
                    value={ user.password }
                    onChange={(e) => onChangeInputValue(e)}
                />
                {/* 닉네임중복체크 */}
                <form className={classes.root} noValidate autoComplete="off">
                    <TextField 
                        id="nickname" 
                        name="nickname" 
                        label="닉네임" 
                        value={ user.nickname }
                        onChange={(e) => onChangeInputValue(e)}
                        />
                </form>
                
                <div>
                    <Button variant="contained" color="primary" onClick={() => apiFunction.SignUp(user)}>
                        가입하기
                    </Button>
                    <Button variant="outlined" color="primary">
                        취소하기
                    </Button>
                </div>
              </div>
    )
}