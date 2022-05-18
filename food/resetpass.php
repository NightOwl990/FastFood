<?php
	include"connect.php";
	mysqli_set_charset($conn,'utf8');
	$response = array();
	$username = $_POST['taikhoan'];
	$oldpassword = $_POST['oldmatkhau'];
	$newpassword = $_POST['newmatkhau'];

	// $username = "abc";
	// $oldpassword = "999";
	// $newpassword = "123";

	$sqlCheck = "SELECT * FROM user WHERE matkhau = '$oldpassword' ";
	$result = mysqli_query($conn,$sqlCheck);
	$gt = mysqli_num_rows($result);
	if (mysqli_num_rows($result) == 0) {	
			$response["success"] = 0;
			$response["message"] = "Mật khẩu cũ không chính xác!";
		} else {	
			$sqlUpdate = "UPDATE user SET matkhau='$newpassword' WHERE taikhoan='$username'";
			$resultUpdate = mysqli_query($conn,$sqlUpdate);
			if ($resultUpdate) {
					$response["success"] = 1;
					$response["message"] = "Update password thành công!";
			} else {
				$response["success"] = 0;
				$response["message"] = "Update password thất bại!";
			}
		}	
	echo json_encode($response);
?>