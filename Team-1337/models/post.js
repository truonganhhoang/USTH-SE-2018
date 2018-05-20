var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var postSchema = Schema({
  body: {
    type: String,
    required: [ true, 'Please fill post body.' ],
    trim: true,
  },
  type: {
    type: String,
    enum: ['text', 'code'],
  },
  link: {
    type: String,
    trim: true,
  },
  createdAt: { type: Date, default: Date.now },
  author: { type: Schema.Types.ObjectId, ref: 'User' },
  cookiesFrom: [{ type: Schema.Types.ObjectId, ref: 'User' }],
});

var Post = mongoose.model('Post', postSchema);

module.exports = Post;
