create table Distance_To_Warehouse (
    from_Warehouse_Id int unsigned not null,
    to_Warehouse_Id int unsigned not null,
    distance double not null,
    constraint pk_DistanceToWarehouse primary key (from_Warehouse_Id, to_Warehouse_Id),
    foreign key (from_Warehouse_Id) references Warehouse(id),
    foreign key (to_Warehouse_Id) references Warehouse(id),
    check (from_Warehouse_Id < to_Warehouse_Id)
);