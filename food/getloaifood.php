<?php
    include "connect.php";
    $query = "SELECT * FROM loaifood";
    $data = mysqli_query($conn, $query);
    $mangloaifd = array();
    while($row = mysqli_fetch_assoc($data)){
        array_push($mangloaifd, new Loaifood(
            $row['id'],
            $row['tenloaifood'],
            $row['hinhanhloaifood']));
    }
    echo json_encode($mangloaifd);

    
    class Loaifood{
        function __construct ($id, $tenloaifd, $hinhanhloaifd){
            $this->id = $id;
            $this->tenloaifd = $tenloaifd;
            $this->hinhanhloaifd = $hinhanhloaifd;
        }
    }
?>