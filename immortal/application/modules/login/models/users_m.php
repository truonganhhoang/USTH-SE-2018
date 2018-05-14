<?php

/**
 * Class user_m
 */
class Users_m extends MY_Model
{
	var $table = 'users';
	var $details;



	function validate_user($username, $password)
	{
		// Build a query to retrieve the user's details
		// based on the received username and password
		$this->db->from($this->table);
		$this->db->where('username', $username);
		$this->db->where('password', md5($password));
		$this->db->where('status_delete_yn', 'n');
		$login = $this->db->get()->result();

		// The results of the query are stored in $login.
		// If a value exists, then the user account exists and is validated
		if (is_array($login) && count($login) == 1) {
			// Set the users details into the $details property of this class
			$this->details = $login[0];
			// Call set_session to set the user's session vars via CodeIgniter
			$this->set_session();

			return TRUE;
		}

		return FALSE;
	}

	function set_session()
	{
		// session->set_userdata is a CodeIgniter function that
		// stores data in CodeIgniter's session storage.  Some of the values are built in
		// to CodeIgniter, others are added.  See CodeIgniter's documentation for details.
		$this->session->set_userdata('user_data',array(
				'user_id'         => $this->details->id,
				'username'   => $this->details->username,
				'fullname'   => $this->details->lastname . ' ' . $this->details->firstname,
				'avatar'      => $this->details->avatar,
				'email'      => $this->details->email,
				'role_id'      => $this->details->role_id,
				'isLoggedIn' => TRUE
			)
		);
	}

	public function isset_data_gv($username) {
		$this->db->from($this->table);
		$this->db->where('username', $username);
		$query  = $this->db->get();
		$result = $query->row();
		if ($result)
			return $result->id;
		else return NULL;
	}

	public function get_info_by_username($username) {
		$this->db->from($this->table);
		$this->db->where('username', $username);
		$query = $this->db->get();
		$result = $query->row();
		if ($result)
			return $result;
		else return NULL;
	}
}
