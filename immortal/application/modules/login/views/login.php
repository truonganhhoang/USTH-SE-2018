<!DOCTYPE html>
<html lang = "en">
<head>
	<meta charset = "utf-8">
	<meta http-equiv = "X-UA-Compatible" content = "IE=edge">
	<meta name = "viewport" content = "width=device-width, initial-scale=1">
	<title>
		<?php
		if (isset($title)) {
			echo $title;
		}
		?>
	</title>

	<!-- Global stylesheets -->
	<link href = "<?php echo base_url('assets/images/to_do_list.png') ?>" rel = "shortcut icon" type = "image/x-icon"/>
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
	<link href="<?php echo base_url('assets/css/icons/icomoon/styles.css') ?>" rel="stylesheet" type="text/css">
	<link href="<?php echo base_url('assets/css/bootstrap.css') ?>" rel="stylesheet" type="text/css">
	<link href="<?php echo base_url('assets/css/core.css') ?>" rel="stylesheet" type="text/css">
	<link href="<?php echo base_url('assets/css/components.css') ?>" rel="stylesheet" type="text/css">
	<link href="<?php echo base_url('assets/css/colors.css') ?>" rel="stylesheet" type="text/css">
	<!-- /global stylesheets -->

	<!-- Core JS files -->
	<script type="text/javascript" src="<?php echo base_url('assets/js/plugins/loaders/pace.min.js') ?>"></script>
	<script type="text/javascript" src="<?php echo base_url('assets/js/core/libraries/jquery.min.js') ?>"></script>
	<script type="text/javascript" src="<?php echo base_url('assets/js/core/libraries/bootstrap.min.js') ?>"></script>
	<script type="text/javascript" src="<?php echo base_url('assets/js/plugins/loaders/blockui.min.js') ?>"></script>
	<!-- /core JS files -->


	<!-- Theme JS files -->
	<script type = "text/javascript" src = "<?php echo base_url('assets/js/core/app.js') ?>"></script>
	<!-- /theme JS files -->

</head>

<body class = "login-container">

<!-- Page container -->
<div class = "page-container">

	<!-- Page content -->
	<div class = "page-content">

		<!-- Main content -->
		<div class = "content-wrapper">

			<!-- Content area -->
			<div class = "content">
				<div align = "center"><h1><?php echo SYSTEM_NAME; ?></h1></div>
				<!-- Simple login form -->
				<form action = "<?php echo base_url('login/login_user') ?>" method="post">
					<div class = "panel panel-body login-form">
						<div class = "text-center">
							<div class = "icon-object border-slate-300 text-slate-300"><i class = "icon-task"></i>
							</div>
							<h5 class = "content-group">Login to your account
								<small class = "display-block">Enter your credentials below</small>
							</h5>
						</div>
						<?php if (isset($error) && $error): ?>
							<div class="text-danger" align="center">
								<a class="close" data-dismiss="alert" href="#">×</a>Incorrect Username or Password!
							</div>
						<?php endif; ?>

						<div class = "form-group has-feedback has-feedback-left">
							<input type = "text" class = "form-control" placeholder = "username" name="username">

							<div class = "form-control-feedback">
								<i class = "icon-user text-muted"></i>
							</div>
						</div>

						<div class = "form-group has-feedback has-feedback-left">
							<input type = "password" class = "form-control" placeholder = "Password" name="password">

							<div class = "form-control-feedback">
								<i class = "icon-lock2 text-muted"></i>
							</div>
						</div>

						<div class = "form-group">
							<button type = "submit" class = "btn btn-primary btn-block">Sign in <i
									class = "icon-circle-right2 position-right"></i></button>
						</div>

						<div class = "text-center">
							<a href = "login_password_recover.html">Forgot password?</a>
						</div>
					</div>
				</form>
				<!-- /simple login form -->

			</div>
			<!-- /content area -->

		</div>
		<!-- /main content -->

	</div>
	<!-- /page content -->

</div>
<!-- /page container -->

</body>
</html>
