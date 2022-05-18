<?php
	include "connect.php";
	$tkuser = $_POST['taikhoan'];
	// $tkuser = "chinh";
	$manguser = array();
	$query = "SELECT * FROM user WHERE taikhoan = '$tkuser'";
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)){
		array_push($manguser, new User(
			$row['id'],
			$row['taikhoan'],
			$row['matkhau'],
			$row['hoten'],
			$row['namsinh'],
			$row['diachi'],
			$row['email'],
			$row['sodienthoai']));
	}
	echo json_encode($manguser);
	class User{
		function __construct ($iduser,$taikhoanuser,$matkhauuser,$hotenuser,$namsinhuser,$diachiuser,$emailuser, $sodienthoaiuser){
			$this->iduser = $iduser;
			$this->taikhoanuser = $taikhoanuser;
			$this->matkhauuser = $matkhauuser;
			$this->hotenuser = $hotenuser;
			$this->namsinhuser = $namsinhuser;
			$this->diachiuser = $diachiuser;
			$this->emailuser = $emailuser;
			$this->sodienthoaiuser = $sodienthoaiuser;

		}
	}
?>