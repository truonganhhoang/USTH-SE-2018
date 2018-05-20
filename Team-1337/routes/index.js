var express = require('express');
var router = express.Router();

var createError = require('http-errors');
var isUser = require('../helpers/is-user');

var Post = require('../models/post');
var User = require('../models/user');



// Dynamic helpers for res.locals
router.use('/', function (req, res, next) {
  User
    .findById(req.session.userId)
    .populate({ path: 'followers', match: { _id: req.session.userId }})
    .exec(function (err, user) {
      if (err) {
        next(err);
      } else {
        res.locals.isFollowing = user && user.followers.length;
        next();
      }
    });
});



//
// GET home page.
//
router.get('/', function(req, res, next) {
  // Find recent posts (< 7 days)
  Post
    .find()
    .where('createdAt').gt(Date.now() - 7*24*3600000)
    .sort({ createdAt: -1 })
    .limit(100)
    .populate('author')
    .exec(function (err, posts) {
      if (err) {
        next(err);
      } else {
        res.render('feed', {
          title: !req.session.userId ? 'Welcome to 1337er!' : 'Welcome, ' + res.locals.user.fullName,
          posts: posts,
          active: 'home',
        });
      }
    });
});



//
// Write new post
//
router.get('/add', isUser, function (req, res, next) {
  res.render('add', {
    title: 'New Post',
  })
});

router.post('/add', isUser, function (req, res, next) {
  Post.create(req.body, function (err, post) {
    if (err && err.errors) {
      Object.keys(err.errors).map(field => req.flash('error', err.errors[field].message));
      res.redirect('/add');
    } else if (err) {
      req.flash('error', 'Post wasn\'t produced. Please try again later.');
      res.redirect('/add');
    } else {
      req.flash('success', 'Post written!! Check it out below.');
      res.redirect('/');
    }
  });
});



//
// Login
//
router.get('/login', function (req, res, next) {
  if (req.session.userId) {
    res.redirect('/');
  } else {
    res.render('login', {
      title: 'Login',
    });
  }
});

router.get('/logout', isUser, function (req, res, next) {
  delete req.session.userId;
  req.flash('success', 'You have successfully logged out.');
  res.redirect('/');
});

router.post('/login', function (req, res, next) {
  // router.all('/login', function (req, res, next) {
  var credentials = {
    username: req.body.username,
    password: req.body.password,
  };

  User.findOne(credentials, function (err, user) {
    if (err) {
      next(err);
    } else if (user) {
      req.session.userId = user._id;
      res.redirect('/');
    } else {
      req.flash('error', 'Invalid username and password. Please try again.');
      res.redirect('/login');
    }
  })
});



//
// Sign Up
//
router.get('/signup', function (req, res, next) {
  if (req.session.userId) {
    res.redirect('/');
  } else {
    res.render('signup', {
      title: 'Sign up',
    });
  }
});

router.post('/signup', function (req, res, next) {
  // router.all('/signup', function (req, res, next) {
  if (req.body.password !== req.body.confirmPassword) {
    req.flash('error', 'Passwords do not match.')
    res.redirect('/signup');
  } else if (!req.body.termsAgreement) {
    req.flash('error', 'You must agree to our terms of service.')
    res.redirect('/signup');
  } else {
    User.create(req.body, function (err, user) {
      if (err && err.errors) {
        Object.keys(err.errors).map(field => req.flash('error', err.errors[field].message));
        res.redirect('/signup');
      } else if (err) {
        req.flash('error', 'Username was already taken.');
        res.redirect('/signup');
      } else {
        req.session.userId = user._id;
        req.flash('success', 'You have successfully created an account. Enjoy!');
        res.redirect('/');
      }
    });
  }
});

module.exports = router;
