<!-- Page container -->
<div class="page-container">

	<!-- Page content -->
	<div class="page-content">

		<!-- Main sidebar -->
		<div class="sidebar sidebar-main sidebar-fixed">
			<div class="sidebar-content">

				<!-- User menu -->
				<?php if ($this->session->userdata('user_data')) { ?>
					<div class="sidebar-user">
						<div class="category-content">
							<div class="media">
								<a href="#" class="media-left">
									<?php if ($this->session->userdata('user_data')['avatar']) : ?>
										<img src="<?php echo $this->session->userdata('user_data')['avatar']; ?>"
											 class="img-circle img-sm" alt="">
									<?php else: ?>
										<img src="<?php echo base_url('upload/avatar/avatar.png') ?>"
											 class="img-circle img-sm" alt="">
									<?php endif; ?>
								</a>

								<div class="media-body">
								<span class="media-heading text-semibold">
									<?php
										echo $this->session->userdata('FullName');
									?>
								</span>

									<div class="text-size-mini text-muted">
										<i class="icon-pin text-size-small"></i> &nbsp; HN
									</div>
								</div>

								<div class="media-right media-middle">
									<ul class="icons-list">
										<li>
											<a href="#"><i class="icon-user-plus"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				<?php } ?>
				<!-- /user menu -->

				<!-- Main navigation -->
				<div class="sidebar-category sidebar-category-visible">
					<div class="category-content no-padding">
						<ul class="navigation navigation-main navigation-accordion">
							<!-- Main -->
                            <li <?php if (isset($active_home)) echo $active_home; ?>>
                                <a href="<?php echo base_url('manage/dashboard') ?>"><i
												class="glyphicon glyphicon-home"></i> <span>Dashboard</span></a>
							</li>

							<li <?php if (isset($active_tasks)) echo $active_tasks; ?>>
                                <a href="<?php echo base_url('manage/tasks') ?>"><i
												class="glyphicon glyphicon-list-alt"></i> <span>Tasks</span></a>
							</li>

							<!-- /main -->

						</ul>
					</div>
				</div>
				<!-- /main navigation -->

			</div>
		</div>
		<!-- /main sidebar -->
