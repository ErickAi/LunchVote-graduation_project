[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fd40c1a6a36a4658aad236b777ad1dae)](https://www.codacy.com/app/ErickAi/lunch-voting?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ErickAi/lunch-voting&amp;utm_campaign=Badge_Grade)
Lunch voting System. Graduation Project.
=====================================

<details><summary>
TASK
</summary>

##### Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.


#### The tasks are:
- Build a voting system for deciding where to have lunch.
- 2 types of users: admin and regular users 
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
- If it is before 11:00 we asume that he changed his mind.
- If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides new menu each day.

##### As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.
##### Make sure everything works with latest version that is on github :)
##### Asume that your API will be used by a frontend developer to build frontend on top of that.
</details>
 
## Implementation stack:
- HSQLDB (PostgteSQL in branch)
- Hibernate
- Spring
- SpringMVC
- Dependency managment - Maven

### Some cURL commands:
#### User handling: (access denied for user and allowed for admin)
     curl 'http://localhost:8080/api/users' -i -H 'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/users/100000' -i -H 'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
	 curl 'http://localhost:8080/api/users/by/100000' -i -H 'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/users' -i -d '{"name" : "NewUser", "email" : "new@mail.ru","password" : "123456","roles" : ["ROLE_USER"]}' -H 'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H'Content-Type: application/json'

### Restaurant handling
     curl 'http://localhost:8080/api/restaurants' -i -H'Authorization: Basic dXNlckB5YW5kZXgucnU6dXNlcg=='
     curl 'http://localhost:8080/api/restaurants/10000' -i -H'Authorization: Basic dXNlckB5YW5kZXgucnU6dXNlcg=='
     curl 'http://localhost:8080/api/restaurants/find-by-name?name=Man' -i -H'Authorization: Basic dXNlckB5YW5kZXgucnU6dXNlcg=='

		Modification (access denied for User):
     curl 'http://localhost:8080/api/restaurants' -i -d'{"name" : "New Restaurant"}' -H'Authorization: Basic dXNlckB5YW5kZXgucnU6dXNlcg==' -H'Content-Type: application/json'

		Modification (Access allowed for Admin):
     curl 'http://localhost:8080/api/restaurants' -i -d'{"name" : "New Restaurant"}' -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H'Content-Type: application/json'
     curl 'http://localhost:8080/api/restaurants/10002' -X'DELETE' -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H'Content-Type: application/json'

### Menu handling
     curl 'http://localhost:8080/api/menus' -i -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/menus/1001' -i -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
	 curl 'http://localhost:8080/api/menus/for-date' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
	 curl 'http://localhost:8080/api/menus/for-date?date=2018-12-31' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/menus/for-restaurant/10000' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
	 
		Modification (Access allowed for Admin):
	 curl 'http://localhost:8080/api/menus' -i -d'{"date" : "2018-12-31", "restaurant":{"name": "Terrassa"}}' -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H'Content-Type: application/json' 
     curl 'http://localhost:8080/api/menus/1000' -i -X'PUT' -d'{"date" : "2018-12-31", "restaurant":{"name": "New Restaurant"}}' -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H'Content-Type: application/json'


### Dish handling

     curl 'http://localhost:8080/api/dishes' -i -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/dishes/101' -i -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/dishes/for-date' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     curl 'http://localhost:8080/api/dishes/for-menu/1000' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
     
		Modification (Access allowed for Admin):
     curl 'http://localhost:8080/api/dishes' -i -d'{"name":"Fry", "price":100, "menu":{"date":"2018-12-31", "restaurant":{"name": "Mansarda"}}}' -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H'Content-Type: application/json'	 
     curl 'http://localhost:8080/api/dishes/113' -X'DELETE' -H'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H'Content-Type: application/json'

### Voting
		Check current vote:
     curl 'http://localhost:8080/api/vote' -i -H'Authorization: Basic dXNlckB5YW5kZXgucnU6dXNlcg=='

		Change current vote (allowed before 11.00 a.m. If time expired returns not modified vote)
     curl 'http://localhost:8080/api/vote/for-menu/1002' -i -X POST -H'Authorization: Basic dXNlckB5YW5kZXgucnU6dXNlcg==' -H'Content-Type: application/json'

		Vote for tomorow menu: 
     curl 'http://localhost:8080/api/vote/for-menu/1003' -i -X POST -H'Authorization: Basic dXNlckB5YW5kZXgucnU6dXNlcg==' -H'Content-Type: application/json'
	 
		Change tomorow vote for another menu: 
     curl 'http://localhost:8080/api/vote/for-menu/1004' -i -X POST -H'Authorization: Basic dXNlckB5YW5kZXgucnU6dXNlcg==' -H'Content-Type: application/json'    
		
		Vote for expired date menu(trows exception):
     curl 'http://localhost:8080/api/vote/for-menu/1000' -i -X POST -H'Authorization: Basic dXNlckB5YW5kZXgucnU6dXNlcg==' -H'Content-Type: application/json'
	 
