import TextField from "@material-ui/core/TextField";
import React, { useState } from "react";
import { io } from "socket.io-client";
import axios from 'axios';

export default function ChatEx(){

    const formStyle = {
        position: 'absolute',
        left: '0',
        bottom: '0',
        width: '100%',
        padding: '15px 0 15px 15px'
    }

    const [messageObject, setMessageObject] = useState('');

    // From the same domain
    // const socket = io()
    
    // From a different domain
    // const socket = io("http://localhost:8888"); // node port
    const socket = io("http://localhost:8888"); // node port

    const roomname = "c1"

    const sendMessage = (e) => {
        e.preventDefault();
        console.log("서브미");
        console.log(messageObject); // roomname 추가

        socket.emit('message', messageObject);
        // value = '';
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setMessageObject({
            ...messageObject,
             [name]: value
        });
    }

   /*  // <script src="/socket.io/socket.io.js"></script>
        // var socket = io();
        var socket = io.connect('http://localhost:8080');
        var msgform = document.getElementById('msgform');
        var roomsText = document.getElementById('rooms');
        // socket.on 함수로 서버에서 전달하는 신호를 수신
        socket.on('usercount', (count) => {
            var userCounter = document.getElementById('usercount');
            userCounter.innerText = "현재 서버에 " + count + "명이 접속해있습니다. (같은 포트)";
        });

        // 메시지 수신시 HTML에 메시지 내용 작성
        socket.on('message', (data) => {  // message + nick
            var messageList = document.getElementById('messages');
            var messageTag = document.createElement("li");
            console.log(data);
            console.log(socket.id); // 내 현재 socket.id
            messageTag.innerText = data;
            messageList.appendChild(messageTag);
            // 채팅 스크롤 내리기
        });

        // 접속한 룸이 바뀌었을 때
        socket.on('roomChanged', (joinedRoom) => { 
            roomname = joinedRoom;
            var messageList = document.getElementById('messages');
            var messageTag = document.createElement("li");
            messageTag.innerText = "[알림] " + joinedRoom + "에 접속했습니다. ===============";
            messageList.appendChild(messageTag);
        });

        socket.on('disconnect user', (socketId) => {
            var messageList = document.getElementById('messages');
            var messageTag = document.createElement("li");
            messageTag.innerText = "[알림]" + socketId +" 가 퇴장했습니다. ===============";
            messageList.appendChild(messageTag);
        });

        msgform.onsubmit = (e) => {
            e.preventDefault();
            var msginput = document.getElementById('msginput');
            var nickinput = document.getElementById('nick');

            var data = {
                msg: msginput.value,
                roomname,
                nick: nickinput.value
            }

            // socket.emit으로 서버에 신호를 전달
            socket.emit('message', data);
            console.log(roomname);
            msginput.value = "";
        };

        function getRooms() { // 방 목록 가져오기 버튼 클릭시
           // url을 지정해서 특정 네임스페이스를 들어갈 수 있다.
           var debug = io.connect('http://localhost:8080/');

           debug.emit('getRooms');  // getRooms 이벤트 호출
           
           debug.on('rooms', (rooms) => { // rooms 이벤트 발생
               // 룸 목록 업데이트
               roomsText.textContent = "";
               console.log(rooms);
               for (var room in rooms) {
                   roomsText.innerHTML += room + "<br>";
               }
           });
        }
        function joinRoom() { // 방 접속 버튼 클릭시
            var roomOptions = document.getElementById("roomoptions");
            var roomToJoin = roomOptions.options[roomOptions.selectedIndex].value;
            console.log(roomToJoin); // 채팅방 이름
            // 서버에 룸 전환 신호를 발신
            socket.emit('joinRoom', roomname, roomToJoin);
        } */

    return(
        <div>
            {/* <select id="roomoptions" onchange="joinRoom()">
                <option value="chatroom01" selected>chatroom01</option>
                <option value="chatroom02">chatroom02</option>
            </select> */}
            <hr/>
            <ul id="messages" type="none">
                <li id="usercount"></li><br/>
            </ul>

            <form id="msgform" style={formStyle} onSubmit={sendMessage}>
                <hr/>
                <TextField 
                    type="text" 
                    name="nickname" 
                    id="nickname"
                    onChange={handleChange}
                    />
                <TextField 
                    id="msginput" 
                    type="text" 
                    name="message"
                    id="message"
                    size="medium"
                    style={{width:"100%"}}
                    onChange={handleChange}
                    />
                <button type="submit">전송</button>
            </form>

            {/* <button onClick="getRooms()">방 목록 가져오기</button> */}
            

            <p id="rooms"></p>
        </div>
    )
}
/* #msgform {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
  padding: 15px 0 15px 15px;
} */
