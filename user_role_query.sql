set pagesize 200
set linesize 200
set trimspool on
col username format a30
col role_name format a30
select u.app_user_id, u.username, r.role_name
from app_users u
join app_user_roles ur on u.app_user_id = ur.app_user_id
join app_roles r on ur.role_id = r.role_id
order by u.username, r.role_name;
exit
