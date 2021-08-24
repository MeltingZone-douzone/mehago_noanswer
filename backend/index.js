const express = require('express');
const session = require('express-session');
const http = require('http');
const path = require('path');
const dotenv = require("dotenv");
const argv = require('minimist')(process.argv.slice(2));

const errorRouter = require('./routes/error');
dotenv.config({ path: path.join(__dirname, 'app.config.env') });

// Application Setup
const application = express();
application.io = require('socket.io')();
const chatRouter = require('./routes/chat')(application.io)

// TODO: socket.io는 router에서 하고 application만 넘겨주고도 해보기


//Logging
const logger = require("./logging");

// process Argument
process.title = argv.name;

application 
            // session environment
            .use(session({
                secret: process.env.SESSION_SECRET,         // 쿠키 변조를 방지하기 위한 값.
                resave: false,              // 요청 처리에서 session의 변경 사항이 없어도 항상 저장.
                saveUninitialized: false    // 새로 session을 생성할 때 "uninitialized" 상태로 둔다. 따라서 로그인 session에서는 false로 하는 것이 좋다.
            }))
            .use(express.urlencoded({extended: true})) 
            .use(express.json())
            .use(express.static(path.join(__dirname, process.env.STATIC_RESOURCES_DIRECTORY)))
            .set("views",path.join(__dirname,"views"))
            // .set("view engine", "ejs")
            .all("*", function(req, res, next) {
                res.locals.req = req;
                res.locals.resp = res;
                next();
            })
            .use('/chat', chatRouter)
            .use(errorRouter.error404)
            .use(errorRouter.error500);

// Server Setup
http.createServer(application)
    .on('listening', function() {
        logger.info(`Http Server Running on Port ${process.env.PORT}`);
    })
    .on('error', function(error) {
        if(error.syscall !== 'listen') {
            throw error;
        }
        switch(error.code) {
            case 'EACCESS' : 
                logger.error(`Port: ${process.env.PORT} requires privileges`);
                process.exit(1);
                break;
            case 'EADDRINUSE' :
                logger.error(`Port: ${process.env.PORT} is already in use`);
                process.exit(1);
                break;
            default:
                throw error;
        }
    })
    .listen(process.env.PORT);