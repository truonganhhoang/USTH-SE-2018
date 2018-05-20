var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var userSchema = Schema({
  fullName: {
    type: String,
    required: [ true, 'Full name cannot be empty.' ],
    minlength: [ 3, 'Full name is too short.' ],
    maxlength: [ 30, 'Full name is too long.' ],
    trim: true,
  },
  username: {
    type: String,
    required: [ true, 'Username cannot be empty.' ],
    unique: [ true, 'Username was already taken.' ],
    minlength: [ 3, 'Username is too short.' ],
    maxlength: [ 30, 'Username is too long.' ],
    lowercase: true,
    trim: true,
  },
  password: {
    type: String,
    required: [ true, 'Password cannot be empty.' ],
    minlength: [ 3, 'Password is too short.' ],
    maxlength: [ 50, 'Password is too long.' ],
  },
  description: {
    type: String,
    trim: true,
  },
  location: {
    type: String,
    trim: true,
  },
  link: {
    type: String,
    trim: true,
  },
  quote: {
    type: String,
    trim: true,
  },
  disableGames: Boolean,
  createdAt: { type: Date, default: Date.now },
  followers: [{ type: Schema.Types.ObjectId, ref: 'User' }],
});

var User = mongoose.model('User', userSchema);

module.exports = User;
