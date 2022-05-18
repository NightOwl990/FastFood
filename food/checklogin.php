<?php
	include"connect.php";
	mysqli_set_charset($conn,'utf8');
	/** Array for JSON response*/
	$response = array();

		$username = $_POST['taikhoan'];
		$password = $_POST['matkhau'];
		$sqlCheck = "SELECT * FROM user WHERE taikhoan = '$username' AND matkhau = '$password' ";
		$result = mysqli_query($conn,$sqlCheck);
		if (mysqli_num_rows($result) != 0) {	
			$row = mysqli_fetch_assoc($result);
				$response["success"] = 1;
				$response["message"] = "Đăng nhập thành công!";
				$response["username"] = $row['taikhoan'];
				$response["password"] = $row['matkhau'];
			} else {	
				$response["success"] = 0;
				$response["message"] = "Tài khoản hoặc mật khẩu không đúng";
			}	
			/**Return json*/
		echo json_encode($response);

?>