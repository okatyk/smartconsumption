create table apartments
(
    apartmentId int         not null
        primary key,
    floor       varchar(13) null,
    number      varchar(13) null,
    constraint MEASURING
        unique (floor, number)
);

create table meters
(
    NAME      varchar(60) not null
        primary key,
    LOCATION  varchar(50) null,
    apartment int         null,
    constraint meters_ibfk_1
        foreign key (apartment) references apartments (apartmentId)
            on update cascade
);

create table electric_measures
(
    ID                int auto_increment
        primary key,
    MEASURING         varchar(60)                               not null,
    UNIT_OF_MEASURE   varchar(13)                               not null,
    MEASURED_DATA     float(13, 3)                              null,
    DATE_OF_MEASURING timestamp(6) default current_timestamp(6) not null on update current_timestamp(6),
    constraint MEASURING
        unique (MEASURING, DATE_OF_MEASURING),
    constraint electric_measures_ibfk_1
        foreign key (MEASURING) references meters (NAME)
            on update cascade
);

create table heating_measures
(
    ID                int auto_increment
        primary key,
    MEASURING         varchar(60)                               not null,
    UNIT_OF_MEASURE   varchar(13)                               not null,
    MEASURED_DATA     float(13, 3)                              null,
    DATE_OF_MEASURING timestamp(6) default current_timestamp(6) not null on update current_timestamp(6),
    constraint MEASURING
        unique (MEASURING, DATE_OF_MEASURING),
    constraint heating_measures_ibfk_1
        foreign key (MEASURING) references meters (NAME)
            on update cascade
);

create table water_measures
(
    ID                int auto_increment
        primary key,
    MEASURING         varchar(60)                               not null,
    UNIT_OF_MEASURE   varchar(13)                               not null,
    MEASURED_DATA     float(13, 3)                              null,
    DATE_OF_MEASURING timestamp(6) default current_timestamp(6) not null on update current_timestamp(6),
    constraint MEASURING
        unique (MEASURING, DATE_OF_MEASURING),
    constraint water_measures_ibfk_1
        foreign key (MEASURING) references meters (NAME)
            on update cascade
);


