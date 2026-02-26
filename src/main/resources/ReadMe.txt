Feature	Method	URL	Notes
MySqlPass123!
ssh -i "spring-boot-app-key.pem" ec2-user@ec2-13-53-39-136.eu-north-1.compute.amazonaws.com
mysql -h student-management-db.crg2ckamob9g.eu-north-1.rds.amazonaws.com -P 3306 -u admin -p

Get All Students	: Should return a JSON array of all students.
	GET	http://localhost:8080/api/students	
	
Search by Name	:Checks if the "ContainingIgnoreCase" logic works.
GET	http://localhost:8080/api/students/search?name=John	

Filter by Class	: Filters by the specific class selected in your UI.
GET	http://localhost:8080/api/students/search?standard=10th	

Filter by Status	
GET	http://localhost:8080/api/students/search?active=true	Checks for active/inactive students.
Add New Student	POST	
http://localhost:8080/api/students/add	Requires a JSON Body (see below).

Delete Student	
DELETE	http://localhost:8080/api/students/1	Replace 1 with an actual ID from your database.




AWS RDS
mysql -h student-management-db.crg2ckamob9g.eu-north-1.rds.amazonaws.com -P 3306 -u admin -p 
make sure you allow firewall TCP- IPV4