// Determine if user is logged in
module.exports = function (req, res, next) {
  if (req.session.userId) {
    next();
  } else {
    req.flash('error', 'Only site members can perform that action. Please log in to continue.');
    res.redirect('/login');
  }
}
