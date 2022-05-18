package com.example.foodwebservice;

public class Sever {
    public static String localhost = "192.168.1.2";
    public static String LoginUser = "http://" + localhost + "/food/checklogin.php";
    public static String SignUpUser = "http://" + localhost + "/food/signup.php";
    public static String FoodMoiNhat = "http://" + localhost + "/food/getfoodmoinhat.php";
    public static String Chitietdonhang = "http://" + localhost + "/food/chitietdonhang.php";
    public static String LoaiFood = "http://" + localhost + "/food/getloaifood.php";
    public static String getPizza = "http://" + localhost + "/food/getfood.php";
    public static String getFlashSale = "http://" + localhost + "/food/getfoodflashsale.php";
    public static String getUser = "http://" + localhost + "/food/getuser.php";
    public static String UpdateUser = "http://" + localhost + "/food/updateuser.php";
    public static String RePassWord = "http://" + localhost + "/food/resetpass.php";
}
