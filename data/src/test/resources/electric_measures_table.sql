create table smart.electric_measures
(
    ID int auto_increment
        primary key,
    MEASURING varchar(60) not null,
    UNIT_OF_MEASURE varchar(13) not null,
    MEASURED_DATA float(13,3) null,
    DATE_OF_MEASURING timestamp(6) default current_timestamp(6) not null on update current_timestamp(6),
    constraint MEASURING
        unique (MEASURING, DATE_OF_MEASURING),
    constraint electric_measures_ibfk_1
        foreign key (MEASURING) references smart.meters (NAME)
            on update cascade
);

