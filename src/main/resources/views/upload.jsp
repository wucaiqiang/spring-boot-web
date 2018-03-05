<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload page</title>

</head>
<body>

<div>============单文件上传====================</div>
<div class="upload">
	<form action="upload" enctype="multipart/form-data" method="post">
		<input type="file" name="file"/><br/>
		<input type="submit" value="上传">
	</form>
</div>

<div>============多文件上传====================</div>

<div class="upload" style="margin-top: 10px;">
	<form action="uploads" enctype="multipart/form-data" method="post">
		<input type="file" name="files"/><br/>
		<input type="file" name="files"/><br/>
		<input type="submit" value="上传">
	</form>
</div>

</body>
</html>