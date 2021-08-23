import axios from "axios";
import localStorage from "local-storage";

const header = {
    "Context-Type": "application/json",
    "Accept": "application/json",
}


const headerWithAuth = {
    "Context-Type": "application/json",
    "Accept": "application/json",
    "Authorization": `Bearer ` + localStorage.get("token")

}
export function signIn(account) {
    return axios
        .post("/api/account/login", account, {
            headers: header
        })
        .then((res) => res);
}
export function signUp(account) {
    console.log(account);
    return axios.post("/api/account/sign-up", account, header)
        .then(res => console.log(res));
}

export function getUserInfo() {
    return axios.get("/api/account/get-user", { headers: headerWithAuth })
        .then(res => res);
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
