<?php
	/**
	 * Created by PhpStorm.
	 * User: computer
	 * Date: 3/25/17
	 * Time: 10:18 PM
	 */

	/**
	 * @name  : Chuyển tiếng việt có dấu thành không dấu
	 *
	 * @param : $str
	 *
	 * @return: $str
	 */
	function unicode_str_filter($str)
	{
		$unicode = array(
			'a' => 'á|à|ả|ã|ạ|ă|ắ|ặ|ằ|ẳ|ẵ|â|ấ|ầ|ẩ|ẫ|ậ',
			'd' => 'đ',
			'e' => 'é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ',
			'i' => 'í|ì|ỉ|ĩ|ị',
			'o' => 'ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ',
			'u' => 'ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự',
			'y' => 'ý|ỳ|ỷ|ỹ|ỵ',
			'A' => 'Á|À|Ả|Ã|Ạ|Ă|Ắ|Ặ|Ằ|Ẳ|Ẵ|Â|Ấ|Ầ|Ẩ|Ẫ|Ậ',
			'D' => 'Đ',
			'E' => 'É|È|Ẻ|Ẽ|Ẹ|Ê|Ế|Ề|Ể|Ễ|Ệ',
			'I' => 'Í|Ì|Ỉ|Ĩ|Ị',
			'O' => 'Ó|Ò|Ỏ|Õ|Ọ|Ô|Ố|Ồ|Ổ|Ỗ|Ộ|Ơ|Ớ|Ờ|Ở|Ỡ|Ợ',
			'U' => 'Ú|Ù|Ủ|Ũ|Ụ|Ư|Ứ|Ừ|Ử|Ữ|Ự',
			'Y' => 'Ý|Ỳ|Ỷ|Ỹ|Ỵ',
		);
		foreach ($unicode as $nonUnicode => $uni) {
			$str = preg_replace("/($uni)/i", $nonUnicode, $str);
		}

		return $str;
	}

	/**
	 * Check quyen truy cap course
	 *
	 * @return mixed
	 */
	function check_access()
	{
		$ci =& get_instance();
		if (!isset($_SESSION)) {
			session_start();
		}

		$user_data = $ci->session->userdata('user_data');
		if (empty($user_data)) {
			redirect(base_url() . 'login');
			exit;
		} else {
			$user_role_id = $user_data['role_id'];
			if ($user_role_id > 2) {
				$link = substr($_SERVER['REQUEST_URI'], strrpos($_SERVER['REQUEST_URI'], '/') + 1);
				if ($link !='ketrapha_report'){
					echo 'Bạn không được phép truy cập vào đường dẫn này!';
					exit();
				}
			}

			return $user_data;
		}
	}

?>