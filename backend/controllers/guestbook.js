const model = require('../models/guestbook');

module.exports = {
    getGuestbooks: async function(req, res, next) {
        try {
            const results = await model.index();
            res.status(200)
                .send({
                    result: 'success',
                    data: results,
                    message: null
                });
        } catch(err){
          next(err);
        }       
    },
    addGuestbook: async (req, res, next) =>{
        try{
            const guestbookVo = req.body;
            const result = await model.add(guestbookVo);
            const resultData = Object.assign({},guestbookVo, { id : result.insertId});
            
            console.log(result);
            res.status(200)
                .send({
                    result: 'success',
                    data: resultData,
                    message: null
                });
        } catch(error) {
            next(error);
        }
    },

    deleteGuestbook: async (req, res, next) => {
        try{
            const id = req.params['id'];
            console.log(id);
        } catch(error) {
            next(error);
        }
    }
}