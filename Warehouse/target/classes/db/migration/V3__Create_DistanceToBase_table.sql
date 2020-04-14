create table Distance_To_Base (
    base_Id int unsigned not null,
    warehouse_Id int unsigned not null,
    distance double not null,
    constraint pk_DistanceToBase primary key (base_Id, warehouse_Id),
    foreign key (base_Id) references Courier_Base(id),
    foreign key (warehouse_Id) references Warehouse(id)
);