<?php
	include "connect.php";

	$taikhoan=$_POST['taikhoan'];
	$hoten=$_POST['hoten'];
	$namsinh=$_POST['namsinh'];
	$diachi=$_POST['diachi'];
	$email=$_POST['email'];
	$sodienthoai=$_POST['sodienthoai'];

	// $taikhoan="abc";
	// $hoten="chinhzxc";
	// $namsinh="1999";
	// $diachi="Hà Nội";
	// $email="hanoi@gmail.com";
	// $sodienthoai="0999999999";

	// $manguser = array();
	$query = "UPDATE user SET hoten='$hoten', namsinh='$namsinh', diachi='$diachi', email='$email', sodienthoai='$sodienthoai' WHERE taikhoan='$taikhoan' ";
	$data = mysqli_query($conn, $query);

	// $query1 = "SELECT * FROM user WHERE taikhoan = '$taikhoan'";
	// $data1 = mysqli_query($conn, $query1);
	// while ($row = mysqli_fetch_assoc($data1)){
	// 	array_push($manguser, new User(
	// 		$row['id'],
	// 		$row['taikhoan'],
	// 		$row['matkhau'],
	// 		$row['hoten'],
	// 		$row['namsinh'],
	// 		$row['diachi'],
	// 		$row['email'],
	// 		$row['sodienthoai']));
	// }
	// echo json_encode($manguser);
	// class User{
	// 	function __construct ($iduser,$taikhoanuser,$matkhauuser,$hotenuser,$namsinhuser,$diachiuser,$emailuser, $sodienthoaiuser){
	// 		$this->iduser = $iduser;
	// 		$this->taikhoanuser = $taikhoanuser;
	// 		$this->matkhauuser = $matkhauuser;
	// 		$this->hotenuser = $hotenuser;
	// 		$this->namsinhuser = $namsinhuser;
	// 		$this->diachiuser = $diachiuser;
	// 		$this->emailuser = $emailuser;
	// 		$this->sodienthoaiuser = $sodienthoaiuser;

	// 	}
	// }
?>