<?php 
    class Hello_World extends CI_controller {
        public function index() {
		    $this->load->view('hello_world_view');
	    }
    }
?>