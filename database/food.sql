-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 18, 2022 lúc 05:06 AM
-- Phiên bản máy phục vụ: 10.4.20-MariaDB
-- Phiên bản PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `food`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `taikhoanfood` text NOT NULL,
  `mafood` int(11) NOT NULL,
  `tenfood` text NOT NULL,
  `giafood` int(11) NOT NULL,
  `soluongfood` int(11) NOT NULL,
  `hotenfood` text NOT NULL,
  `sodienthoaifood` int(11) NOT NULL,
  `diachifood` text NOT NULL,
  `emailfood` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `taikhoanfood`, `mafood`, `tenfood`, `giafood`, `soluongfood`, `hotenfood`, `sodienthoaifood`, `diachifood`, `emailfood`) VALUES
(25, 'abc', 27, 'Bánh Tiramisu Italia', 60000, 2, 'mnb', 999, 'mnb', 'chinh@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `food`
--

CREATE TABLE `food` (
  `id` int(10) NOT NULL,
  `tenfood` text NOT NULL,
  `giafood` int(200) NOT NULL,
  `hinhanhfood` text NOT NULL,
  `motafood` text NOT NULL,
  `idfood` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `food`
--

INSERT INTO `food` (`id`, `tenfood`, `giafood`, `hinhanhfood`, `motafood`, `idfood`) VALUES
(1, 'Hot and Spicy Pizza', 199000, 'https://pizzakuller.ee/wp-content/uploads/2017/08/hotandspicy.png', 'Nguyên liệu: bò xay, ớt xanh, hành tây, cà chua, sốt cà chua, phomai Mozzarella.', 1),
(3, 'Chicken BBQ Pizza', 299000, 'https://craftcms-pizzaranch.s3.amazonaws.com/general-uploads/Menu-Images/_960x800_crop_center-center_none/PR-menu-BBQChicken-960x800.png?mtime=20181001133959', 'Nguyên liệu: lườn gà xông khói, ớt Đà Lạt, nấm, sốt BBQ, phomai Mozzarella.', 1),
(4, 'Meat lover Pizza', 100000, 'https://bestmargheritapizza.com/wp-content/uploads/2016/10/meatlovers_pizza-2.png', 'Nguyên liệu: jăm bông, xúc xích Chorizo, lườn gà xông khói, hành tây, oliu, sốt cà chua, phomai Mozzarella.', 1),
(5, 'Ocean Pizza', 200000, 'https://screamin-admin.bcdev.site/wp-content/uploads/2021/04/product-overhead_SS_Hawaiian.png', 'Nguyên liệu: cá ngừ đại dương, dứa, ngô hạt, sốt cà chua, phomai Mozzarella.', 1),
(6, 'Margherita Pizza', 99000, 'https://www.pngkey.com/png/full/169-1694415_vector-transparent-margherita-italian-cuisine-pasta-pizza.png', 'Nguyên liệu: cà chua tươi, sốt cà chua, phomai Mozzarella.', 1),
(7, 'Burger bò ốp la sốt Teriyaki', 79000, 'https://mcdonalds.vn/uploads/2018/food-categories/terayaki_burger.png', '300 gr thịt bò\r\n3 bánh burger\r\n1 củ hành tây\r\n1 trái ớt chuông\r\n1 trái cà chua\r\n1 bắp xà lách\r\nGia vị: sốt teriyaki, mirin', 2),
(8, 'Hamburger chay đơn giản', 50000, 'http://icons.iconarchive.com/icons/pixelkit/tasty-bites/512/hamburger-icon.png', 'Vỏ bánh hamburger\r\nMì căn\r\nLá thyme khô\r\nMuối tiêu\r\nDầu oliu\r\nXà lách\r\nSốt mayonaise', 2),
(9, 'Hamburger bánh cua', 50000, 'http://icons.iconarchive.com/icons/iconshock/food/256/hamburger-icon.png', 'Bột mì đa dụng 200g (10~11%protein) -Men nở 3g -Sữa tươi 130ml\r\nMuối 1/4mcf(1~2g) -Bơ lạt 25g - Sữa đặc có đường 25g\r\nMè trắng, phomai bò cười', 2),
(10, 'Hotdog Việt Nam', 30000, 'https://assets.webiconspng.com/uploads/2017/09/Hot-Dog-PNG-Image-79643.png', '4 quả trứng gà\r\n20 ml sữa tươi không đường\r\n70 gram đường\r\n150 gram bột mì\r\n30 gram chà bông\r\n1 muỗng cà phê bơ\r\n1 muỗng cà phê vani4 muỗng cà phê pate\r\n1 cây xúc xích loại vừa\r\n1 viên phô mai con bò cười\r\nMột số loại gia vị như muối, dầu ăn,..', 3),
(11, 'Hotdog kiểu Mỹ', 45000, 'https://www.downloadclipart.net/large/hot-dog-png-background-photo.png', '1/2 củ khoai tây\r\n1/2 củ hành tây\r\n1 ổ bánh mì hotdog\r\n100 gram thịt nạc vai\r\n2 muỗng cà phê dầu hào\r\n1,5 muỗng cà phê sốt Teriyaki\r\n1 cây xúc xích\r\nGia vị: tiêu, tương cà, sốt cà chua, bơ, sốt mù tạc', 3),
(12, 'Hotdog xúc xích kiểu Hàn Quốc', 50000, 'https://assets.webiconspng.com/uploads/2017/09/Hot-Dog-PNG-Image-92165.png', '4 cây xúc xích\r\n1 củ khoai tây\r\n200 gam bột mì\r\n3 gam bột nở\r\n2 gam muối\r\n10 gam đường\r\n100 ml sữa tươi', 3),
(13, 'Hotdog phô mai kiểu Hàn Quốc', 55000, 'https://www.downloadclipart.net/large/hot-dog-transparent-png.png', '160 gam phô mai Mozzarella\r\n150 gam bột mì\r\n3 gam bột nở\r\n100 gam bột chiên xù\r\n2 gam muối\r\n1 quả trứng gà\r\n10 gam đường\r\n100 ml sữa tươi', 3),
(14, 'Cocktail Old Fashioned', 50000, 'https://i.pinimg.com/originals/61/bc/16/61bc16f88759bf3f0d2852b5acdebfa1.jpg', 'Old Fashioned là loại Cocktail kinh điển xuất hiện ở hầu hết khắp các bar lớn nhỏ trên thế giới. Và đây là loại Cocktail phổ biến nhất, luôn đứng đầu trong danh sách các loại thức uống được yêu thích.\r\n\r\nChỉ cần có rượu Whisky (bourbon), một viên đường nhỏ, ba thìa cafe rượu đắng Angostura, một vài viên đá nhỏ, miếng cam để trang trí. Và pha chế chúng cùng với nhau, bạn đã có một ly Cocktail Old Fashioned để thưởng thức vào cuối tuần rồi.', 4),
(15, 'Cocktail Mojito', 70000, 'https://freepngimg.com/download/mojito/30292-8-mojito-photos.png', 'Cocktail Mojito là một loại thức uống truyền thống của người Cuba. Mojito (hay còn được gọi là Mohito) không quá xa lạ với những người sành Cocktail.\r\n\r\nĐây còn là loại thức uống hoàn hảo nếu bạn là một pha chế gia cho kỳ nghỉ ở bãi biển. Thành phần chính của Cocktail Mojito này là rượu Rum nhẹ, nước cốt chanh và bạc hà.', 4),
(16, 'Cocktail Bloody Mary', 90000, 'https://i.pinimg.com/originals/fb/a1/cc/fba1cc234a2e520a1de9f7944643f74f.png', 'Bloody Mary là một loại Cocktail được cho là có công thức pha chế phức tạp nhất thế giới. Bởi chúng có chứa Ketel One Vodka, nước ép cà chua và hỗn hợp gia vị tự chế biến như: nước cốt chanh tươi, cần tây, ô liu,ớt cay, hạt tiêu đen,… Thậm chí có nơi còn thêm cả nước sốt thịt bò. Chính sự phức tạp này khiến Bloody Mary trở nên rất khó pha chế, nhưng lại có hương vị cực kỳ đặc sắc và riêng biệt.', 4),
(17, 'Cocktail Moscow Mule', 40000, 'http://hugosbar.uk/wp-content/uploads/2018/06/cocktail_moscow_mule-1.png', 'Cocktail Moscow Mule là một loại thức uống truyền thống của người dân nước Nga. Được pha chế từ công thức gồm có Vodka, bia gừng, nước chanh cùng vài lá bạc hà và vài lát chanh dùng để trang trí. Ngoài ra, Moscow Mule được đựng trong một chiếc cốc bằng đồng lại càng tạo nên sự khác biệt và độc đáo cho loại Cocktail này.', 4),
(18, 'Cocktail Negroni', 50000, 'https://mrblack.co/content/uploads/sites/5/2019/10/MrBlack_Cocktails_3.png', 'Negroni là loại Cocktail khá là lâu đời, xuất xứ từ Italy vào những năm 1919. Tính đến nay loại thức uống này vẫn rất được ưa chuộng. Chỉ từ các nguyên liệu truyền thống như Gin, Sweet vermouth và Campari đã tạo nên loại Cocktail với sắc đỏ óng ánh cùng hương vị độc đáo. Thêm vài lát vỏ cam đã làm nao lòng bao nhiêu người yêu thích Cocktail.', 4),
(19, 'Cà phê Phin', 45000, 'https://gomsuminhlong.vn/datafiles/32543/upload/images/products/bo-phin-ca-phe-camellia-mau-ca-phe-2.png?t=1595320995', 'Loại cà phê xay mịn và được chắt lọc nhỏ giọt qua 1 dụng cụ pha đặc biệt. Đó là chiếc phin pha - một dụng cụ pha cafe kinh điển đã đi vào tiềm thức của bao thế hệ người Việt. Vậy cà phê có nguồn gốc từ đâu? Cây cà phê được du nhập vào nước ta từ thế kỷ 19, thời kỳ Pháp thuộc.', 4),
(20, 'Cà phê Arabica', 40000, 'https://luankha.com/wp-content/uploads/2019/09/46.jpg', 'Đây là một trong số các loại cafe ngon của Việt Nam có hạt hơi dài, được trồng ở độ cao trên 600m. Ở nước ta arabica thường chỉ được trồng tại Lâm Đồng. Cà phê Arabica có hai loại đang được nước ta trồng đó là Catimor và Moka. Moka có mùi thơm quyến rũ, vị nhạt; còn Catimor có mùi thơm nồng nàn, vị hơi chua.\r\n\r\nCatimor được nước ta trồng chủ yếu, quả cafe sau được thu hoạch sẽ lên men, rửa sạch và sấy. Hương vị của Arabica hơi chua, nhưng lập tực thấy được vị đắng.\r\n\r\nHiện nay ở nước ta có nhiều thương hiệu kinh doanh cafe arabica mà bạn có thể tìm mua như LeO, Hương Việt, Mê Trang', 4),
(21, 'Cà phê Culi', 50000, 'https://ciconline.vn/wp-content/uploads/2019/10/culi-1024x683.jpg', 'Một loại cà phê khác của nước ta có hạt tròn to bóng mẩy, đặc biệt so với các loại cafe ngon khác là nó chỉ có một hạt duy nhất trong một trái. Cà phê culi vị đắng ngắt, hương thơm say lòng người, nước đen sóng sánh.\r\n\r\nCác thương hiệu cà phê culi bạn có thể dễ dàng tìm mua trên thị trường như Mộc, Rita Võ cafe, cafe Bùi Văn Ngọ Đà Lạt', 4),
(22, 'Nước ép cà rốt', 30000, 'https://1.bp.blogspot.com/-kAY7ci7GUic/XQr5QtFu_PI/AAAAAAAADsY/SV--U7mh3WIm690s1GDtoXFKT5BGCdWygCLcBGAs/s1600/nuoc-ep-ca-chua-ca-rot.jpg', 'Nước ép cà rốt chứa hàm lượng magie, kali, canxi cao và là nguồn cung cấp đôi dào beta-carotene, axit alphalipoic, carotenoid và các loại vitamin ...vv. Vậy nước ép cà rốt có tác dụng gì? nó giúp làm tăng khả năng điều tiết của cơ thể, tăng cường thị lực và quá trình trao đổi chất, nâng cao hệ  thống miễn dịch, tăng sức đề kháng, giúp giải độc, hạ đường huyết và có tác dụng hiệu quả trong việc ngăn ngừa ung thư… Cách làm nước ép cà rốt cũng rất đơn giản, chị em có thể mua cà rốt và tự tay làm tại nhà. Loại nước ép trái trái này cũng nằm trong những loại nước ép giảm cân đẹp da ưa thích của rất nhiều chị em phụ nữ. Nước ép cà rốt còn là thức uống giúp nhuận tràng và điều trị táo bón mà bác sỹ khuyên dùng.', 4),
(23, 'Nước ép bưởi', 30000, 'http://cafeanhthu.com/uploads/shops/2021_01/nuoc-ep-buoi_1.jpg', 'Bưởi là một loại hoa quả sạch tự nhiên rất tốt cho cơ thể, ngoài công dụng ăn bưởi giảm cân thì nước ép bưởi còn là loại nước ép có khả năng phòng chống ung thư cao nhất trong các loại quả bởi khả năng tiêu diệt các gốc tự do có hại gây ra bệnh ung thư. Chất lycopen chứa trong nước ép bưởi còn giúp giảm nguy cơ đột quỵ và các bệnh về tim mạch. Tuy nhiên, cũng giống như nước ép cam, vì chúng chứa thành phần vitamin C cao nên khi sử dụng nước ép bưởi này 1 cách hợp lý, tránh lạm dụng.', 4),
(24, 'Nước ép táo', 30000, 'http://amthuctran.vn/wp-content/uploads/2019/09/70354060_1347051302102221_4734391803849998336_n-683x1024.png', 'Nước ép táo cũng là 1 trong những loại nước ép giảm cân vô cùng hiệu quả của các chị em phụ nữ. Nước táo ép chứa hàm lượng cácloại vitamin như A, C, B1, B2, K cao. Đây là những chất chống oxy hóa, rất có lợi cho hệ miễn dịch và là các yếu tố cần thiết của cơ thể. Nước ép táo có công dụng điều trị bệnh thiếu máu, viêm khớp mãn tính, giúp làm giảm lượng cholesterol trong máu. Trong nước ép táo còn chứa nguyên tố Bo giúp xương chắc khỏe hơn. Để làm nước ép táo thì vô cùng đơn giản bạn chỉ cần táo và một chiếc máy xay sinh tố thôi nhé. Ngoài uống nước táo ép nguyên chất thì bạn cũng có thể kết hợp táo với các loại trái cây khác như cà rốt, dứa để tạo thành các loại nước ép táo cà rốt, nước ép táo dứa thơm ngon, bổ dưỡng nhé!', 4),
(25, 'Nước ép dưa hấu', 30000, 'http://product.hstatic.net/1000287491/product/3._nuoc_ep_dua_hau_grande.jpg', 'Nước ép dưa hấu có nhiều công dụng thần kì đối với sức khỏe. Ngoài công dụng thanh nhiệt, giải độc, giải khát thì nước ép dưa hấu còn là liều thuốc tự nhiên rất tốt cho cơ thể. Tác dụng của nước ép dưa hấu giúp giảm cân, bổ sung năng lượng, hỗ trợ xương chắc khỏe, chống mệt mỏi và giúp phục hồi cơ bắp do nước ép dưa hấu rất giàu axit amin có tên là L-citrulline. Cách làm nước ép dưa hấu cũng rất đơn giản chỉ cần thêm một cái máy xay sinh tố. ', 4),
(26, 'Bánh Mochi Nhật', 20000, 'https://i.pinimg.com/originals/5e/58/53/5e5853fc39029ee4e809500d9738de1e.png', 'Mochi là món ăn truyền thống trong ngày Tết được người Nhật yêu thích, món bánh này tượng trưng cho sự may mắn và thịnh vượng trong năm mới.\r\n\r\nNhân bánh có thể được làm từ đậu đổ, đậu trắng hoặc dâu tây hay một số loại hoa quả khác kết hợp với đậu đỏ. Lớp vỏ bên ngoài mềm, dai là nét đặc trưng của bánh mochi, lớp bột gạo trắng muốt bên ngoài giúp chúng trở nên mịn màng và đẹp mắt hơn nhiều.', 5),
(27, 'Bánh Tiramisu Italia', 30000, 'https://allourway.com/wp-content/uploads/2019/05/Tiramisu-Cake.jpg', 'Tiramisu là một loại bánh ngọt tráng miệng rất nổi tiếng của Italia. Sản phẩm là sự kết hợp hòa quyện giữa hương thơm của cà phê, rượu nhẹ và vị béo của trứng cùng kem phô mai.\r\n\r\nChỉ cần cắn một miếng, bạn sẽ cảm nhận được tất cả các hương vị đó hòa quyện cùng một, chính vì thế người ta còn ví món bánh này là “Thiên đường trong miệng của bạn” nên nhất định phải thử một lần nhé.', 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `foodflashsale`
--

CREATE TABLE `foodflashsale` (
  `id` int(10) NOT NULL,
  `idfoodsale` int(10) NOT NULL,
  `giacu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `foodflashsale`
--

INSERT INTO `foodflashsale` (`id`, `idfoodsale`, `giacu`) VALUES
(4, 5, 400000),
(5, 8, 100000),
(6, 4, 200000),
(7, 9, 100000),
(8, 3, 598000),
(9, 20, 80000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaifood`
--

CREATE TABLE `loaifood` (
  `id` int(10) NOT NULL,
  `tenloaifood` text NOT NULL,
  `hinhanhloaifood` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaifood`
--

INSERT INTO `loaifood` (`id`, `tenloaifood`, `hinhanhloaifood`) VALUES
(1, 'Pizza', 'https://i.pinimg.com/originals/13/ce/2f/13ce2fa338e940ce30ea4babb7110c3e.png'),
(2, 'Burger', 'http://icons.iconarchive.com/icons/google/noto-emoji-food-drink/1024/32382-hamburger-icon.png'),
(3, 'Hotdog', 'http://icons.iconarchive.com/icons/google/noto-emoji-food-drink/1024/32385-hot-dog-icon.png'),
(4, 'Drink', 'https://freepngimg.com/download/drink/22730-2-drink-photos.png'),
(5, 'Donut', 'http://icons.iconarchive.com/icons/google/noto-emoji-food-drink/1024/32419-doughnut-icon.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `taikhoan` text NOT NULL,
  `matkhau` text NOT NULL,
  `hoten` text NOT NULL,
  `namsinh` text NOT NULL,
  `diachi` text NOT NULL,
  `email` text NOT NULL,
  `sodienthoai` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `taikhoan`, `matkhau`, `hoten`, `namsinh`, `diachi`, `email`, `sodienthoai`) VALUES
(1, 'admin', '123', 'Nguyễn Văn Chính', '2000', 'Nam Định', 'nguyenchinh992000@gmail.com', 906181931),
(2, 'nhanvien', '123', 'Nguyễn Phương Hảo', '1998', 'Hà Nam', 'chinh9999cccc@gmail.com', 384244467),
(3, 'chinh', '123', 'nguyenvanchinh', '2000', 'namdinh', 'chinh999ccc@gmail.com', 984476422),
(4, 'chinh123', '123', 'Nguyễn Văn Chính', '2000', 'Nam Định', '', 0),
(5, 'chienbinh', '123', 'Chính', '2000', 'Nam Định', '', 0),
(6, 'abc', '999', 'mnb', '999', 'mnb', 'chinh@gmail.com', 999);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `foodflashsale`
--
ALTER TABLE `foodflashsale`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaifood`
--
ALTER TABLE `loaifood`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT cho bảng `food`
--
ALTER TABLE `food`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT cho bảng `foodflashsale`
--
ALTER TABLE `foodflashsale`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `loaifood`
--
ALTER TABLE `loaifood`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
