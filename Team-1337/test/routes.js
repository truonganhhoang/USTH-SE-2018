// During the test the env variable is set to test
process.env.NODE_ENV = 'test';

var mongoose = require('mongoose');
var User = require('../models/user');
var Post = require('../models/post');

// Test dependencies
var chai = require('chai');
let chaiHttp = require('chai-http');
var server = require('../bin/www');
var should = chai.should();

chai.use(chaiHttp);
var requester = chai.request.agent(server);


//
// Example database documents
//
var users = [new User({
  fullName: 'Demo',
  username: 'demo',
  password: 'demo',
  description: 'A special first user.',
}), new User({
  fullName: 'Mordred Phain',
  username: 'morph',
  password: 'morph',
  description: 'Doctor.',
  location: 'NY',
  link: 'http://9v.lt/blog/category/general/dr-noire-by-recon/',
  quote: 'Sit down, Mr. Naissance. I’ve got a job for you.',
})];

var posts = [new Post({
  body: 'Demo post.\nNot much text here.',
  type: 'text',
  author: users[0]._id,
}), new Post({
  body: 'Check out the link below for more info.',
  type: 'text',
  link: 'http://lmgtfy.com/?q=Evilzone',
  author: users[1]._id,
}), new Post({
  body: 'Second post by the second user.\n\nIts font is monospaced.',
  type: 'code',
  author: users[1]._id,
})];



describe('\n\n*** ROUTES TESTS ***', function () {

  //
  // Index Routes
  //
  describe('\n -- Top level Routes', function () {

    beforeEach(function (done) {
      User.remove(function (err) {
        User.insertMany(users, done);
      });
    });

    beforeEach(function (done) {
      Post.remove(function (err) {
        Post.insertMany(posts, done);
      });
    });

    describe('Go to Homepage', function () {
      it('should welcome visitors to the page and show list of all recent posts', function (done) {
        requester
          .get('/')
          .end(function (err, res) {
            res.should.have.status(200);
            res.text.should.include('Welcome');
            res.text.should.include('Demo post.');
            res.text.should.include('Check out the link below for more info.');
            res.text.should.include('Second post by the second user.');
            done();
          });
      });
    });

    describe('Go to Login Page', function () {
      it('should render page without error', function (done) {
        requester
          .get('/login')
          .end(function (err, res) {
            res.should.have.status(200);
            done();
          });
      });
    });

    describe('Go to Signup Page', function () {
      it('should render page without error', function (done) {
        requester
          .get('/signup')
          .end(function (err, res) {
            res.should.have.status(200);
            done();
          });
      });
    });

    describe('Go to Add Post', function () {
      it('should require authentication', function (done) {
        requester
          .get('/add')
          .end(function (err, res) {
            res.redirects[0].endsWith('/login').should.be.true;
            done();
          });
      });
    });

  });



  //
  // User Routes
  //
  describe('\n -- User Routes', function () {

    beforeEach(function (done) {
      User.remove(function (err) {
        User.insertMany(users, done);
      });
    });

    beforeEach(function (done) {
      Post.remove(function (err) {
        Post.insertMany(posts, done);
      });
    });

    describe('Go to User Page', function () {
      it('should show user profile and a list of posts by user', function (done) {
        requester
          .get('/user/demo')
          .end(function (err, res) {
            res.should.have.status(200);
            res.text.should.include('A special first user.');
            res.text.should.include('Demo post.');
            done();
          });
      });
    });

    describe('Go to User Followers Page', function () {
      it('should show followers of a user', function (done) {
        requester
          .get('/user/demo/follow')
          .end(function (err, res) {
            res.should.have.status(200);
            res.text.should.include('A special first user.');
            res.text.should.include('Maybe');
            done();
          });
      });
    });

    describe('Follow a User', function () {
      it('should require authentication', function (done) {
        requester
          .post(`/user/${users[1].username}/follow`)
          .end(function (err, res) {
            res.redirects[0].endsWith('/login').should.be.true;
            done();
          });
      });
    });

  });


  //
  // Authenticated Routes
  //
  describe('\n -- Authenticated Routes', function () {

    before(function (done) {
      requester
        .post('/login')
        .send({ username: users[0].username, password: users[0].password })
        .end(function (err, res) {
          res.redirects[0].endsWith('/').should.be.true;
          done();
        });
    });

    beforeEach(function (done) {
      User.remove(function (err) {
        User.insertMany(users, done);
      });
    });

    beforeEach(function (done) {
      Post.remove(function (err) {
        Post.insertMany(posts, done);
      });
    });

    describe('Write a Post', function () {
      it('should add a new post to the site when logged in', function (done) {
        requester
          .post('/add')
          .send({ body: 'Brand new post.', type: 'text', author: users[0]._id })
          .end(function (err, res) {
            res.redirects[0].endsWith('/').should.be.true;
            res.text.should.include('Brand new post.');
            done();
          });
      });
    });

    describe('Follow another User', function () {
      it('should become a follower', function (done) {
        requester
          .post(`/user/${users[1].username}/follow`)
          .end(function (err, res) {
            res.redirects[0].endsWith(`/user/${users[1].username}`).should.be.true;
            res.text.should.include('Followed');
            done();
          });
      });
    });
  });
});
