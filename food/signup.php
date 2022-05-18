<?php
	include"connect.php";
	mysqli_set_charset($conn,'utf8');
	/** Array for JSON response*/
	$response = array();
	$username = $_POST['taikhoan'];
	$password = $_POST['matkhau'];
	// $name = $_POST['hoten'];
	// $date = $_POST['namsinh'];
	// $place = $_POST['diachi'];
	$sqlCheck = "SELECT * FROM user WHERE taikhoan = '$username' ";
	$result = mysqli_query($conn,$sqlCheck);
	$gt = mysqli_num_rows($result);
	if (mysqli_num_rows($result) != 0) {	
			$response["success"] = 0;
			$response["message"] = "Tên đăng nhập đã có người sử dụng!";
		} else {	
		// $sqlInsert = "INSERT INTO user (taikhoan,matkhau,hoten,namsinh,diachi) VALUES ('$username','$password','$name','$date','$place')
			$sqlInsert = "INSERT INTO user (taikhoan,matkhau) VALUES ('$username','$password')";
			$resultInsert = mysqli_query($conn,$sqlInsert);
			if ($resultInsert) {
					$sqlGetInfo = "SELECT * FROM user WHERE taikhoan = '$username' AND matkhau = '$password'";
					$result = mysqli_query($conn,$sqlGetInfo);
					$row = mysqli_fetch_array($result);
					
					$response["success"] = 1;
					$response["message"] = "Đăng ký thành công!";
					$response["username"] = $username;
					$response["password"] = $password;
					// $response["name"] = $name;
					// $response["date"] = $date;
					// $response["place"] = $place;
			} else {
				$response["success"] = 0;
				$response["message"] = "Đăng ký thất bại!";
			}
		}	
		/**Return json*/
	echo json_encode($response);
?>