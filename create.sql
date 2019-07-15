create table event (id bigint not null, date datetime(6), description varchar(255), title varchar(255), primary key (id)) engine=InnoDB
create table event_attendees (event_id bigint not null, attendees_id varchar(255) not null, primary key (event_id, attendees_id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table tag (id bigint not null, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user_group (id bigint not null, address varchar(255), city varchar(255), country varchar(255), name varchar(255), postal_code varchar(255), state_or_province varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table user_group_events (group_id bigint not null, events_id bigint not null, primary key (group_id, events_id)) engine=InnoDB
alter table user_group_events add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)
alter table event_attendees add constraint FKbwhsss8n1vcqu5fvrkyb6wifv foreign key (attendees_id) references user (id)
alter table event_attendees add constraint FKap7lh3xccuje5vul66hut5ia7 foreign key (event_id) references event (id)
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id)
alter table user_group_events add constraint FKde1on71cqt4na61qjmdx4jpy6 foreign key (events_id) references event (id)
alter table user_group_events add constraint FKbrrphbsny3tlf4212gxwl9w0u foreign key (group_id) references user_group (id)
create table event (id bigint not null, date datetime(6), description varchar(255), title varchar(255), primary key (id)) engine=InnoDB
create table event_attendees (event_id bigint not null, attendees_id varchar(255) not null, primary key (event_id, attendees_id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table tag (id bigint not null, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user_group (id bigint not null, address varchar(255), city varchar(255), country varchar(255), name varchar(255), postal_code varchar(255), state_or_province varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table user_group_events (group_id bigint not null, events_id bigint not null, primary key (group_id, events_id)) engine=InnoDB
alter table user_group_events add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)
alter table event_attendees add constraint FKbwhsss8n1vcqu5fvrkyb6wifv foreign key (attendees_id) references user (id)
alter table event_attendees add constraint FKap7lh3xccuje5vul66hut5ia7 foreign key (event_id) references event (id)
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id)
alter table user_group_events add constraint FKde1on71cqt4na61qjmdx4jpy6 foreign key (events_id) references event (id)
alter table user_group_events add constraint FKbrrphbsny3tlf4212gxwl9w0u foreign key (group_id) references user_group (id)
create table event (id bigint not null auto_increment, date datetime(6), description varchar(255), title varchar(255), primary key (id)) engine=InnoDB
create table event_attendees (event_id bigint not null, attendees_id varchar(255) not null, primary key (event_id, attendees_id)) engine=InnoDB
create table tag (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user_group (id bigint not null auto_increment, address varchar(255), city varchar(255), country varchar(255), name varchar(255), postal_code varchar(255), state_or_province varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table user_group_events (group_id bigint not null, events_id bigint not null, primary key (group_id, events_id)) engine=InnoDB
alter table user_group_events add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)
alter table event_attendees add constraint FKbwhsss8n1vcqu5fvrkyb6wifv foreign key (attendees_id) references user (id)
alter table event_attendees add constraint FKap7lh3xccuje5vul66hut5ia7 foreign key (event_id) references event (id)
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id)
alter table user_group_events add constraint FKde1on71cqt4na61qjmdx4jpy6 foreign key (events_id) references event (id)
alter table user_group_events add constraint FKbrrphbsny3tlf4212gxwl9w0u foreign key (group_id) references user_group (id)
create table event (id bigint not null auto_increment, date datetime(6), description varchar(255), title varchar(255), primary key (id)) engine=InnoDB
create table event_attendees (event_id bigint not null, attendees_id varchar(255) not null, primary key (event_id, attendees_id)) engine=InnoDB
create table tag (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user_group (id bigint not null auto_increment, address varchar(255), city varchar(255), country varchar(255), name varchar(255), postal_code varchar(255), state_or_province varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table user_group_events (group_id bigint not null, events_id bigint not null, primary key (group_id, events_id)) engine=InnoDB
alter table user_group_events add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)
alter table event_attendees add constraint FKbwhsss8n1vcqu5fvrkyb6wifv foreign key (attendees_id) references user (id)
alter table event_attendees add constraint FKap7lh3xccuje5vul66hut5ia7 foreign key (event_id) references event (id)
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id)
alter table user_group_events add constraint FKde1on71cqt4na61qjmdx4jpy6 foreign key (events_id) references event (id)
alter table user_group_events add constraint FKbrrphbsny3tlf4212gxwl9w0u foreign key (group_id) references user_group (id)
create table event (id bigint not null auto_increment, date datetime(6), description varchar(255), title varchar(255), primary key (id)) engine=InnoDB
create table event_attendees (event_id bigint not null, attendees_id varchar(255) not null, primary key (event_id, attendees_id)) engine=InnoDB
create table tag (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user_group (id bigint not null auto_increment, address varchar(255), city varchar(255), country varchar(255), name varchar(255), postal_code varchar(255), state_or_province varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table user_group_events (group_id bigint not null, events_id bigint not null, primary key (group_id, events_id)) engine=InnoDB
alter table user_group_events add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)
alter table event_attendees add constraint FKbwhsss8n1vcqu5fvrkyb6wifv foreign key (attendees_id) references user (id)
alter table event_attendees add constraint FKap7lh3xccuje5vul66hut5ia7 foreign key (event_id) references event (id)
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id)
alter table user_group_events add constraint FKde1on71cqt4na61qjmdx4jpy6 foreign key (events_id) references event (id)
alter table user_group_events add constraint FKbrrphbsny3tlf4212gxwl9w0u foreign key (group_id) references user_group (id)
create table event (id bigint not null auto_increment, date datetime(6), description varchar(255), title varchar(255), primary key (id)) engine=InnoDB
create table event_attendees (event_id bigint not null, attendees_id varchar(255) not null, primary key (event_id, attendees_id)) engine=InnoDB
create table tag (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user_group (id bigint not null auto_increment, address varchar(255), city varchar(255), country varchar(255), name varchar(255), postal_code varchar(255), state_or_province varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table user_group_events (group_id bigint not null, events_id bigint not null, primary key (group_id, events_id)) engine=InnoDB
alter table user_group_events add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)
alter table event_attendees add constraint FKbwhsss8n1vcqu5fvrkyb6wifv foreign key (attendees_id) references user (id)
alter table event_attendees add constraint FKap7lh3xccuje5vul66hut5ia7 foreign key (event_id) references event (id)
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id)
alter table user_group_events add constraint FKde1on71cqt4na61qjmdx4jpy6 foreign key (events_id) references event (id)
alter table user_group_events add constraint FKbrrphbsny3tlf4212gxwl9w0u foreign key (group_id) references user_group (id)
create table event (id bigint not null auto_increment, date datetime(6), description varchar(255), title varchar(255), primary key (id)) engine=InnoDB
create table event_attendees (event_id bigint not null, attendees_id varchar(255) not null, primary key (event_id, attendees_id)) engine=InnoDB
create table tag (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user_group (id bigint not null auto_increment, address varchar(255), city varchar(255), country varchar(255), name varchar(255), postal_code varchar(255), state_or_province varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table user_group_events (group_id bigint not null, events_id bigint not null, primary key (group_id, events_id)) engine=InnoDB
alter table user_group_events add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)
alter table event_attendees add constraint FKbwhsss8n1vcqu5fvrkyb6wifv foreign key (attendees_id) references user (id)
alter table event_attendees add constraint FKap7lh3xccuje5vul66hut5ia7 foreign key (event_id) references event (id)
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id)
alter table user_group_events add constraint FKde1on71cqt4na61qjmdx4jpy6 foreign key (events_id) references event (id)
alter table user_group_events add constraint FKbrrphbsny3tlf4212gxwl9w0u foreign key (group_id) references user_group (id)
create table event (id bigint not null auto_increment, date datetime(6), description varchar(255), title varchar(255), primary key (id)) engine=InnoDB
create table event_attendees (event_id bigint not null, attendees_id varchar(255) not null, primary key (event_id, attendees_id)) engine=InnoDB
create table tag (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user_group (id bigint not null auto_increment, address varchar(255), city varchar(255), country varchar(255), name varchar(255), postal_code varchar(255), state_or_province varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table user_group_events (group_id bigint not null, events_id bigint not null, primary key (group_id, events_id)) engine=InnoDB
alter table user_group_events add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)
alter table event_attendees add constraint FKbwhsss8n1vcqu5fvrkyb6wifv foreign key (attendees_id) references user (id)
alter table event_attendees add constraint FKap7lh3xccuje5vul66hut5ia7 foreign key (event_id) references event (id)
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id)
alter table user_group_events add constraint FKde1on71cqt4na61qjmdx4jpy6 foreign key (events_id) references event (id)
alter table user_group_events add constraint FKbrrphbsny3tlf4212gxwl9w0u foreign key (group_id) references user_group (id)
create table Event (id bigint not null auto_increment, date datetime(6), description varchar(255), title varchar(255), primary key (id)) engine=InnoDB
create table Event_attendees (Event_id bigint not null, attendees_id varchar(255) not null, primary key (Event_id, attendees_id)) engine=InnoDB
create table tag (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table User (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table user_group (id bigint not null auto_increment, address varchar(255), city varchar(255), country varchar(255), name varchar(255), postalCode varchar(255), stateOrProvince varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table user_group_events (Group_id bigint not null, events_id bigint not null, primary key (Group_id, events_id)) engine=InnoDB
alter table user_group_events add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)
alter table Event_attendees add constraint FKlsumi7h6roqpseq4qti11n5lq foreign key (attendees_id) references User (id)
alter table Event_attendees add constraint FKkuyar03tpl6ie3i76m8sme7vl foreign key (Event_id) references Event (id)
alter table user_group add constraint FKqylvkgn4f086og4a9h5qpw0rv foreign key (user_id) references User (id)
alter table user_group_events add constraint FK22i012e5jxqtpc2y45v23qtpl foreign key (events_id) references Event (id)
alter table user_group_events add constraint FK9ed6mlspvn58g5mgnu55vexyt foreign key (Group_id) references user_group (id)

    create table Event (
       id bigint not null auto_increment,
        date datetime,
        description varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table Event_attendees (
       Event_id bigint not null,
        attendees_id varchar(255) not null,
        primary key (Event_id, attendees_id)
    ) engine=MyISAM

    create table tag (
       id bigint not null auto_increment,
        description varchar(255),
        name varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table User (
       id varchar(255) not null,
        email varchar(255),
        name varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table user_group (
       id bigint not null auto_increment,
        address varchar(255),
        city varchar(255),
        country varchar(255),
        name varchar(255),
        postalCode varchar(255),
        stateOrProvince varchar(255),
        user_id varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table user_group_events (
       Group_id bigint not null,
        events_id bigint not null,
        primary key (Group_id, events_id)
    ) engine=MyISAM

    alter table user_group_events 
       add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)

    alter table Event_attendees 
       add constraint FKlsumi7h6roqpseq4qti11n5lq 
       foreign key (attendees_id) 
       references User (id)

    alter table Event_attendees 
       add constraint FKkuyar03tpl6ie3i76m8sme7vl 
       foreign key (Event_id) 
       references Event (id)

    alter table user_group 
       add constraint FKqylvkgn4f086og4a9h5qpw0rv 
       foreign key (user_id) 
       references User (id)

    alter table user_group_events 
       add constraint FK22i012e5jxqtpc2y45v23qtpl 
       foreign key (events_id) 
       references Event (id)

    alter table user_group_events 
       add constraint FK9ed6mlspvn58g5mgnu55vexyt 
       foreign key (Group_id) 
       references user_group (id)

    create table Event (
       id bigint not null auto_increment,
        date datetime,
        description varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table Event_attendees (
       Event_id bigint not null,
        attendees_id varchar(255) not null,
        primary key (Event_id, attendees_id)
    ) engine=MyISAM

    create table tag (
       id bigint not null auto_increment,
        description varchar(255),
        name varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table User (
       id varchar(255) not null,
        email varchar(255),
        name varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table user_group (
       id bigint not null auto_increment,
        address varchar(255),
        city varchar(255),
        country varchar(255),
        name varchar(255),
        postalCode varchar(255),
        stateOrProvince varchar(255),
        user_id varchar(255),
        primary key (id)
    ) engine=MyISAM

    create table user_group_events (
       Group_id bigint not null,
        events_id bigint not null,
        primary key (Group_id, events_id)
    ) engine=MyISAM

    alter table user_group_events 
       add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)

    alter table Event_attendees 
       add constraint FKlsumi7h6roqpseq4qti11n5lq 
       foreign key (attendees_id) 
       references User (id)

    alter table Event_attendees 
       add constraint FKkuyar03tpl6ie3i76m8sme7vl 
       foreign key (Event_id) 
       references Event (id)

    alter table user_group 
       add constraint FKqylvkgn4f086og4a9h5qpw0rv 
       foreign key (user_id) 
       references User (id)

    alter table user_group_events 
       add constraint FK22i012e5jxqtpc2y45v23qtpl 
       foreign key (events_id) 
       references Event (id)

    alter table user_group_events 
       add constraint FK9ed6mlspvn58g5mgnu55vexyt 
       foreign key (Group_id) 
       references user_group (id)

    create table Event (
       id bigint not null auto_increment,
        date datetime,
        description varchar(255),
        title varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table Event_attendees (
       Event_id bigint not null,
        attendees_id varchar(255) not null,
        primary key (Event_id, attendees_id)
    ) engine=InnoDB

    create table tag (
       id bigint not null auto_increment,
        description varchar(255),
        name varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table User (
       id varchar(255) not null,
        email varchar(255),
        name varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table user_group (
       id bigint not null auto_increment,
        address varchar(255),
        city varchar(255),
        country varchar(255),
        name varchar(255),
        postalCode varchar(255),
        stateOrProvince varchar(255),
        user_id varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table user_group_events (
       Group_id bigint not null,
        events_id bigint not null,
        primary key (Group_id, events_id)
    ) engine=InnoDB

    alter table user_group_events 
       add constraint UK_o9sq43dq8lh6jv7yklcnby7j2 unique (events_id)

    alter table Event_attendees 
       add constraint FKlsumi7h6roqpseq4qti11n5lq 
       foreign key (attendees_id) 
       references User (id)

    alter table Event_attendees 
       add constraint FKkuyar03tpl6ie3i76m8sme7vl 
       foreign key (Event_id) 
       references Event (id)

    alter table user_group 
       add constraint FKqylvkgn4f086og4a9h5qpw0rv 
       foreign key (user_id) 
       references User (id)

    alter table user_group_events 
       add constraint FK22i012e5jxqtpc2y45v23qtpl 
       foreign key (events_id) 
       references Event (id)

    alter table user_group_events 
       add constraint FK9ed6mlspvn58g5mgnu55vexyt 
       foreign key (Group_id) 
       references user_group (id)
