import axios from "axios";

export function signUpApi(user) {
    console.log(user);
    return axios.post("/api/account/sign-up", user,)
                .then(res => console.log(res));
}

export function getUserInfo() {
    return axios.get("/api/account/get-user", {headers:{"Context-Type" : "application/json"}});
}

// 닉네임 존재여부
export function validNickanmeExist() { 
    // return axios.get("/api/getUserInfo", {headers:{"Context-Type" : "application/json"});
}

// 이전 비밀번호 확인
export function checkPassword() {
    // return axios.get("/api/getUserInfo", {headers:{"Context-Type" : "application/json"});
}


export function updateNickname() {
    // return axios.get("/api/getUserInfo", {headers:{"Context-Type" : "application/json"});
}

export function updatePassword() {
    // return axios.get("/api/getUserInfo", {headers:{"Context-Type" : "application/json"});
}

export function updateUserInfo() {
    // return axios.get("/api/getUserInfo", {headers:{"Context-Type" : "application/json"});
}
