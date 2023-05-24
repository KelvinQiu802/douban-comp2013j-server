# COMP2013J Douban Project Server

<p align="center">
  <img src="https://imgbed.codingkelvin.fun/uPic/film.png" alt="Douban Movie Icon" style="height:150px;" />
</p>

## Configure Commit Lint Hook

```shell
$ chmod ug+x .githooks/*
$ git config core.hooksPath .githooks
```

[🌈Conventional Commits😋](https://www.conventionalcommits.org/en/v1.0.0/)

## Getting Started

1. Create a database named `douban` and import the `douban.sql` file.
2. Update the database password in `DBConfig.java`.
3. Start the Javalin app.
4. Build and start the [front-end website](https://github.com/KelvinQiu802/douban-comp2013j-client).

## Entities Relationship Diagram

```mermaid
---
title: Douban Movies
---
erDiagram
		Movies ||--o{ Comments : contains
		Comments ||--o{ CommentVotes : has
		Users ||--o{ Comments : post
		Users ||--o{ Bookmarks : add
		Bookmarks }o--|| Movies : save
		Users ||--o{ Scores : star
		Movies ||--o{ Scores : has
		Users ||--o{ CommentVotes : create

		Movies {
			int movie_id PK
			string country
			string intro
			string movie_title
			string starring
			string language
			string director
			string runtime
			string release_date
		}
		
		Users {
			string user_name PK
			string password
		}
		
		Scores {
			string user_name FK
			int movie_id FK
			int star
		}
		
		Bookmarks {
			string user_name FK
			int movie_id FK
			enum status
		}
		
		Comments {
			int comment_id PK
			string user_name FK
			int movie_id FK
			string content
			datetime time
		}
		
		CommentVotes {
			string user_name FK
			int comment_id FK
			enum status
		}
```

## API Documentation

| HTTP Verbs | Endpoints                                         | Action                                   | Response Format |
| ---------- | ------------------------------------------------- | ---------------------------------------- | --------------- |
| GET        | /api/test                                         | To get top 10 movies in the db           | JSON            |
| GET        | /api/movies/{id}                                  | To retrieve movie by id in the db        | JSON            |
| GET        | /api/movies?page={page}&limit={limit}             | To get movies by page and limit          | JSON            |
| GET        | /api/movies/count                                 | To get total number of movies in the db  | JSON            |
| POST       | /api/users                                        | Create an user in the db                 | JSON            |
| POST       | /api/users/login                                  | Login Authentication                     | JSON            |
| GET        | /api/users                                        | To get all user names in the db          | JSON            |
| GET        | /api/bookmarks/{userName}                         | To get the bookmarks by user name        | JSON            |
| POST       | /api/bookmakrs/{userName}/{movieId}/{status}      | Create a bookmark                        | JSON            |
| PUT        | /api/bookmarks/{userName}/{movieId}/{status}      | Update a bookmark                        | JSON            |
| DELETE     | /api/bookmarks/{userName}/{movieId}               | Delete a bookmark                        | JSON            |
| GET        | /api/scores/{movieId}                             | To get all the score records by movie id | JSON            |
| POST       | /api/scores/{userName}/{movieId}/{score}          | Create a score record                    | JSON            |
| PUT        | /api/scores/{userName}/{movieId}/{score}          | Update a score record                    | JSON            |
| DELETE     | /api/scores/{userName}/{movieId}                  | Delete a score record                    | JSON            |
| GET        | /api/comments/{commentId}                         | Get a comments by id                     | JSON            |
| GET        | /api/comments/movie/{movieId}                     | Get all the comments of a movie          | JSON            |
| POST       | /api/comments                                     | Create a comment                         | JSON            |
| DELETE     | /api/comments/{commentId}                         | Delete a comment                         | JSON            |
| GET        | /api/commentvotes/{commentId}                     | Get all comment votes by id              | JSON            |
| POST       | /api/commentvotes/{userName}/{commentId}/{status} | Create a comment vote                    | JSON            |
| PUT        | /api/commentvotes/{userName}/{commentId}/{status} | Update a comment vote                    | JSON            |
| DELETE     | /api/commentvotes/{userName}/{commentId}          | Delete a comment vote                    | JSON            |

