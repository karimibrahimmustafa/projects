<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title> Title </title>
</head>

	<body>
<form action="add" method="post">
{{ csrf_field() }}
    name:<input type="text" name="name" placeholder="put your name">
    from:<input type="text" name="from" placeholder="put your from">
<input type="submit">
        </form>
</body>
</html>
