<?php
	include "connect.php";
	$page = $_GET['page'];
	// $page = "1";
	$idfd = $_POST['idfood'];
	// $idfd = "1";
	$space = 6;
	$limit = ($page - 1) * $space;
	$mangfood = array();
	$query = "SELECT * FROM food WHERE idfood = $idfd LIMIT $limit,$space";
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)){
		array_push($mangfood, new Food(
			$row['id'],
			$row['tenfood'],
			$row['giafood'],
			$row['hinhanhfood'],
			$row['motafood'],
			$row['idfood']));
	}
	echo json_encode($mangfood);
	class Food{
		function __construct ($id,$tenfd,$giafd,$hinhanhfd,$motafd,$idfood){
			$this->id = $id;
			$this->tenfd = $tenfd;
			$this->giafd = $giafd;
			$this->hinhanhfd = $hinhanhfd;
			$this->motafd = $motafd;
			$this->idfood = $idfood;

		}
	}
?>