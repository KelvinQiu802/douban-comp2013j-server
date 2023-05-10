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

## Entities Relationship Diagram

```mermaid
---
title: Douban Movies
---
erDiagram
		Movies ||--o{ Comments : contains
		Comments ||--o{ CommentLikes : has
		Users ||--o{ Comments : post
		Users ||--o{ Bookmarks : add
		Bookmarks }o--|| Movies : save
		Users ||--o{ Stars : star
		Movies ||--o{ Stars : has

		Movies {
			int movie_id PK
			double score
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
		
		Stars {
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
			int comment_to
		}
		
		CommentLikes {
			string user_name FK
			int comment_id FK
		}
```

## API Documentation

| HTTP Verbs | Endpoints                     | Action                             | Response Format |
| ---------- | ----------------------------- | ---------------------------------- | --------------- |
| GET        | /api/test                     | To get top 10 movies in the db.    | JSON            |

