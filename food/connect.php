<?php
$host="localhost";
$username="root";
$password="";
$database="food";
$conn=mysqli_connect($host,$username,$password,$database);
mysqli_query($conn,"SET NAMES 'utf8'");
if($conn){
echo"";
}else{
    echo"Kết nối thất bại";
}
?>