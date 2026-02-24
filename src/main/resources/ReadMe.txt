Feature	Method	URL	Notes
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