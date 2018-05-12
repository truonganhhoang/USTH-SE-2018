<!-- Main navbar -->
<div class = "navbar navbar-inverse navbar-fixed-top">
	<div class = "navbar-header">
		<a class = "navbar-brand" href = "#"><?php echo SYSTEM_NAME;?></a>

		<ul class = "nav navbar-nav visible-xs-block">
			<li><a data-toggle = "collapse" data-target = "#navbar-mobile"><i class = "icon-tree5"></i></a></li>
			<li><a class = "sidebar-mobile-main-toggle"><i class = "icon-paragraph-justify3"></i></a></li>
		</ul>
	</div>

	<div class = "navbar-collapse collapse" id = "navbar-mobile">
		<ul class = "nav navbar-nav">
			<li><a class = "sidebar-control sidebar-main-toggle hidden-xs"><i class = "icon-paragraph-justify3"></i></a>
			</li>

		</ul>

		<?php if ($this->session->userdata('user_data')) : ?>
			<ul class = "nav navbar-nav navbar-right">

				<li class = "dropdown dropdown-user">
					<a class = "dropdown-toggle" data-toggle = "dropdown">
			<?php if ($this->session->userdata('user_data')['avatar']) : ?>
						<img src = "<?php echo $this->session->userdata('user_data')['avatar'] ?>" class = "img-circle img-sm" alt = "">
			<?php else: ?>
				<img src = "<?php echo base_url('upload/avatar/avatar.png') ?>" class = "img-circle img-sm" alt = "">
			<?php endif; ?>
					<span>
				<?php
				echo $this->session->userdata('user_data')['username'];
				?>
			</span>
						<i class = "caret"></i>
					</a>

					<ul class = "dropdown-menu dropdown-menu-right">
						<li><a href = "#"><i class = "icon-user-plus"></i> My profile</a></li>
						<li><a href = "<?php echo base_url('/login/logout_user') ?>"><i class = "icon-switch2"></i> Logout</a></li>
					</ul>
				</li>
			</ul>
		<?php endif; ?>
	</div>
</div>
<!-- /main navbar -->
