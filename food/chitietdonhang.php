<?php
	include "connect.php";
	$json = $_POST['json'];
	// $json = '[{"taikhoanfood":"abc","mafood":27,"tenfood":"Bánh Tiramisu Italia","giafood":60000,"soluongfood":2}]';
	$data = json_decode($json,true);
	foreach ($data as $value) {
		// $id = $value['id'];
		$taikhoanfood = $value['taikhoanfood'];
		$mafood = $value['mafood'];
		$tenfood = $value['tenfood'];
		$giafood = $value['giafood'];
		$soluongfood = $value['soluongfood'];
		$hotenfood = $value['hotenfood'];
		$sodienthoaifood = $value['sodienthoaifood'];
		$diachifood = $value['diachifood'];
		$emailfood = $value['emailfood'];
		// $id = "";
		// $taikhoanfood = "abc";
		// $mafood = "27";
		// $tenfood = "Bánh Tiramisu Italia";
		// $giafood = "60000";
		// $soluongfood = "2";
		$query = "INSERT INTO chitietdonhang (id,taikhoanfood,mafood,tenfood,giafood,soluongfood,hotenfood,sodienthoaifood,diachifood,emailfood) VALUES (null,'$taikhoanfood','$mafood','$tenfood','$giafood','$soluongfood','$hotenfood','$sodienthoaifood','$diachifood','$emailfood')";
		$Dta = mysqli_query($conn,$query);
	}
	if ($Dta) {
		echo "1";
	} else {
		echo "0";
	}
?>