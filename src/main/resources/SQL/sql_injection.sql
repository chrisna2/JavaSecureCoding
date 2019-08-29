use ss;

/*sql Injection*/

#select * from bk_user
#where USER_ID='' or 'a' = 'a'
#and USER_PASSWORD='' or 'a' = 'a';

/* ' or 'a' = 'a   입력하면 로그인이 된다?! */

select * from bk_user
where user_id = 'admin'#' and user_password='aaa';
/* mysql에서는 #을 붙이면 주석 처리되는 부분은 악용! */