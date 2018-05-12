<?php
//ACL - Access List Control
class ACL
{
	var $CI;

	function __construct($param=array())//$param la array()
	{
		$this->CI =& get_instance();//ke thua bien CI cua CodeIgniter
		$this->CI->load->library("Session");
		$this->initialize($param);
	}
	
	function initialize($param=array())//dua gia tri, dua tham so vao library
	{
		if(count($param) > 0)
		{
			foreach($param as $k=>$v)
			{
				$this->$k = $v;	
			}
		}
	}

	function setPermission($group,$class,$function,$allow=false)
	{
		if($this->CI->session->userdata("permission")!="")
		{
			$per = $this->CI->session->userdata("permission");
		}
		
		if($function=="")//tat ca function
		{
			foreach(get_class_methods($class) as $k=>$fname)
			{
				if($fname != "__construct" and $fname != "get_instance")
				{
					$per[$group][$class][$fname] = $allow;	
				}
			}
		}
		else
		{
			$per[$group][$class][$function] = $allow;
		}

		$this->CI->session->set_userdata("permission",$per);

	}
	
	function checkPermission($group,$class,$function)
	{
		$per = $this->CI->session->userdata("permission");
		
		if($per[$group][$class][$function] == false)
		{
			echo "Ban khong co quyen voi chuc nang nay";
			exit;//Stop	
		}
	}
	
	function testFunc()
	{
		echo "Day la ACL Library<br>";
		echo $this->config1."<br>";
		echo $this->config2."<br>";	
		
	}

}
?>