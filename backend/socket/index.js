const redisAdapter = require('socket.io-redis');
const redis = require('redis');
const sub = redis.createClient();
const pub = redis.createClient();
const socketIO = require('socket.io');



const runSocketIo = (server) => {
    const io = socketIO.listen(server);
    /* 여기가 맞나 */
    pub.on('error', (err) => console.log(err));
    sub.on('error', (err) => console.log(err));

    sub.on('message', (roomname, message) => {
        console.log(roomname, '에 보낼 메시지 : ', message);
        io.to(roomname).emit('message', message);
    });

    io.adapter(redisAdapter({ // server인가 io인가 ㅅㅂ
        host: '127.0.0.1',
        port: 6379
    }));
    /* 여기가 맞나 */

    io.on('connection', (socket) => {
        // disconnect(socket);
        // socket.join("c1");
        // pub.publish("c1","c1에 접속함 pub");
        console.log(`connection ${socket.id}`);
        // joinRoom(socket);
        // message(socket, io);
        // readChat(socket, io);
    })
}

const disconnect = (socket) => {
    socket.on('disconnect', () => {
        console.log('소켓 연결 해제 - disconnect :', socket.id);
        // io.to(data.roomname).emit('disconnect', socket.id)
        // io.emit('disconnect user', socket.id)
    });
}

const joinRoom = (socket => {
    socket.on('joinRoom', (roomId) => {
        sub.subscribe(roomId);
        socket.join(roomId);
        console.log(`${roomId}방에 접속`);
    });
});

const message = (socket, io) => {
    socket.on('message', (messageObject) => {
        console.log(`Message received: ${messageObject.msg} by: ${messageObject.nick} socketid: ${socket.id}`); // 각 포트에서만 찍힘
        pub.publish(messageObject.roomname, messageObject.nick + ' : ' + messageObject.msg) // pub.publish(보낼 채널(방), 메시지)
    })
}

module.exports = runSocketIo;