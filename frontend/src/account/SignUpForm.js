import React, { useState } from 'React';

import styles from '../assets/sass/account/Form.scss'
export default function SignUpForm() {

    const [user, setUser] = useState({email : "" , password: "", nickname: "", phoneNumber: 0});

    const handleChange = (e) =>{
        const {name, value} = e.target;

        setUser({
            ...user,
            [name]: value
        });
    }

    return(
        <div className={styles.ContentContainer}>


        </div>
    )
}