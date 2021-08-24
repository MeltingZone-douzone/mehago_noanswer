module.exports = function(io) {
    const express = require('express')
    const router = express.Router();
    const redisAdapter = require('socket.io-redis');
    const redis = require('redis');
    const sub = redis.createClient();
    const pub = redis.createClient();


    pub.on('error', (err) => console.log(err));
    sub.on('error', (err) => console.log(err));

    // publisher가 publish하면 이 함수가 실행된다. 여기서 소켓에 메시지를 뿌려줌
    sub.on('message', (roomname, message) => {
        console.log(roomname, '에 보낼 메시지 : ', message);
        io.to(roomname).emit('message', message);
    });

    io.adapter(redisAdapter({
        host: '127.0.0.1',
        port: 6379
    }));

    router.get('/room', async (req, res) => {
        const roomId = req.query.room_id;
        console.log(`/room 접속함 roomId는 ${roomId}`);
    });
    // router.get('/', function(req, res, next){
    //     res.render('chat/index');
    // })

    io.on('connection', (socket) => {   
        socket.emit('usercount', io.engine.clientsCount);

        sub.subscribe("chatroom01"); // 일단 default 채팅방1 을 구독한다!
        socket.join("chatroom01");   // 일단 default 채팅방1 을 join한다!
        pub.publish("chatroom01", "[알림] 기본 chatroom01에 접속. (publish) :" + socket.id) // 일단 default 채팅방1 에 접속했다고 message를 뿌린다!

        socket.on('message', (data) => { // front에서 message를 뿌리면 들어온다!
            console.log('Message received: ' +  data.msg + ' by: ' + data.nick + ' socketid: ' + socket.id); // 각 포트에서만 찍힘
            // io.to(data.roomname).emit('message', data.nick + ' : ' + data.msg);
            // pub.publish(data.roomname, data.nick + ' : ' + data.msg)
            pub.publish(data.roomname, data.nick + ' : ' + data.msg) // pub.publish(보낼 채널(방), 메시지)
        });
        // 룸 전환 신호
        socket.on('joinRoom', (roomname, roomToJoin) => {
            socket.leave(roomname); // 기존의 룸을 나가고
            sub.subscribe(roomToJoin);
            socket.join(roomToJoin);  // 들어갈 룸에 들어간다.
            // 룸을 성공적으로 전환했다는 신호 발송
            socket.emit('roomChanged', roomToJoin);
        });

        socket.on('disconnect',() => {
            console.log('disconnect :', socket.id);
            // io.to(data.roomname).emit('disconnect', socket.id)
            io.emit('disconnect user', socket.id)
        });
    });
    return router;
}