var express = require('express');
var router = express.Router();

var createError = require('http-errors');
var isUser = require('../helpers/is-user');

var mongoose = require('mongoose');
var Post = require('../models/post');
var User = require('../models/user');



//
// Dynamic helpers for res.locals
//
router.use(function (req, res, next) {
  res.locals.isPersonalPage = true;
  next();
});

router.param('username', function (req, res, next, username) {
  User
    .findOne({ username: username })
    .exec(function (err, user) {
      if (err) {
        next(err);
      } else if (!user) {
        next(createError(404));
      } else {
        res.locals.user = req.user = user;
        next();
      }
    });
});

router.param('username', function (req, res, next, username) {
  User
    .findOne({ username: username })
    .populate({ path: 'followers', match: { _id: req.session.userId }})
    .exec(function (err, user) {
      if (err) {
        next(err);
      } else if (!user) {
        next(createError(404));
      } else {
        res.locals.isFollowing = req.session.userId && user.followers.length;
        next();
      }
    });
});



//
// GET listings from user.
//
router.get('/:username', function (req, res, next) {
  Post
    .find({ author: res.locals.user._id })
    .where('createdAt').gt(Date.now() - 7*24*3600*1000)
    .sort({ createdAt: -1 })
    .limit(100)
    .populate('author')
    .exec(function (err, posts) {
      if (err) {
        next(err);
      } else {
        res.render('feed', {
          title: `${req.user.fullName}'s page`,
          posts: posts,
          active: 'user',
        });
      }
    });
});

router.get('/:username/follow', function (req, res, next) {
  req.user.populate('followers', function (err, user) {
    if (err) {
      next(err);
    } else {
      res.render('follow', {
        title: `${req.user.fullName}'s fanclub`,
        followers: user.followers,
        active: req.user._id == req.session.userId ? 'user' : '',
      });
    }
  });
});

router.post('/:username/follow', isUser, function (req, res, next) {
  req.user.update({
    $addToSet: { followers: req.session.userId },
  }, function (err, response) {
    if (err) {
      next(err);
    } else {
      req.flash('success', `Followed ${req.user.fullName}!!`);
      res.redirect(`/user/${req.user.username}`);
    }
  });
})

module.exports = router;
