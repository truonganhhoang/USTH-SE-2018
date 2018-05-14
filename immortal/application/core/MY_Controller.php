<?php
	defined('BASEPATH') OR exit('No direct script access allowed');

	class MY_Controller extends CI_Controller
	{
		public function __construct()
		{

			parent::__construct();
			/**
			 * Check quyen truy cap
			 *
			 * @return mixed
			 */
		}

		public
		function check_access()
		{
			if (!isset($_SESSION)) {
				session_start();
			}

			$user_data = $this->session->userdata('user_data');
			if (empty($user_data)) {
				redirect(base_url() . 'login');
				exit;
			} else {
				return $user_data;
			}
		}
	}

?>