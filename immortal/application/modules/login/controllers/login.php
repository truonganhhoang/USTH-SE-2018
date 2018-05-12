<?php

class login extends CI_Controller
{
	var $md5_salt = '1@ANmC^%^wrFO';

	function index()
	{
		if ($this->session->userdata('isLoggedIn')) {
			redirect('');
		} else {
			$this->show_login(FALSE);
		}
	}

	/**
	 * Show login
	 *
	 * @param bool $show_err
	 */
	function show_login($show_err = FALSE)
	{
		if (!$this->session->userdata('isLoggedIn')) {
			$data['error'] = $show_err;
			$data['title'] = SYSTEM_NAME;
			$this->load->view('login', $data);
		} else {
			//Hien thi ma hinh trang chu
			redirect('/manage/dashboard');
		}
	}

	/**
	 * Check login
	 */
	function login_user()
	{
		$this->load->model('users_m');
		// Grab the username and password from the form POST
		$username = $this->input->post('username');
		$pass = md5($this->md5_salt . sha1($this->input->post("password") . $this->md5_salt));
//		echo $username.'<br>';
//		echo $this->input->post("password").'<br>';
//		echo $pass.'<br>';
//		exit;

		//kiem tra user va password
		if ($username && $pass && $this->users_m->validate_user($username, $pass) || $this->session->userdata('username')) {
			//Hien thi ma hinh trang chu
			$user_data = $this->session->userdata('user_data');
			redirect('/manage/dashboard');
			exit();
		} else {
			//Hien thi man hinh login
			$this->show_login(TRUE);
		}
	}

	function logout_user()
	{
		$this->session->sess_destroy();
		$this->index();
	}
}