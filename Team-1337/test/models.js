// During the test the env variable is set to test
process.env.NODE_ENV = 'test';

var mongoose = require('mongoose');
var User = require('../models/user');
var Post = require('../models/post');

// Test dependencies
var chai = require('chai');
var server = require('../bin/www');
var should = chai.should();


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

// Init registered users
User.remove(function (err) {
  User.insertMany(users, function (err, users) {});
});

// Initialize test database
Post.remove(function (err) {
  Post.insertMany(posts, function (err, posts) {});
});


describe('\n\n*** MODELS TESTS ***', function () {

  //
  // First model
  //
  describe('\n -- User', function () {

    // // Initialize test database
    // beforeEach(function (done) {
    //   User.remove().exec();
    //   User.insertMany(users, done);
    // });

    describe('Find All Users', function () {
      it('should return every users', function (done) {
        User.find({}, function (err, users) {
          if (err) return done(err);
          users.should.have.length(2);
          done();
        });
      });
    });

    describe('Find by Username', function () {
      it('should find one user or return null', function (done) {
        User.findOne({ username: 'morph' }, function (err, user) {
          if (err) return done(err);
          user.should.not.be.null;
          done();
        });
      });
    });

    describe('Update User Info', function () {
      it('should update relevant fields', function (done) {

        User.findOneAndUpdate({ username: 'morph' }, {
          description: 'Check out my new description!',
          location: 'MA',
        }, { new: true }, function (err, user) {
          if (err) return done(err);
          user.description.should.have.lengthOf(29);
          user.location.should.equal('MA');
          done();
        });

      });
    });

  });



  //
  // Second model
  //
  describe('\n -- Post', function () {

    // // Init registered users
    // before(function (done) {
    //   User.remove(function (err) {
    //     User.insertMany(users, done);
    //   });
    // });

    // // Initialize test database
    // beforeEach(function (done) {
    //   Post.remove(function (err) {
    //     Post.insertMany(posts, done);
    //   });
    // });

    describe('Find All Posts', function () {
      it('should return posts with their correct authors', function (done) {
        Post.find().sort({ createdAt: 1 }).exec(function (err, posts) {
          if (err) return done(err);
          posts.should.have.lengthOf(3);
          posts[0].author.should.eql(users[0]._id);
          posts[1].author.should.eql(users[1]._id);
          done();
        });
      });
    });

    describe('Find by Author', function () {
      it('should return all posts by the specified user', function (done) {
        Post.find({ author: users[1]._id }, function (err, posts) {
          if (err) return done(err);
          posts.should.have.lengthOf(2);
          done();
        });
      });
    });

  });

});
