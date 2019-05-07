create table actor (id integer primary key autoincrement,
                    first_name text,
                    last_name text);

create table affiliation (role text,
                          start_date date,
                          end_date date,
                          actor integer,
                          organisation text,
                          primary key (actor, organisation),
                          foreign key (actor) references actor(id),
                          foreign key (organisation) references organisation(name));

create table argument (id integer primary key autoincrement,
                      discourse_name text,
                      rephrasing text,
                      start_index integer,
                      end_index integer,
                      foreign key (discourse_name) references discourse(name));


create table argument_link (argument_1 integer, argument_2 integer, link text,
                            primary key (argument_1, argument_2),
                            foreign key (argument_1) references argument(id),
                            foreign key (argument_2) references argument(id));

create table discourse (name text not null unique primary key,
                        source text,
                        d_text text not null,
                        foreign key (source) refrences source(name));

create table organisation (name text not null unique primary key);

create table source (name text not null unique primary key);