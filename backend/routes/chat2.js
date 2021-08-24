const express = require('express');
const dbconn = require("../models/dbconn");
const util = require("util");

const router = express.Router();

router.get('/room', async (req, res) => {
    const roomId = req.query.room_id;
    console.log(`/room 접속함 roomId는 ${roomId}`);
    // if(!roomId) {
    //     throw new Error();
    // }

/*     try {
        // const chatting = await Chatting.findAll({
        //     attributes: ["id", "room_id", "send_user_id", "message", "not_read", "createdAt"],
        //     where:{
        //         id: { [Sequelize.Op.lt]: cursor },
        //         room_id
        //     },
        //     order: [["id", "DESC"]],
        //     limit: 15,
        // })

        // return res.json({
        //     data: chatting.reverse(),
        //     msg: "채팅 목록을 불러왔습니다."
        // })
        const conn = dbconn();
        const query = util.promisify(conn.query).bind(conn);

        try {
            return await query("SELECT no, chatting_room_no, message, not_read_count, created_at")
        } catch (error) {
            
        }
        
        return res.status(200)
            .json({
                result: 'success',
                data: chatting.reverse(),
                message: '채팅 목록 불러오기 완료'
            })
    } catch (err) {
        return res.status(400)
            .json({
                data: false,
                msg: "채팅 목록 불러오기 실패"
            })
    } */

})

module.exports = router;