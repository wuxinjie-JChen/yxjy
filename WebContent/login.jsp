<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>一心教育登录</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- //for-mobile-apps -->
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	<style type="text/css">
		.main ul li{
			display: block;width:100px;line-height: 40px;float:left;font-size: 20px;
		}
		.main ul li.student{
			margin-left: 150px;
		}
	</style>
</head>
<body>
    <!-- main -->
    <div class="main">
        <h1>
           一心教育
        </h1>  
        <h3><i>Y.X.J.Y</i></h3>
        <form>
        	<ul>
        		<li class="student"><i>学生</i><input type="radio" name="type" class="type" value="1"></li>
        		<li><i>教师</i><input type="radio" name="type"  class="type" value="2"></li>
        		<li><i>管理员</i><input type="radio" name="type"  class="type" value="3"></li>
        	</ul>
        	
            <input type="text" name="adminName" value="用户名" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '用户名';}"
                   required="">
            <input type="password" name="adminPwd" value="Password" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '';}"
                   required="">
            <input type="button" value="登录" onclick="login()">
        </form>
    </div>
    
    <script type="text/javascript" src="js/jquery.min.js"></script>
   
    <script type="text/javascript">
    
    		function login(){
    			//获取用户名密码
    			var type=$('input[name="type"]:checked').val();
    			var name=$('input[name="adminName"]').val();
    			var pwd=$('input[name="adminPwd"]').val();
    			$.post('user.do',{op:'login',type:type,name:name,pwd:pwd},function(data){
    				if(data==1){
    					location.href="student/stmain.jsp";
    				}else if(data==2){
    					location.href="teacher/tmain.jsp";
    				}else if(data==3){
    					location.href="admin/smain.jsp";
    				}else{
    					alert("登陆失败");
    				}
    			});
    		}
    </script>
</body>
</html>