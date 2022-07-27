DELIMITER $$
CREATE PROCEDURE sp_insert_product (
    IN title_in VARCHAR(255),
    IN price_in DECIMAL(12,0),
    IN quantity_in INT,
    IN idCategory_in INT,
    IN size_in INT,
    IN color_in VARCHAR(255),
    IN img_in BLOB,
    OUT success TINYINT(1),
    OUT message VARCHAR(255)
        )
BEGIN
	SET success = FALSE;

    IF(price_in < 0 OR price_in > 9999999999)
		THEN SET message ="Giá Tiền Nhập Vào Không Hợp Lệ";
ELSE IF(quantity_in < 0 OR quantity_in > 1000)
        THEN SET message ="Số Lượng Nhập Vào Từ 0 đến 1000";

ELSE IF(size_in < 38 OR size_in > 45)
        THEN SET  message ="Size nằm trong 38 đến 45";

ELSE IF(NOT EXISTS (SELECT * FROM category WHERE id = idCategory_in))
        THEN SET message= "Danh Mục Không Tồn Tại";

ELSE
			INSERT INTO `java_servlet_manager`.`product` (`title`, `price`, `quantity`, `createAT`, `idCategory`, `size`, `color`,`img`)
			VALUES (title_in, price_in, quantity_in, now(), idCategory_in, size_in , color_in, img_in);
			SET message = "Thêm Sản Phẩm Thành Công";
             SET success = TRUE;
END IF;
END IF;
END IF;
END IF;
END
$$

set @success = 0;
set @message = '0';
CALL sp_insert_product('AODep', 1111, 12, 4, 39, 'RED', 'NULL', @success, @message);
select @success, @message;