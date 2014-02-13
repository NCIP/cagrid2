alter table grouper_types drop foreign key FKA992D9E65236F20E;
alter table gridgrouper_membershiprequest_history drop foreign key FKF6C38EB551626995;
alter table gridgrouper_membershiprequest_history drop foreign key FKF6C38EB53847521;
alter table grouper_memberships drop foreign key FK2B2D6F0A5236F20E;
alter table grouper_memberships drop foreign key FK2B2D6F0ACC93A68B;
alter table grouper_memberships drop foreign key FK2B2D6F0ACF5106EC;
alter table grouper_memberships drop foreign key FK2B2D6F0A63219E27;
alter table grouper_memberships drop foreign key FK2B2D6F0A5000A8E0;
alter table grouper_fields drop foreign key FK6FFE2AEC4C7188FA;
alter table grouper_groups_types drop foreign key FKFBD6041CD26E040;
alter table grouper_groups_types drop foreign key FKFBD60411E2E76DB;
alter table grouper_groups drop foreign key FK723686073C82913E;
alter table grouper_groups drop foreign key FK7236860763219E27;
alter table grouper_stems drop foreign key FKA98254373C82913E;
alter table grouper_stems drop foreign key FKA982543763219E27;
alter table grouper_attributes drop foreign key FK92BF040A1E2E76DB;
alter table grouper_owners drop foreign key FK80231053A3FBEB83;
alter table grouper_owners drop foreign key FK802310535236F20E;
alter table gridgrouper_membershiprequest drop foreign key FKCF5D636051626995;
alter table gridgrouper_membershiprequest drop foreign key FKCF5D63601E2E76DB;
alter table SubjectAttribute drop foreign key FK33C2CAF0F69C367;
alter table grouper_sessions drop foreign key FKF43E3C105000A8E0;
alter table grouper_composites drop foreign key FK602E397FABC59087;
alter table grouper_composites drop foreign key FK602E397F63219E27;
alter table grouper_composites drop foreign key FK602E397F653F2B3;
alter table grouper_composites drop foreign key FK602E397F6B4EDCD2;
drop table if exists grouper_types;
drop table if exists gridgrouper_membershiprequest_history;
drop table if exists Subject;
drop table if exists grouper_memberships;
drop table if exists grouper_fields;
drop table if exists grouper_groups_types;
drop table if exists grouper_groups;
drop table if exists grouper_members;
drop table if exists grouper_stems;
drop table if exists grouper_attributes;
drop table if exists grouper_owners;
drop table if exists gridgrouper_membershiprequest;
drop table if exists SubjectAttribute;
drop table if exists grouper_sessions;
drop table if exists grouper_settings;
drop table if exists grouper_composites;
create table grouper_types (
   id varchar(128) not null,
   name varchar(255) not null unique,
   creator_id varchar(128),
   create_time bigint not null,
   assignable boolean,
   internal boolean,
   primary key (id)
);
create table gridgrouper_membershiprequest_history (
   id varchar(128) not null,
   membershiprequest_id varchar(128) not null,
   status varchar(255) not null,
   reviewer_id varchar(128),
   update_date bigint,
   public_note varchar(255),
   admin_note varchar(255),
   primary key (id)
);
create table Subject (
   subjectId varchar(255) not null,
   subjectTypeId varchar(32) not null,
   name varchar(255),
   primary key (subjectId)
);
create table grouper_memberships (
   id varchar(128) not null,
   owner_id varchar(128) not null,
   member_id varchar(128) not null,
   list_name varchar(32) not null,
   list_type varchar(32) not null,
   mship_type varchar(32) not null,
   via_id varchar(128),
   depth int,
   parent_membership varchar(128),
   membership_uuid varchar(128),
   creator_id varchar(128),
   create_time bigint,
   primary key (id),
   unique (owner_id, member_id, list_name, list_type, via_id, depth, membership_uuid)
);
create table grouper_fields (
   id varchar(128) not null,
   group_type varchar(128) not null,
   field_type varchar(32) not null,
   field_name varchar(32) not null unique,
   read_priv varchar(128) not null,
   write_priv varchar(128) not null,
   nullable boolean,
   primary key (id)
);
create table grouper_groups_types (
   group_id varchar(128) not null,
   type_id varchar(128) not null,
   primary key (group_id, type_id)
);
create table grouper_groups (
   owner_id varchar(128) not null,
   parent_stem varchar(128),
   primary key (owner_id)
);
create table grouper_members (
   id varchar(128) not null,
   member_uuid varchar(128) not null,
   subject_id varchar(255) not null,
   subject_source varchar(255) not null,
   subject_type varchar(255) not null,
   primary key (id),
   unique (subject_id, subject_source, subject_type)
);
create table grouper_stems (
   owner_id varchar(128) not null,
   description varchar(255),
   display_extension varchar(255) not null,
   display_name varchar(255) not null,
   extension varchar(255) not null,
   name varchar(255) not null,
   parent_stem varchar(128),
   primary key (owner_id)
);
create table grouper_attributes (
   id varchar(128) not null,
   version integer not null,
   group_id varchar(128),
   field_name varchar(32) not null,
   field_type varchar(32) not null,
   value varchar(255) not null,
   primary key (id)
);
create table grouper_owners (
   id varchar(128) not null,
   creator_id varchar(128) not null,
   create_source varchar(255),
   create_time bigint not null,
   modifier_id varchar(128),
   modify_source varchar(255),
   modify_time bigint,
   uuid varchar(128) not null,
   primary key (id)
);
create table gridgrouper_membershiprequest (
   id varchar(128) not null,
   requestor varchar(255) not null,
   group_id varchar(128) not null,
   request_time bigint,
   status varchar(255) not null,
   reviewer_id varchar(128),
   review_time bigint,
   public_note varchar(255),
   admin_note varchar(255),
   primary key (id)
);
create table SubjectAttribute (
   subjectId varchar(255) not null,
   name varchar(255) not null,
   value varchar(255) not null,
   searchValue varchar(255),
   primary key (subjectId, name, value)
);
create table grouper_sessions (
   id varchar(128) not null,
   member_id varchar(128),
   start_time datetime not null,
   session_uuid varchar(128),
   primary key (id)
);
create table grouper_settings (
   id varchar(128) not null,
   schema_version integer not null,
   primary key (id)
);
create table grouper_composites (
   owner_id varchar(128) not null,
   owner varchar(128) not null,
   left_factor varchar(128) not null,
   right_factor varchar(128) not null,
   type varchar(32) not null,
   primary key (owner_id)
);
alter table grouper_types add index FKA992D9E65236F20E (creator_id), add constraint FKA992D9E65236F20E foreign key (creator_id) references grouper_members (id);
alter table gridgrouper_membershiprequest_history add index FKF6C38EB551626995 (reviewer_id), add constraint FKF6C38EB551626995 foreign key (reviewer_id) references grouper_members (id);
alter table gridgrouper_membershiprequest_history add index FKF6C38EB53847521 (membershiprequest_id), add constraint FKF6C38EB53847521 foreign key (membershiprequest_id) references gridgrouper_membershiprequest (id);
create index subject_idx on Subject (subjectId, subjectTypeId);
create index membership_depth_idx on grouper_memberships (depth);
create index membership_createtime_idx on grouper_memberships (create_time);
create index membership_owner_idx on grouper_memberships (owner_id);
create index membership_parent_idx on grouper_memberships (parent_membership);
create index membership_via_idx on grouper_memberships (via_id);
create index membership_field_idx on grouper_memberships (list_name, list_type);
create index membership_creator_idx on grouper_memberships (creator_id);
create index membership_type_idx on grouper_memberships (mship_type);
create index membership_member_idx on grouper_memberships (member_id);
alter table grouper_memberships add index FK2B2D6F0A5236F20E (creator_id), add constraint FK2B2D6F0A5236F20E foreign key (creator_id) references grouper_members (id);
alter table grouper_memberships add index FK2B2D6F0ACC93A68B (parent_membership), add constraint FK2B2D6F0ACC93A68B foreign key (parent_membership) references grouper_memberships (id);
alter table grouper_memberships add index FK2B2D6F0ACF5106EC (via_id), add constraint FK2B2D6F0ACF5106EC foreign key (via_id) references grouper_owners (id);
alter table grouper_memberships add index FK2B2D6F0A63219E27 (owner_id), add constraint FK2B2D6F0A63219E27 foreign key (owner_id) references grouper_owners (id);
alter table grouper_memberships add index FK2B2D6F0A5000A8E0 (member_id), add constraint FK2B2D6F0A5000A8E0 foreign key (member_id) references grouper_members (id);
create index field_type_idx on grouper_fields (field_type);
alter table grouper_fields add index FK6FFE2AEC4C7188FA (group_type), add constraint FK6FFE2AEC4C7188FA foreign key (group_type) references grouper_types (id);
alter table grouper_groups_types add index FKFBD6041CD26E040 (type_id), add constraint FKFBD6041CD26E040 foreign key (type_id) references grouper_types (id);
alter table grouper_groups_types add index FKFBD60411E2E76DB (group_id), add constraint FKFBD60411E2E76DB foreign key (group_id) references grouper_groups (owner_id);
alter table grouper_groups add index FK723686073C82913E (parent_stem), add constraint FK723686073C82913E foreign key (parent_stem) references grouper_stems (owner_id);
alter table grouper_groups add index FK7236860763219E27 (owner_id), add constraint FK7236860763219E27 foreign key (owner_id) references grouper_owners (id);
create index member_subjectsource_idx on grouper_members (subject_source);
create index member_uuid_idx on grouper_members (member_uuid);
create index member_subjecttype_idx on grouper_members (subject_type);
create index member_subjectid_idx on grouper_members (subject_id);
create index stem_displayname_idx on grouper_stems (display_name);
create index stem_displayextn_idx on grouper_stems (display_extension);
create index stem_name_idx on grouper_stems (name);
create index stem_extn_idx on grouper_stems (extension);
alter table grouper_stems add index FKA98254373C82913E (parent_stem), add constraint FKA98254373C82913E foreign key (parent_stem) references grouper_stems (owner_id);
alter table grouper_stems add index FKA982543763219E27 (owner_id), add constraint FKA982543763219E27 foreign key (owner_id) references grouper_owners (id);
create index attribute_field_idx on grouper_attributes (field_name, field_type);
create index attribute_value_idx on grouper_attributes (value);
create index attribute_group_idx on grouper_attributes (group_id);
alter table grouper_attributes add index FK92BF040A1E2E76DB (group_id), add constraint FK92BF040A1E2E76DB foreign key (group_id) references grouper_groups (owner_id);
create index owner_creator_idx on grouper_owners (creator_id);
create index owner_modifier_idx on grouper_owners (modifier_id);
create index owner_createtime_idx on grouper_owners (create_time);
create index owner_uuid_idx on grouper_owners (uuid);
create index owner_modifytime_idx on grouper_owners (modify_time);
alter table grouper_owners add index FK80231053A3FBEB83 (modifier_id), add constraint FK80231053A3FBEB83 foreign key (modifier_id) references grouper_members (id);
alter table grouper_owners add index FK802310535236F20E (creator_id), add constraint FK802310535236F20E foreign key (creator_id) references grouper_members (id);
alter table gridgrouper_membershiprequest add index FKCF5D636051626995 (reviewer_id), add constraint FKCF5D636051626995 foreign key (reviewer_id) references grouper_members (id);
alter table gridgrouper_membershiprequest add index FKCF5D63601E2E76DB (group_id), add constraint FKCF5D63601E2E76DB foreign key (group_id) references grouper_groups (owner_id);
create index subjectattribute_key_idx on SubjectAttribute (name);
create index subjectattribute_id_idx on SubjectAttribute (subjectId);
alter table SubjectAttribute add index FK33C2CAF0F69C367 (subjectId), add constraint FK33C2CAF0F69C367 foreign key (subjectId) references Subject (subjectId);
alter table grouper_sessions add index FKF43E3C105000A8E0 (member_id), add constraint FKF43E3C105000A8E0 foreign key (member_id) references grouper_members (id);
create index composite_owner_idx on grouper_composites (owner);
create index composite_factor_idx on grouper_composites (left_factor, right_factor);
alter table grouper_composites add index FK602E397FABC59087 (left_factor), add constraint FK602E397FABC59087 foreign key (left_factor) references grouper_owners (id);
alter table grouper_composites add index FK602E397F63219E27 (owner_id), add constraint FK602E397F63219E27 foreign key (owner_id) references grouper_owners (id);
alter table grouper_composites add index FK602E397F653F2B3 (owner), add constraint FK602E397F653F2B3 foreign key (owner) references grouper_owners (id);
alter table grouper_composites add index FK602E397F6B4EDCD2 (right_factor), add constraint FK602E397F6B4EDCD2 foreign key (right_factor) references grouper_owners (id);
