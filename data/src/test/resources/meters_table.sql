create table smart.meters
(
	NAME varchar(60) not null
		primary key,
	LOCATION varchar(50) null,
	apartment int null,
	constraint meters_ibfk_1
		foreign key (apartment) references smart.apartments (apartmentId)
			on update cascade
);


