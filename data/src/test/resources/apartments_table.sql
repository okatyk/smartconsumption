create table smart.apartments
(
	apartmentId int not null
		primary key,
	floor varchar(13) null,
	number varchar(13) null,
	constraint MEASURING
		unique (floor, number)
);

