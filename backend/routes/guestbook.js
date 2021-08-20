const express = require('express');
const controller = require('../controllers/guestbook');

const router = express.Router();

router.route('/guestbook').get(controller.getGuestbooks);
router.route('/guestbook/add').post(controller.addGuestbook);
router.route('/guestbook/delete/:id').get(controller.deleteGuestbook);


module.exports = router;