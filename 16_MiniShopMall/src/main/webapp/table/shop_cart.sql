create table shop_cart(
	cart_num number primary key,
	cart_pnum number not null,
	cart_userid varchar2(50) not null,
	cart_pname varchar2(200) not null,
	cart_pqty number not null,
	cart_price number not null,
	cart_pspec varchar2(500),
	cart_pimage varchar2(300)
	
);