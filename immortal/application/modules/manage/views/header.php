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

	<link href = "<?php echo base_url('assets/images/to_do_list.png') ?>" rel = "shortcut icon" type = "image/x-icon"/>
	<!-- Global stylesheets -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel = "stylesheet"
	      type = "text/css">
	<link href="<?php echo base_url('assets/css/icons/icomoon/styles.css') ?>" rel = "stylesheet" type = "text/css">
	<link href="<?php echo base_url('assets/css/bootstrap.css') ?>" rel = "stylesheet" type = "text/css">
	<link href="<?php echo base_url('assets/css/core.css') ?>" rel = "stylesheet" type = "text/css">
	<link href="<?php echo base_url('assets/css/components.css') ?>" rel = "stylesheet" type = "text/css">
	<link href="<?php echo base_url('assets/css/colors.css') ?>" rel = "stylesheet" type = "text/css">
	<link href="<?php echo base_url('assets/css/mycss.css') ?>" rel = "stylesheet" type = "text/css">
	<!-- /global stylesheets -->

	<!-- Core JS files -->
	<script type = "text/javascript" src="<?php echo base_url('assets/js/plugins/loaders/pace.min.js') ?>"></script>
	<script type = "text/javascript" src="<?php echo base_url('assets/js/core/libraries/jquery.min.js') ?>"></script>
	<script type = "text/javascript" src="<?php echo base_url('assets/js/core/libraries/bootstrap.min.js') ?>"></script>
	<script type = "text/javascript" src="<?php echo base_url('assets/js/plugins/loaders/blockui.min.js') ?>"></script>
	<!-- /core JS files -->

	<!-- Theme JS files -->
	<script type = "text/javascript" src="<?php echo base_url('assets/js/plugins/visualization/d3/d3.min.js') ?>"></script>
	<script type = "text/javascript" src="<?php echo base_url('assets/js/plugins/forms/selects/bootstrap_multiselect.js') ?>"></script>
	<script type = "text/javascript" src="<?php echo base_url('assets/js/plugins/pickers/daterangepicker.js') ?>"></script>
	<script type = "text/javascript" src="<?php echo base_url('assets/js/plugins/ui/nicescroll.min.js') ?>"></script>

	<script type = "text/javascript" src="<?php echo base_url('assets/js/pages/layout_fixed_custom.js') ?>"></script>
	<script type="text/javascript" src="<?php echo base_url('assets/js/plugins/forms/selects/select2.min.js') ?>"></script>

	<script type="text/javascript" src="<?php echo base_url('assets/js/core/app.js') ?>"></script>
<!--	Datatables-->
	<script type="text/javascript" src="<?php echo base_url('assets/js/plugins/tables/datatables/datatables.min.js') ?>"></script>
	<script type="text/javascript" src="<?php echo base_url('assets/js/plugins/tables/datatables/extensions/responsive.min.js') ?>"></script>
	<script type="text/javascript" src="<?php echo base_url('assets/js/pages/datatables_responsive.js') ?>"></script>

<!--	select search-->

	<script type="text/javascript" src="<?php echo base_url('assets/js/core/libraries/jquery_ui/interactions.min.js') ?>"></script>
	<script type="text/javascript" src="<?php echo base_url('assets/js/pages/form_select2.js') ?>"></script>

	<script type="text/javascript" src="<?php echo base_url('assets/js/plugins/uploaders/fileinput.min.js') ?>"></script>
	<script type="text/javascript" src="<?php echo base_url('assets/js/pages/uploader_bootstrap.js') ?>"></script>
<!-- /theme JS files -->

<!-- JQuery Confirm -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.js"></script>
<!-- End JQuery Confirm -->
	<script type="text/javascript" src="<?php echo base_url('ckeditor/ckeditor.js') ?>"></script>

</head>

<body class = "navbar-top" >
