<?php
	include "connect.php";
	$mangallfood = array();
	$query = "SELECT * FROM food";
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)){
		array_push($mangallfood, new AllFood(
			$row['id'],
			$row['tenfood'],
			$row['giafood'],
			$row['hinhanhfood'],
			$row['motafood'],
			$row['idfood']));
	}
	echo json_encode($mangallfood, JSON_UNESCAPED_UNICODE);
	class AllFood{
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