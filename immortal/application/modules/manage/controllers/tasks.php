<?php

	Class Tasks extends CI_controller
	{
		public function __construct()
		{
			parent::__construct();
			$this->load->model('tasks_m');
		}

		public function index()
		{
			check_access();
			$this->load->helper('url');
			$form['tasks_list']     = $this->tasks_m->get_list();
			$title['title']            = 'Tasks';
			$active['active_tasks'] = 'class="active"';

			$this->load->view('header', $title);
			$this->load->view('top');
			$this->load->view('menu_left', $active);
			$this->load->view('tasks_view', $form);
			$this->load->view('footer');
		}

		/**
		 * Lấy thông tin task
		 *
		 * @param $id
		 */
		public function ajax_edit($id)
		{
			check_access();
			$data = $this->tasks_m->get_by_id($id);
			echo json_encode($data);
		}

		/**
		 * Thêm mới task
		 */
		public function ajax_add()
		{
			$username = check_access();
			$this->_validate();
			$data = array(
				'title'    => $this->input->post('title'),
				'description'   => $this->input->post('description'),
				'date_from'   => $this->input->post('date_from'),
			);

			$insert = $this->tasks_m->insert($data);
			echo json_encode(array("status" => TRUE));
		}

		/**
		 * Cập nhật thông tin task
		 */
		public function ajax_update()
		{
			$username = check_access();
			$this->_validate();
			$data = array(
				'title'    => $this->input->post('title'),
				'date_from'     => $this->input->post('date_from'),
				'date_to'   => $this->input->post('date_to'),
				'description'    => $this->input->post('description'),
			);
			$this->tasks_m->update($this->input->post('idtask'), $data);
			echo json_encode(array("status" => TRUE));
		}

		/**
		 * Xoá task
		 *
		 * @param $id
		 */
		public function ajax_delete()
		{
			check_access();
			$idtask = $this->input->post('idtask');

			$delete = $this->tasks_m->delete_by_id($idtask);
			echo json_encode(array("status" => TRUE));
		}

		/**
		 * Validate input data
		 *
		 * @param string $action
		 */
		private function _validate()
		{

			$data                 = array();
			$data['error_string'] = array();
			$data['inputerror']   = array();
			$data['status']       = TRUE;

			if ($this->input->post('title') == '') {
				$data['inputerror'][]   = 'title';
				$data['error_string'][] = 'Title cannot be blank';
				$data['status']         = FALSE;
			}

			if ($this->input->post('date_from') == '') {
				$data['inputerror'][]   = 'date_from';
				$data['error_string'][] = 'Date from cannot be blank';
				$data['status']         = FALSE;
            }
            
            if ($this->input->post('date_to') == '') {
				$data['inputerror'][]   = 'date_to';
				$data['error_string'][] = 'Date to cannot be blank';
				$data['status']         = FALSE;
			}

			if ($data['status'] === FALSE) {
				echo json_encode($data);
				exit();
			}
		}

	}

?>