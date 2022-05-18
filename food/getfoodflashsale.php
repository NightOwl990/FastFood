<?php
   include "connect.php";
   $mangfoodflashsale = array();
   // Nối hai bảng với nhau thỏa mãn điều kiện 'idfoodsale của bảng foodflashsale' = 'id của bảng food', sau đó sắp xếp thứ tự giảm dần theo 'id của bảng foodflashsale', và giới hạn 30 giá trị. Như thế ta có thể xác định sản phẩm thứ bao nhiêu trong bảng food đang được giảm giá, thông qua việc thêm giá trị cho cột idfoodsale của bảng flashsale.
   $query ="SELECT * FROM `foodflashsale` INNER JOIN `food` ON `foodflashsale`.`idfoodsale`=`food`.`id` ORDER BY `foodflashsale`.`id` DESC LIMIT 30";
   $data = mysqli_query($conn,$query);
   while ($row = mysqli_fetch_assoc($data)){
      array_push($mangfoodflashsale,new Foodflashsale(
         $row['id'],
         $row['tenfood'],
         $row['giafood'],
         $row['hinhanhfood'],
         $row['motafood'],
         $row['idfood'],
         $row['giacu']));
   }
   echo json_encode($mangfoodflashsale);
   class Foodflashsale{
      function __construct ($id,$tenfd,$giamoifd,$hinhanhfd,$motafd,$idfood, $giacufd){
         $this->id=$id;
         $this->tenfd=$tenfd;
         $this->giamoifd=$giamoifd;
         $this->hinhanhfd=$hinhanhfd;
         $this->motafd=$motafd;
         $this->idfood=$idfood;
         $this->giacufd=$giacufd;
    } 
 }
?>