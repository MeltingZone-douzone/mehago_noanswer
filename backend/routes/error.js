const logger = require("../logging");
const path = require('path')

module.exports = {
    error404: (req, res) => {
        if(req.accepts("html")){
            // res.status(404).render("error/404")
            res.sendFile(path.join(__dirname, "../views/error/404.html"))
            return;
        }

        res.status(400).send({
            result: 'fail',
            data: null,
            message: "Unknown Request"
        });
    },

    error500: (error, req, res, next) =>{
        // 로깅처리
        //error.name, error.message, error.stack
        logger.error(error.stack);

        // 응답
        if(req.accepts("html")) {
            // res.status(500).render("error/500", {
                res.sendFile(path.join(__dirname, "../views/error/404.html"), {
                error: error.stack
            }); 
            // res.status(500).send(`<pre>${error.stack}</pre>`); // 개발시 편함을 위함
            return;
        }

        res.status(500).send({
            result: "fail",
            data: null,
            message: error.stack
        })
    }
}