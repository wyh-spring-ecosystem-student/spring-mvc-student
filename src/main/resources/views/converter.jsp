<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HttpMessageConverter Demo</title>
</head>
<body>
    <div id="resp"></div><input type="button" onclick="req();" value="请求"/>
<script src="assets/js/jquery.js" type="text/javascript"></script>
<script>
    function req(){
        $.ajax({
            url: "convert",
            data: "1-wangyunfei", //数据格式和自定义的消息转换器一致
            type:"POST",
            contentType:"application/x-wisely", //媒体类型和消息转换器的一致
            success: function(data){
                $("#resp").html(data);
            }
        });
    }

</script>
</body>
</html>