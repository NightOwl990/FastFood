<?php
	include "connect.php";
	$mangfoodmoinhat = array();
	$query = "SELECT * FROM food ORDER BY ID DESC LIMIT 6";
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)){
		array_push($mangfoodmoinhat, new Foodmoinhat(
			$row['id'],
			$row['tenfood'],
			$row['giafood'],
			$row['hinhanhfood'],
			$row['motafood'],
			$row['idfood']));
	}
	echo json_encode($mangfoodmoinhat, JSON_UNESCAPED_UNICODE);
	class Foodmoinhat{
		function __construct ($id,$tenfd,$giafd,$hinhanhfd,$motafd,$idfd){
			$this->id=$id;
			$this->tenfd=$tenfd;
			$this->giafd=$giafd;
			$this->hinhanhfd=$hinhanhfd;
			$this->motafd=$motafd;
			$this->idfd=$idfd;

		}
	}
?>